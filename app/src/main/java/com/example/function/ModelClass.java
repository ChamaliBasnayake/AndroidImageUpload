package com.example.function;

import android.graphics.Bitmap;

public class ModelClass {
    private String imageName;
    private Bitmap image;

    public String getImageName() {
        return imageName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ModelClass(String imageName, Bitmap image){
        this.imageName = imageName;
        this.image = image;
    }
}
