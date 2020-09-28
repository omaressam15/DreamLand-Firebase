package com.omaressam.mydreamland.Model;


public class DreamLand {
    private String image;
    private String name;
    private String number;
    private int id;

    public DreamLand(String image, String name, String number, int id) {
        this.image = image;
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public DreamLand() {

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
