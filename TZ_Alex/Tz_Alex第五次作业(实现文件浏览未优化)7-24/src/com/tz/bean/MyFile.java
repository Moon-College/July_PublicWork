package com.tz.bean;

import android.graphics.Bitmap;

public class MyFile {
	private String info;
	private Bitmap icon;
	private String path;
	private boolean isPhoto;
	
	public MyFile() {
		super();
	}
	
	public MyFile(String info, Bitmap icon, String path, boolean isPhoto) {
		super();
		this.info = info;
		this.icon = icon;
		this.path = path;
		this.isPhoto = isPhoto;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	public boolean isPhoto() {
		return isPhoto;
	}
	public void setPhoto(boolean isPhoto) {
		this.isPhoto = isPhoto;
	}
	
}
