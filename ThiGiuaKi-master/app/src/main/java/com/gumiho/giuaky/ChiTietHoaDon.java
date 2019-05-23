package com.gumiho.giuaky;

public class ChiTietHoaDon {
    private int soHD, maHang, soLuong;

    public ChiTietHoaDon() {

    }
    public ChiTietHoaDon(int soHD, int maHang, int soLuong) {
        this.soHD = soHD;
        this.maHang = maHang;
        this.soLuong = soLuong;
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
