package com.gumiho.giuaky;

public class MatHang {
    private String tenHang, DVT, donGia;
    private int maHang;

    public MatHang(){

    }
    public MatHang(int maHang, String tenHang,String DVT,String donGia) {
        this.maHang = maHang;
        this.donGia = donGia;
        this.tenHang = tenHang;
        this.DVT = DVT;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }


}
