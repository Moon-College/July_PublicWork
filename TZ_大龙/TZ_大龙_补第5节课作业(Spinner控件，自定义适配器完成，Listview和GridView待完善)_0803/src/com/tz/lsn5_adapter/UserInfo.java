package com.tz.lsn5_adapter;


public class UserInfo {
	public int imgId; //Í·ÏñID
	public String name;
	public String nickname;
	public String sex;
	int faceValue;
	public String hobby;
	
	public UserInfo(int imgId, String name, String nickname, String sex,
			int faceValue, String hobby) {
		super();
		this.imgId = imgId;
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.faceValue = faceValue;
		this.hobby = hobby;
	}
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	
	
	
}
