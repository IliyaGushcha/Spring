package com.project.crowdfunding.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.crowdfunding.dto.Message;
import com.project.crowdfunding.models.Company;
import com.project.crowdfunding.models.Image;
import com.project.crowdfunding.rep.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    ImageService imageService;

    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryService() {
        valuesMap.put("cloud_name", "db2ql90c8");
        valuesMap.put("api_key", "624461911991266");
        valuesMap.put("api_secret", "AAcKN6TemqcqOoWNMBxdztgQ5us");
        cloudinary = new Cloudinary(valuesMap);
    }


    public ResponseEntity<?> upload(MultipartFile multipartFile, Company company) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("Invalid image!"), HttpStatus.BAD_REQUEST);
        }
        Image image =
                new Image((String)result.get("original_filename"),
                        (String)result.get("url"),
                        (String)result.get("public_id"));
        image.setCompany(company);
        imageService.save(image);
        return new ResponseEntity(new Message("Image uploaded!"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(long id) throws IOException {
        Map result = cloudinary.uploader().destroy(String.valueOf(id), ObjectUtils.emptyMap());
        if(!imageService.exists(id))
            return new ResponseEntity(new Message("Not exists!"), HttpStatus.NOT_FOUND);
        Image image = imageService.getOne(id).get();
        imageService.delete(id);
        return new ResponseEntity(new Message("Image deleted!"), HttpStatus.OK);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
