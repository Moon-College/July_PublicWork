package com.example.files;

public class MyFile {
public MyFile() {
		super();
		// TODO Auto-generated constructor stub
	}
public MyFile(String fileName, String path, Long fileSize,
			String createTime, boolean isImage) {
		super();
		this.fileName = fileName;
		this.path = path;
		this.fileSize = fileSize;
		this.createTime = createTime;
		this.isImage = isImage;
	}
private String fileName;
private String path;
private  Long fileSize;
private String createTime;
private boolean isImage;
public boolean isImage() {
	return isImage;
}
public void setImage(boolean isImage) {
	this.isImage = isImage;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public Long getFileSize() {
	return fileSize;
}
public void setFileSize(Long fileSize) {
	this.fileSize = fileSize;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
}
