package com.tz.fileexplorer.bean;

import android.graphics.Bitmap;

public class MyFile{

	private boolean isIcon;
	private Bitmap icon;
	private String path;
	private String name;

	public MyFile(){}

	public MyFile(String name, String path, Bitmap icon, boolean isIcon) {
		
		this.name = name;
		this.path = path;
		this.icon = icon;
		this.isIcon = isIcon;
	}

	public boolean isIcon() {
		return isIcon;
	}

	public void setIcon(boolean isIcon) {
		this.isIcon = isIcon;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "myfile [name=" + name + " path=" + path + " icon=" + icon
				+ " isIcon=" + isIcon + "]";
	}

}
