package com.tz.bean;

public class Person {

	public Person() {
		super();
	}

	public Person(int iconId, String nickName, int gender, String yan,
			String interest) {
		super();
		this.iconId = iconId;
		this.nickName = nickName;
		this.gender = gender;
		this.yan = yan;
		this.interest = interest;
	}

	public int iconId;
	public String nickName;
	public int gender;// 0ÄÐ£¬1Å®
	public String yan;
	public String interest;
}
