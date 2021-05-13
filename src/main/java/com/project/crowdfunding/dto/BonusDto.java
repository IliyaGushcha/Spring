package com.project.crowdfunding.dto;

public class BonusDto {
    private String name;
    private int cost;

    public BonusDto() {}

    public BonusDto(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
