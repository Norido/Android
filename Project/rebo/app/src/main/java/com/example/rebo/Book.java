package com.example.rebo;

// Tạo đối tượng Sách để add vào List View
public class Book {
    private int biaSach;
    private int tenSach;
    private int tacGia;

    public Book(){

    }
    public Book(int biaSach, int tenSach, int tacGia) {
        this.biaSach = biaSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
    }
    public Book(int biaSach){
        this.biaSach = biaSach;
    }
    public int getBiaSach() {
        return biaSach;
    }

    public void setBiaSach(int biaSach) {
        this.biaSach = biaSach;
    }

    public int getTenSach() {
        return tenSach;
    }

    public void setTenSach(int tenSach) {
        this.tenSach = tenSach;
    }

    public int getTacGia() {
        return tacGia;
    }

    public void setTacGia(int tacGia) {
        this.tacGia = tacGia;
    }
}
