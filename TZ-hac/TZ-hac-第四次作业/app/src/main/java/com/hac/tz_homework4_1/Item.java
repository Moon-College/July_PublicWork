package com.hac.tz_homework4_1;

import android.graphics.Bitmap;

/**
 * Created by hp on 2015/7/23.
 */
public class Item {
    private String name;
    private String author;
    private int number;
    Bitmap photo;
    Bitmap background;

    public Item(String name, String author, int number, Bitmap photo, Bitmap background) {
        this.name = name;
        this.author = author;
        this.number = number;
        this.photo = photo;
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getBackground() {
        return background;
    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }
}
