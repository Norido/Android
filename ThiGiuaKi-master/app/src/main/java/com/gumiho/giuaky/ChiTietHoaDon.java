package com.gumiho.giuaky;

public class ChiTietHoaDon {
    private int key,soHD, maHang, soLuong;

    public ChiTietHoaDon() {

    }
    public ChiTietHoaDon(int key, int soHD, int maHang, int soLuong) {
        this.key = key;
        this.soHD = soHD;
        this.maHang = maHang;
        this.soLuong = soLuong;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getSoHD() {
        return soHD;
    }

    public void setSoHD(int soHD) {
        this.soHD = soHD;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
