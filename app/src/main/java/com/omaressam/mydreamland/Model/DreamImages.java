package com.omaressam.mydreamland.Model;

public class DreamImages  {

    private String image;
    private String images;

    public DreamImages(String image, String images) {
        this.image = image;
        this.images = images;
    }
      public DreamImages() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
