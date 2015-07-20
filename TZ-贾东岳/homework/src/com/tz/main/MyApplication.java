package com.tz.main;

import android.app.Application;
import android.content.Context;

/**
 * 这里 只用来存放需要用到的静态变量.
 * 
 * @author JDY
 */
public class MyApplication extends Application {
	public static boolean debuger = true;
	public static Context appContext(){
		return getInstance().getAppContext();
	}
	
	private static MyApplication instance;
	private Context context;

	public Context getAppContext(){

		if(context==null){
			// context = App.getInstance().getApplicationContext();
			context=this.getBaseContext();
		}
		return context;
	}

	public static MyApplication getInstance(){
		if(instance==null){
			instance=new MyApplication();
		}
		return instance;
	}

	@Override
	public void onCreate(){
		super.onCreate();
		instance=this;
		context=this.getBaseContext();
	}
}
