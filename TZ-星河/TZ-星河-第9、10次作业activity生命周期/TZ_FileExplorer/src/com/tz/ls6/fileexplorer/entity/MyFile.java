package com.tz.ls6.fileexplorer.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

/**
 * 文件对象,封装文件的基本信息
 * 
 * @author GWP
 * 
 */
public class MyFile implements Serializable {
	/**
	 * 当前文件的路径
	 */
	private String path;
	/**
	 * 当前文件的图标，文件夹，其他文件，图片文件的不同
	 */
	private Bitmap icon;
	/**
	 * 文件名
	 */
	private String fileName;
	
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
