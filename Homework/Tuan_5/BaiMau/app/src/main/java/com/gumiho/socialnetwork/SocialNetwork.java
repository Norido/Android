package com.gumiho.socialnetwork;

public class SocialNetwork {
    private int icon;
    private int title;
    public SocialNetwork() {
        super();

    }

    public SocialNetwork(int icon, int title) {
        super();
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int gettitle() {
        return title;
    }

    public void settitle(int title) {
        this.title = title;
    }
}
