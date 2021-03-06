package com.tz.lsn6.bean;

import android.graphics.Bitmap;

public class MyFile {
	String name; 	/* 名称 */
	Bitmap icon;	/* 图标  */
	String path;	/* 文件路径 */
	boolean isImg = false; /* 是否为图片 */
	String modifyTime = ""; /* 修改时间  */
	
	public MyFile(String name, Bitmap icon, String path, boolean isImg) {
		super();
		this.name = name;
		this.icon = icon;
		this.path = path;
		this.isImg = isImg;
	}
	public MyFile() {
		// TODO Auto-generated constructor stub
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
	public boolean isImg() {
		return isImg;
	}
	public void setIsImg(boolean isImg) {
		this.isImg = isImg;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "MyFile [name=" + name + ", icon=" + icon + ", path=" + path
				+ ", isImg=" + isImg + "]";
	}
	

}
