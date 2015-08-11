package com.tz.lsn6.bean;

import android.graphics.Bitmap;

public class MyFile {
	String name;
	Bitmap icon;
	String path;
	boolean isImg = false;
	public MyFile(String name, Bitmap icon, String path, boolean isImg) {
		super();
		this.name = name;
		this.icon = icon;
		this.path = path;
		this.isImg = isImg;
	}
	public MyFile() {
		// TODO Auto-generated constructor stub
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
	public boolean isImg() {
		return isImg;
	}
	public void setImg(boolean isImg) {
		this.isImg = isImg;
	}
	@Override
	public String toString() {
		return "MyFile [name=" + name + ", icon=" + icon + ", path=" + path
				+ ", isImg=" + isImg + "]";
	}
	

}
