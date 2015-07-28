package com.hac.tz_homework5.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by hp on 2015/7/25.
 */
public class Item {
    private String name;
    private String dir;
    private boolean isDir;
    private boolean isPic;
    private boolean isFile;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private long size;

    public Item() {
    }

    public boolean isFile() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    public Item(String dir, boolean isDir, boolean isPic, String name) {
        this.dir = dir;
        this.isDir = isDir;
        this.isPic = isPic;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    public boolean isPic() {
        return isPic;
    }

    public void setIsPic(boolean isPic) {
        this.isPic = isPic;
    }
}
