package com.tz.customlistview.bean;

/**
 * ����VIP
 * @author zhao_jx
 *
 */
public class JulyVip {
	//ͬѧ��ͷ��+����+�Ա�+��ֵ+����
	private int faceImgId;
	private String netName;
	private String sex;
	private String faceScore;//��ֵ
	private String hobby;//����
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
