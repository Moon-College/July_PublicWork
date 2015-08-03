package com.tz.filebrowser.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class SDFile {
	private String name;
	private Bitmap bitmap;
	private File file;
	private boolean isPicture;
	private int count;
	private String fileSize;
	private SoftReference<Bitmap> softReference;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public boolean isPicture() {
		return isPicture;
	}
	public void setPicture(boolean isPicture) {
		this.isPicture = isPicture;
	}
	
	public SoftReference<Bitmap> getSoftReference() {
		return softReference;
	}
	public void setSoftReference(SoftReference<Bitmap> softReference) {
		this.softReference = softReference;
	}
	
	
}
