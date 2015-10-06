package com.tz.orm.bean;

public class Student {

	public int sId;
	public String sName;
	public String sNumber;
	
	public Student(){
		this.sName="sname_"+((int)(Math.random()*1000));
		this.sNumber="snumber_"+((int)(Math.random()*1000));
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsNumber() {
		return sNumber;
	}

	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}

	@Override
	public String toString() {
		return "Student [sId=" + sId + ", sName=" + sName + ", sNumber="
				+ sNumber + "]";
	}
}
