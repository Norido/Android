package com.example.rebo;

public class BookName {
    private String bookName;
    private String biaSach;

    public BookName(String biaSach, String bookName ) {
        this.bookName = bookName;
        this.biaSach = biaSach;
    }

    public String getBiaSach() {
        return biaSach;
    }

    public void setBiaSach(String biaSach) {
        this.biaSach = biaSach;
    }
    public BookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return this.bookName;
    }
}
