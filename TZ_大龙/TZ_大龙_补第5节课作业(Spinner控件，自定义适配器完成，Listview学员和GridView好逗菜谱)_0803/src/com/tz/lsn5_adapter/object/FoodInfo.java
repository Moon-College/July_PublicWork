package com.tz.lsn5_adapter.object;


public class FoodInfo {
	String foodName;
	int foodImgId;
	int count;
	String des;
	int userImgId;
	String userName;
	public FoodInfo(String foodName, int foodImgId, int count, String des,
			int userImgId, String userName) {
		super();
		this.foodName = foodName;
		this.foodImgId = foodImgId;
		this.count = count;
		this.des = des;
		this.userImgId = userImgId;
		this.userName = userName;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodImgId() {
		return foodImgId;
	}
	public void setFoodImgId(int foodImgId) {
		this.foodImgId = foodImgId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getUserImgId() {
		return userImgId;
	}
	public void setUserImgId(int userImgId) {
		this.userImgId = userImgId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
