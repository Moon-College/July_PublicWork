package com.tz.orm.bean;

public class Product {

	private int pId;
	private String pName;
	private float pPrice;
	
	public Product(){
		this.pName="pname_"+((int)(Math.random()*1000));
		this.pPrice=(int)(Math.random()*1000);
	}
	
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public float getpPrice() {
		return pPrice;
	}
	public void setpPrice(float pPrice) {
		this.pPrice = pPrice;
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pName=" + pName + ", pPrice="
				+ pPrice + "]";
	}
}
