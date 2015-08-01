package com.example.tz_724_filelistview.bean;

import android.graphics.Bitmap;

public class MyFile {
	private String name;
	private String path;
	private Bitmap icon;
	private Boolean isIcon;

	public MyFile() {
	}

	public MyFile(String name, String path, Bitmap icon, Boolean isIcon) {
		super();
		this.name = name;
		this.path = path;
		this.icon = icon;
		this.isIcon = isIcon;
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

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public Boolean getIsIcon() {
		return isIcon;
	}

	public void setIsIcon(Boolean isIcon) {
		this.isIcon = isIcon;
	}

}
