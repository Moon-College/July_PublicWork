package com.tz.customlistview.bean;

/**
 * 七月VIP
 * @author zhao_jx
 *
 */
public class JulyVip {
	//同学的头像+网名+性别+颜值+爱好
	private int faceImgId;
	private String netName;
	private String sex;
	private String faceScore;//颜值
	private String hobby;//爱好
	public synchronized int getFaceImgId() {
		return faceImgId;
	}
	public synchronized void setFaceImgId(int faceImgId) {
		this.faceImgId = faceImgId;
	}
	public synchronized String getNetName() {
		return netName;
	}
	public synchronized void setNetName(String netName) {
		this.netName = netName;
	}
	public synchronized String getSex() {
		return sex;
	}
	public synchronized void setSex(String sex) {
		this.sex = sex;
	}
	public synchronized String getFaceScore() {
		return faceScore;
	}
	public synchronized void setFaceScore(String faceScore) {
		this.faceScore = faceScore;
	}
	public synchronized String getHobby() {
		return hobby;
	}
	public synchronized void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
}
