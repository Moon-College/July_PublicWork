package com.tz.bean;

public class Dish {
	private int dishImg;
	private String foodName;
	private int userImg;
	private String userName;
	public int getDishImg() {
		return dishImg;
	}
	public void setDishImg(int dishImg) {
		this.dishImg = dishImg;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getUserImg() {
		return userImg;
	}
	public void setUserImg(int userImg) {
		this.userImg = userImg;
	}
	public String getUserName() {
		return userName;
	}
	public Dish(int dishImg, String foodName, int userImg, String userName) {
		super();
		this.dishImg = dishImg;
		this.foodName = foodName;
		this.userImg = userImg;
		this.userName = userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
