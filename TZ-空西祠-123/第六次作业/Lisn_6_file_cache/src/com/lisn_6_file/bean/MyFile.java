package com.lisn_6_file.bean;

import android.graphics.Bitmap;

public class MyFile {
	private String name;//�ļ���
	private Bitmap bitmap;//ͼƬ
	private String path;//·��
	private boolean isIcon;//�ж��Ƿ�ΪͼƬ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public  Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
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
	public MyFile(String name, Bitmap bitmap, String path, boolean isIcon) {
		super();
		this.name = name;
		this.bitmap = bitmap;
		this.path = path;
		this.isIcon = isIcon;
	}
	public MyFile() {
		super();
	}
	

}
