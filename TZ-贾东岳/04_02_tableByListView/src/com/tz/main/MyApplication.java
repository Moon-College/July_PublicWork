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
	
	/**比较传入的两个对象是否是同一个类的对象.
	 * 因为instandof会把父类也算上所以有了这个方法。
	 * @param class1
	 * @param class2
	 * @return 如果两个参数均不为空且为同一个类的对象则返回true，否则返回false。
	 */
	public static boolean isTheSameClass(Object class1,Object class2){
		if (class1==null|class2 == null) {
			return false;
		}
		return class1.getClass().equals(class2.getClass());
	}
	/**比较传入的两个对象是否是同一个类的对象.
	 * 因为instandof会把父类也算上所以有了这个方法。
	 * @param class1
	 * @param class2
	 * @return 如果两个参数均不为空且为同一个类的对象则返回true，否则返回false。
	 */
	public static boolean isTheSameClassWhen(Object class1,Object class2){
		if (class1==null|class2 == null) {
			return false;
		}
		return class1.getClass().equals(class2.getClass());
	}
}
