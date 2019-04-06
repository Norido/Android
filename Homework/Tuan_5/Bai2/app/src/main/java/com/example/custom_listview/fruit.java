package com.example.custom_listview;

public class fruit {
    private int name_fruit;
    private int description;
    private int img;

    public fruit() {
        super();
    }
    public fruit(int name_fruit, int description, int img){
        super();
        this.name_fruit = name_fruit;
        this.description = description;
        this.img = img;
    }

    public int getName_fruit() {
        return name_fruit;
    } public int getDescription() {
        return description;
    } public int getimg() {
        return img;
    }

    public void setName_fruit(int name_fruit) {
        this.name_fruit = name_fruit;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
