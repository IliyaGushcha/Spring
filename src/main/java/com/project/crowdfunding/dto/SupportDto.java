package com.project.crowdfunding.dto;

public class SupportDto {

    private long companyId;
    private long userId;
    private long bonusId;

    public SupportDto() {
    }

    public SupportDto(long companyId, long userId, long bonusId) {
        this.companyId = companyId;
        this.userId = userId;
        this.bonusId = bonusId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBonusId() {
        return bonusId;
    }

    public void setBonusId(long bonusId) {
        this.bonusId = bonusId;
    }
}
