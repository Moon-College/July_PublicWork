package com.tz.qqlayout.bean;

public class QQPlugin {

	private int imageId;//图片资源ID
	private String name;//插件名称
	private int redirectImageId;//跳转图片
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRedirectImageId() {
		return redirectImageId;
	}
	public void setRedirectImageId(int redirectImageId) {
		this.redirectImageId = redirectImageId;
	}
	
}
