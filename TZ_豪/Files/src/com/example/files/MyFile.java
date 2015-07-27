package com.example.files;

public class MyFile {
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
