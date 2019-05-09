package com.example.bai3_tuan6;


public class film {
    private String name, des;
    private float ratingbar;
    private int icon;
    public film(){}
    public film(String name, String des, float ratingbar,int icon ){
        this.name = name;
        this.des = des;
        this.ratingbar = ratingbar;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(float ratingbar) {
        this.ratingbar = ratingbar;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
