package com.project.crowdfunding.controllers;

import com.project.crowdfunding.dto.Message;
import com.project.crowdfunding.models.Image;
import com.project.crowdfunding.services.CloudinaryService;
import com.project.crowdfunding.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImageService imageService;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> list = imageService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id)throws IOException {
        return cloudinaryService.delete(id);
    }
}