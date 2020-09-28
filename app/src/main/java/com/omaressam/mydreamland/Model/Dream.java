package com.omaressam.mydreamland.Model;

public class Dream {

    private String image;
    private String name;

    public Dream(String image, String name) {
        this.image = image;
        this.name = name;

    } public Dream() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
