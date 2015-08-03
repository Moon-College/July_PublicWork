package com.tz.fileexplorer.bean;

import android.graphics.Bitmap;

public class FileBean {
	//文件的名称
	private String name;
	//文件的路径
	private String path;
	//文件的图标
	private Bitmap icon;
	//是否是目录
	private boolean isDir;	
	
	
	public FileBean(String name, String path, Bitmap icon, boolean isDir) {
		super();
		this.name = name;
		this.path = path;
		this.icon = icon;
		this.isDir = isDir;
	}
	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bitmap getIcon() {
		return icon;
	}
	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDir() {
		return isDir;
	}
	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
	
	

}
