package com.tz.file.bean;

import android.graphics.Bitmap;

/**
 * Created by Tokey on 2015/7/25.
 */
public class AFile {

    private String name;
    private String path;
    private Bitmap icon;
    private boolean isImage;


    public AFile() {


    }

    public AFile(String name, String path, Bitmap icon,boolean isImage) {
        this.isImage = isImage;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }


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

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }
}