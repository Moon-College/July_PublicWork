package com.tz.bean;

public class MemberInfo {
	private String name;
	private String sex;
	private String face;
	private String hobby;
	private int imgId;
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public MemberInfo(String name, String sex, String face, String hobby,int imgId) {
		super();
		this.name = name;
		this.sex = sex;
		this.face = face;
		this.hobby = hobby;
		this.imgId = imgId;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
