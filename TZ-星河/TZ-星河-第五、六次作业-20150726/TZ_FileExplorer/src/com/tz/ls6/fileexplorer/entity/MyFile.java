package com.tz.ls6.fileexplorer.entity;

import java.io.Serializable;

import android.graphics.Bitmap;

/**
 * �ļ�����,��װ�ļ��Ļ�����Ϣ
 * 
 * @author GWP
 * 
 */
public class MyFile implements Serializable {
	/**
	 * ��ǰ�ļ���·��
	 */
	private String path;
	/**
	 * ��ǰ�ļ���ͼ�꣬�ļ��У������ļ���ͼƬ�ļ��Ĳ�ͬ
	 */
	private Bitmap icon;
	/**
	 * �ļ���
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
