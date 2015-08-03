package com.ws.intent;

import android.os.Parcel;
import android.os.Parcelable;

public class MyInFo implements Parcelable {
	private String name;
	private int age;
	private int weight;
	private int height;
	private String dd;

	public MyInFo(String name, int age, int weight, int height, String dd) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.dd = dd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	@Override
	public String toString() {
		return "MyInFo [name=" + name + ", age=" + age + ", weight=" + weight
				+ ", height=" + height + ", dd=" + dd + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		 out.writeString(name);
		 out.writeInt(age);
		 out.writeInt(weight);
		 out.writeInt(height);
		 out.writeString("dd");
	}

	public static final Parcelable.Creator<MyInFo> CREATOR = new Parcelable.Creator<MyInFo>() {
		public MyInFo createFromParcel(Parcel in) {
			return new MyInFo(in);
		}

		public MyInFo[] newArray(int size) {
			return new MyInFo[size];
		}
	};

	private MyInFo(Parcel in) {
		name = in.readString();
		age = in.readInt();
		weight = in.readInt();
		height = in.readInt();
		dd = in.readString();
		
	}

}
