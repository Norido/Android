package com.gumiho.giuaky;

public class HoaDon {
    private int soHD, maKH;
    private String ngayLap, ngayGiao;

    public HoaDon() {

    }
    public HoaDon(int soHD, String ngayLap, String ngayGiao,int maKH) {
        this.soHD = soHD;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.ngayGiao = ngayGiao;
    }

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }
}
