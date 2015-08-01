package com.tz.android.bean;

public class Student {
	
	private String nickname;
	private String sex;
	private String yanzhi;
	private String love;
	private int head_img_id;
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
	public String getYanzhi() {
		return yanzhi;
	}
	public void setYanzhi(String yanzhi) {
		this.yanzhi = yanzhi;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	public int getHead_img_id() {
		return head_img_id;
	}
	public void setHead_img_id(int head_img_id) {
		this.head_img_id = head_img_id;
	}
	public Student(String nickname, String sex, String yanzhi, String love,
			int head_img_id) {
		super();
		this.nickname = nickname;
		this.sex = sex;
		this.yanzhi = yanzhi;
		this.love = love;
		this.head_img_id = head_img_id;
	}
	
	
	
	
	
	

}
