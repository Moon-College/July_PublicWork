package com.tz.volleylrucache;

import android.app.Application;

public class MyApplication extends Application{

	public static MyApplication application;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application=this;
	}
}
