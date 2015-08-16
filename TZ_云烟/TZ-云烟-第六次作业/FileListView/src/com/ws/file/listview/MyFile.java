package com.ws.file.listview;

import android.graphics.Bitmap;

public class MyFile {
	private String name;
	private Bitmap  bitmap;
	private String path;
	private boolean isIocn;
	
	public MyFile() {
		super();
	}
	public MyFile(String name, Bitmap bitmap, String path) {
		super();
		this.name = name;
		this.bitmap = bitmap;
		this.path = path;
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
	public boolean isIocn() {
		return isIocn;
	}
	public void setIocn(boolean isIocn) {
		this.isIocn = isIocn;
	}

}
