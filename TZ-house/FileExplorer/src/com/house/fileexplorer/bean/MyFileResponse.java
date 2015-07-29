package com.house.fileexplorer.bean;

import android.graphics.Bitmap;

public class MyFileResponse {
	private String name;
	private Bitmap icon;
	private String path;
	private boolean isIcon;
	
	public MyFileResponse() {
		super();
	}
	public MyFileResponse(String name, Bitmap icon, String path, boolean isIcon) {
		super();
		this.name = name;
		this.icon = icon;
		this.path = path;
		this.isIcon = isIcon;
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
	public boolean isIcon() {
		return isIcon;
	}
	public void setIcon(boolean isIcon) {
		this.isIcon = isIcon;
	}
	
}
