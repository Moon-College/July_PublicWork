package com.tz.hellomyapp.utils;

import android.util.Log;

public class MyLog {

	 public  static boolean isDebug;//日志
	
	 /**
	 * INFO日志 
     * @param tag	日志标签
	 * @param msg 日志信息
	 * @return int 
	 */
	public static int i(String tag,String msg){
		if(isDebug){//开关为true时，正常输出日志
			return Log.i(tag, msg);
		}		
		return 0; 
	}
	/**
	  * DEBUG日志 
	 * @param tag	日志标签
	 * @param msg 日志信息
	 * @return int 
	 */
	public static int d(String tag,String msg){
		if(isDebug){
			//return Log.d(tag, msg);
			return	MyLog.println(Log.DEBUG, tag, msg);
		}		
		return 0;
	}
	/**
	  * error日志 
	 * @param tag	日志标签
	 * @param msg 日志信息
	 * @return int 
	 */
	public static int e(String tag,String msg){
		if(isDebug){
			return Log.e(tag, msg);
		}		
		return 0;
	}
	/**
	  * WARN日志 
	 * @param tag	日志标签
	 * @param msg 日志信息
	 * @return int 
	 */
	public static int w(String tag,String msg){
		if(isDebug){
//			return Log.w(tag, msg);
			return MyLog.println(Log.WARN, tag, msg);
		}		
		return 0;
	}
	
	/**
	 * 输出日志信息
	 * @param bufid 日志级别
	 * @param tag	日志标签
	 * @param msg 日志信息
	 * @return int 
	 */
	public static int println(int bufid,String tag,String msg){
		if(isDebug){ 
			return Log.println(bufid, tag, msg);
		}
		return 0;	
	}
}
