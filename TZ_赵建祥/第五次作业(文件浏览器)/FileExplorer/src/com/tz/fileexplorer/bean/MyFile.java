package com.tz.fileexplorer.bean;

import android.graphics.Bitmap;

/**
 * 文件类
 * 
 * @author 赵建祥
 * 
 */
public class MyFile {

	private String name;
	private Bitmap icon;
	private String path;

	public MyFile() {

	}

	public MyFile(String name, Bitmap icon) {
		super();
		this.name = name;
		this.icon = icon;
	}

	public MyFile(String name, Bitmap icon, String path) {
		super();
		this.name = name;
		this.icon = icon;
		this.path = path;
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

}
