package com.project.crowdfunding.dto;

public class CommentDto {

    private String text;
    private long userId;
    private long companyId;

    public CommentDto() {
    }

    public CommentDto(String text, long userId, long companyId) {
        this.text = text;
        this.userId = userId;
        this.companyId = companyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
