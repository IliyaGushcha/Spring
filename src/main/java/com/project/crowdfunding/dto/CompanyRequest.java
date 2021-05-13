package com.project.crowdfunding.dto;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public class CompanyRequest {

    @Autowired
    private Gson gson;

    private String name;
    private String description;
    private BonusDto  bonus;
    private String subject;
    private String video;
    private int amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private MultipartFile image;


    public CompanyRequest() {}

    public CompanyRequest(String name, String description, BonusDto bonus, String subject, String video, int amount, Date deadline, MultipartFile image) {
        this.name = name;
        this.description = description;
        this.bonus = bonus;
        this.subject = subject;
        this.video = video;
        this.amount = amount;
        this.deadline = deadline;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BonusDto getBonus() {
        return this.bonus;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
