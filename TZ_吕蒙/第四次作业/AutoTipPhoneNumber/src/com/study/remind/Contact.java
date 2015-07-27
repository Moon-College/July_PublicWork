package com.study.remind;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Contact {
	
	private String name;
	
	private String phoneNum;
	private Bitmap image;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	
	public static ArrayList<Contact> generateContact(){
		ArrayList<Contact> contacts= new ArrayList<Contact>();
		
		for(int i=0;i<10;i++){
			Contact contact =new Contact();
			contact.name="Small Qiao"+i;
			contact.phoneNum="185XXXXXX"+i;
			contacts.add(contact);
		}
		
		return contacts;
	}

}
