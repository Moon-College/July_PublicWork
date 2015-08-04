package com.tz.browse.bean;

import android.graphics.Bitmap;

/**
 * Created by Çü·¢ on 2015/8/3.
 */
public class MyFile {
    private String name;
    private String path;
    private boolean isImage;
    private Bitmap icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", isImage=" + isImage +
                ", icon=" + icon +
                '}';
    }

    public MyFile() {
    }

    public MyFile(String name, String path, Bitmap icon, boolean isImage) {

        this.name = name;
        this.path = path;
        this.icon = icon;
        this.isImage = isImage;
    }
}
