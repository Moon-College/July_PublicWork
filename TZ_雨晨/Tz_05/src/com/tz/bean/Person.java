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
	public int gender;// 0�У�1Ů
	public String yan;
	public String interest;
}
