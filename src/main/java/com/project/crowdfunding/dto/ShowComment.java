package com.project.crowdfunding.dto;

public class ShowComment {

    private String text;

    private String username;

    public ShowComment() {
    }

    public ShowComment(String text, String username) {
        this.text = text;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
