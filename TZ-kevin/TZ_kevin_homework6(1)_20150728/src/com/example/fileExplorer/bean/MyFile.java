package com.example.fileExplorer.bean;

import android.graphics.Bitmap;
/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function �ļ���
 */
public class MyFile {
	// ͼ��
	private Bitmap fileIcon;
	// �ļ���
	private String fileName;
	// ·��
	private String filePath;
	// ���ļ��ǲ���ͼƬ
	private boolean isPicture;

	public MyFile() {

	}

	public MyFile(Bitmap fileIcon, String fileName, String filePath,
			boolean isPicture) {
		super();
		this.fileIcon = fileIcon;
		this.fileName = fileName;
		this.filePath = filePath;
		this.isPicture = isPicture;
	}

	public Bitmap getFileIcon() {
		return fileIcon;
	}

	public void setFileIcon(Bitmap fileIcon) {
		this.fileIcon = fileIcon;
	}

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

	public boolean isPicture() {
		return isPicture;
	}

	public void setPicture(boolean isPicture) {
		this.isPicture = isPicture;
	}

	@Override
	public String toString() {
		return "MyFile [fileIcon=" + fileIcon + ", fileName=" + fileName
				+ ", fileString=" + filePath + ", isPicture=" + isPicture + "]";
	}

}
