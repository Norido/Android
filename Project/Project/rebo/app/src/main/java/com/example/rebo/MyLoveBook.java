package com.example.rebo;

public class MyLoveBook {
    private String BookName;
    private String valueBoolean;

    public MyLoveBook(String bookName, String valueBoolean) {
        BookName = bookName;
        this.valueBoolean = valueBoolean;
    }

    public MyLoveBook() {
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getValueBoolean() {
        return valueBoolean;
    }

    public void setValueBoolean(String valueBoolean) {
        this.valueBoolean = valueBoolean;
    }
}
