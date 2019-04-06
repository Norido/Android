package com.example.tracnghiem;

import java.io.Serializable;
public class Result implements Serializable{
    private String KQ;
    public Result(String KQ){
        this.KQ = KQ;
    }
    public String toString(){
        return this.KQ;
    }
}
