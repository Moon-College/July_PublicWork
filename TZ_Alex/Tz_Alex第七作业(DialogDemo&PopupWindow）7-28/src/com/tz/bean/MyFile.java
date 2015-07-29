package com.tz.bean;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

public class MyFile {
	private String name;
	private Bitmap bitmap;
	private String path;
	private Boolean isPicBoolean;
	
	
	public MyFile() {
		super();
	}
	public MyFile(String name, Bitmap bitmap, String path, Boolean isPicBoolean) {
		super();
		this.name = name;
		this.bitmap = bitmap;
		this.path = path;
		this.isPicBoolean = isPicBoolean;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getIsPicBoolean() {
		return isPicBoolean;
	}
	public void setIsPicBoolean(Boolean isPicBoolean) {
		this.isPicBoolean = isPicBoolean;
	}
	
	
}
