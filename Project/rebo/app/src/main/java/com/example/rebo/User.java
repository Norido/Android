package com.example.rebo;

public class User {
    private String username;
    private String email;
    private String Avatar;
    private String SDT;

    public User() {
    }

    public User(String username, String email, String avatar, String SDT) {
        this.username = username;
        this.email = email;
        Avatar = avatar;
        this.SDT = SDT;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getSDT() {
        return SDT;
    }
}
