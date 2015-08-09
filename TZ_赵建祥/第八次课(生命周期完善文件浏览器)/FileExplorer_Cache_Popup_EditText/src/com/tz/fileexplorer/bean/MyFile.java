package com.tz.fileexplorer.bean;

import java.util.Comparator;

import android.graphics.Bitmap;

/**
 * 文件类
 * 
 * @author 赵建祥
 * 
 */
public class MyFile  implements Comparator<MyFile>{

	private String name;
	private Bitmap icon;
	private String path;
	private boolean isImage;

	public MyFile(){

	}

	public MyFile(String name, Bitmap icon) {
		super();
		this.name = name;
		this.icon = icon;
	}

	public MyFile(String name, Bitmap icon, String path,boolean isImage) {
		super();
		this.name = name;
		this.icon = icon;
		this.path = path;
		this.isImage=isImage;
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

	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

	public int compare(MyFile lhs, MyFile rhs) {
		// TODO Auto-generated method stub
		return lhs.getName().compareTo(rhs.getName());
	}
}
