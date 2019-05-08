package com.example.bai2;

import android.graphics.Bitmap;

public class Player {
    private String medal, home,name;
    private Bitmap bmp;

    public Player(){}
    public Player(String age, String home, String name, Bitmap icon){
        this.medal = age;
        this.home = home;
        this.name = name;
        this.bmp = icon;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String age) {
        this.medal = age;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconBitmap(Bitmap bmp){
        this.bmp = bmp;
    }
    public Bitmap getBitmap(){
        return bmp;
    }
}
