package com.example.bai1_listviewcountry;

public class ClassCountry {
    public Integer icon;
    public String ten;
    public String detal;

    public ClassCountry(Integer icon, String ten, String detal) {
        this.icon = icon;
        this.ten = ten;
        this.detal = detal;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDetal() {
        return detal;
    }

    public void setDetal(String detal) {
        this.detal = detal;
    }
}
