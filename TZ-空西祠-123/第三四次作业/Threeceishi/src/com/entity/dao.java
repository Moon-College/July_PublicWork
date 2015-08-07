package com.entity;

public class dao {
	private int icon;
	private String name;
	private String sex;
	private String numble;
	public dao(int icon, String name, String sex, String numble, String hoppy) {
		super();
		this.icon = icon;
		this.name = name;
		this.sex = sex;
		this.numble = numble;
		this.hoppy = hoppy;
	}
	private String hoppy;
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
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
	public String getNumble() {
		return numble;
	}
	public void setNumble(String numble) {
		this.numble = numble;
	}
	public String getHoppy() {
		return hoppy;
	}
	public void setHoppy(String hoppy) {
		this.hoppy = hoppy;
	}

}
