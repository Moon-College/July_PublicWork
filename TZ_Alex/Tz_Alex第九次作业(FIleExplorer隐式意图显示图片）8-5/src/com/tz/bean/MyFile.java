package com.tz.bean;

import android.graphics.Bitmap;

public class MyFile {
	private String name;
	private String path;
	private Bitmap bm;
	private Boolean isPic;
	
	
	
	public MyFile() {
		super();
	}
	public MyFile(String name, String path, Bitmap bm, Boolean isPic) {
		super();
		this.name = name;
		this.path = path;
		this.bm = bm;
		this.isPic = isPic;
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
	public Bitmap getBm() {
		return bm;
	}
	public void setBm(Bitmap bm) {
		this.bm = bm;
	}
	public Boolean getIsPic() {
		return isPic;
	}
	public void setIsPic(Boolean isPic) {
		this.isPic = isPic;
	}
	
	
}
