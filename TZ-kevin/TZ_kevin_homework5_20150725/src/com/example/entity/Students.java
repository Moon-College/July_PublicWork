package com.example.entity;

public class Students {

	// ͷ��
	private int head;
	// ����
	private String screenNames;
	// �Ա� 0 Ů 1 ��
	private String sex;
	// ��ֵ
	private String faceScore;
	// ����
	private String hobby;

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public String getScreenNames() {
		return screenNames;
	}

	public void setScreenNames(String screenNames) {
		this.screenNames = screenNames;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFaceScore() {
		return faceScore;
	}

	public void setFaceScore(String faceScore) {
		this.faceScore = faceScore;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "Students [head=" + head + ", screenNames=" + screenNames
				+ ", sex=" + sex + ", faceScore=" + faceScore + ", hobby="
				+ hobby + "]";
	}

}
