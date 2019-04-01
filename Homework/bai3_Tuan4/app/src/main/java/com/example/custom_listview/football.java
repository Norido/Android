package com.example.custom_listview;

public class football {
    private int name_football;
    private int description;
    private int img1;
    private int img2;

    public football() {
        super();
    }

    public football(int name_football, int description, int img, int img2) {
        super();
        this.name_football = name_football;
        this.description = description;
        this.img1 = img;
        this.img2 = img2;
    }

    public int getName_football() {
        return name_football;
    }

    public void setName_football(int name_football) {
        this.name_football = name_football;
    }

    public int getDescription() {
        return description;
    }

    public int getImg() {
        return img1;
    }


    public int getImg2() {
        return img2;
    }


}
