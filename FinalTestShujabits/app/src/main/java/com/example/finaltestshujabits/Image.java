package com.example.finaltestshujabits;

import android.graphics.Bitmap;

public class Image {
    private Bitmap photo;
    private String name;

    public Image(Bitmap photo, String name) {
        this.photo = photo;
        this.name = name;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }
}
