package com.tz.fileadapter.baen;

import android.graphics.Bitmap;

public class Myfile {
	private String name;
	private Bitmap icon;
	private String path;
	private boolean isIcon;
	public Myfile() {
		// TODO Auto-generated constructor stub
	}
	public Myfile(String name, Bitmap icon, String path, boolean isIcon) {
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
	@Override
	public String toString() {
		return "Myfile [name=" + name + ", icon=" + icon + ", path=" + path
				+ ", isIcon=" + isIcon + "]";
	}
	
}
