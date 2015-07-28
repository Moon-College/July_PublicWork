package com.mumu.filebrowser.bean;

import android.graphics.Bitmap;

public class FileInfo {
	public final static int FILE_TYPE_UNKNOW = 0;
	public final static int FILE_TYPE_DIR = 1;
	public final static int FILE_TYPE_IMAGE = 2;
	public final static int FILE_TYPE_PDF = 3;
	public final static int FILE_TYPE_SOUND = 4;
	
	private String fileName;
	private String filePath;
	private Bitmap bitmap;
    private int type;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
    
    
}
