package com.pujie_dome;

public class Ctiy {

	private String name;
	private int icon;
	private String deca;
	private int icon2;
	
	public Ctiy() {
		// TODO Auto-generated constructor stub
	}
	
	public Ctiy(String name, int icon, String deca, int icon2) {
		super();
		this.name = name;
		this.icon = icon;
		this.deca = deca;
		this.icon2 = icon2;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getDeca() {
		return deca;
	}
	public void setDeca(String deca) {
		this.deca = deca;
	}
	public int getIcon2() {
		return icon2;
	}
	public void setIcon2(int icon2) {
		this.icon2 = icon2;
	}

	@Override
	public String toString() {
		return "Ctiy [name=" + name + ", icon=" + icon + ", deca=" + deca
				+ ", icon2=" + icon2 + "]";
	}
	
}