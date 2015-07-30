package com.tz.fileexplorer.bean;

import android.graphics.Bitmap;

public class MyFile {
    private String name;
    private Bitmap icon;
    private String path;
    private boolean isPic;
    public MyFile() {
        // TODO Auto-generated constructor stub
    }
    public MyFile(String name, Bitmap icon, String path, boolean isPic) {
        super();
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.isPic = isPic;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Bitmap getIcon() {
        return icon;
    }
    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public boolean isPic() {
        return isPic;
    }
    public void setPic(boolean isPic) {
        this.isPic = isPic;
    }
    @Override
    public String toString() {
        return "MyFile [name=" + name + ", icon=" + icon + ", path=" + path + ", isPic=" + isPic
                + "]";
    }

}
