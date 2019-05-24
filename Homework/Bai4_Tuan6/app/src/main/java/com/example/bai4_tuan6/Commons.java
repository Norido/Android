package com.example.bai4_tuan6;

import android.text.Editable;

public class Commons {
    private String id;
    private String name;
    public String getId (){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Commons (String id, String name){
        super();
        this.id = id;
        this.name = name;
    }
    public Commons(){
        super();
    }
    public String toString(){
        return this.id+" - "+this.name;
    }
}

