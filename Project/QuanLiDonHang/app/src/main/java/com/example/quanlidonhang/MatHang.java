package com.example.quanlidonhang;

public class MatHang {
    public int maMatHang;
    public   String tenMatHang;
    public  String donViTinh;
    public int soTien;

    public MatHang(int maDonHang, String tenDonHang, String donViTinh, int soTien) {
        this.maMatHang = maMatHang;
        this.tenMatHang = tenMatHang;
        this.donViTinh = donViTinh;
        this.soTien = soTien;
    }

    public int getMaDonHang() {
        return maMatHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maMatHang = maDonHang;
    }

    public String getTenDonHang() {
        return tenMatHang;
    }

    public void setTenDonHang(String tenDonHang) {
        this.tenMatHang = tenDonHang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
