package com.project.crowdfunding.services;

import com.project.crowdfunding.models.Image;
import com.project.crowdfunding.rep.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> list(){
        return imageRepository.findByOrderById();
    }

    public Optional<Image> getOne(long id){
        return imageRepository.findById(id);
    }

    public void save(Image image){
        imageRepository.save(image);
    }

    public void delete(long id){
        imageRepository.deleteById(id);
    }

    public boolean exists(long id){
        return imageRepository.existsById(id);
    }
}
