package com.example.a4;

import android.graphics.Bitmap;

public class DisplayBlock {
    String url;
    int id;
    int rating;
    Bitmap bitmap;

    public DisplayBlock(String url, int id, int rating, Bitmap bitmap) {
        this.url = url;
        this.id = id;
        this.rating = rating;
        this.bitmap = bitmap;
    }


    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void print() {
        System.out.println("URL: " + url + " ID: " + id + " Rating: " + rating);
    }
}
