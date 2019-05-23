package com.gumiho.giuaky;

public class KhachHang {
    private int maKH;
    private String tenKH, DiaChi, SDT;

    public KhachHang() {

    }
    public KhachHang(int maKH, String tenKH, String diaChi, String SDT) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
