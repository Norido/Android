package com.gumiho.sachonline;

public class GribView {
    private int biaSach;
    private String tenSach;
    private String tacGia;

    public GribView(int biaSach, String tenSach, String tacGia) {
        this.biaSach = biaSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
    }

    public int getBiaSach() {
        return biaSach;
    }

    public void setBiaSach(int biaSach) {
        this.biaSach = biaSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
}
