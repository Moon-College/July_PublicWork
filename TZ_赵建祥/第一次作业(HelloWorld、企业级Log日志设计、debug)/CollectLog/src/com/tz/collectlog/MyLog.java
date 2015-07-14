package com.tz.collectlog;

import android.util.Log;

/**
 * 对日志类进行控制，控制是否记录日志
 * @author 赵建祥
 */
public class MyLog {

	/**
	 * 日志开关
	 */
	public static boolean ISDEBUG=false;
	
	/**
	 * 打印INFO级别的日志
	 * @param tag 定义日志来源标志
	 * @param msg 日志内容 
	 * @return 
	 */
	public static int i(String tag,String msg){
		if(ISDEBUG){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印ERROR级别的日志
	 * @param tag 定义日志来源标志
	 * @param msg 日志内容 
	 * @return 
	 */
	public static int e(String tag,String msg){
		if(ISDEBUG){
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印WARN级别的日志
	 * @param tag 定义日志来源标志
	 * @param msg 日志内容 
	 * @return 
	 */
	public static int w(String tag,String msg){
		if(ISDEBUG){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	/**
	 * 打印VERBOSE级别的日志
	 * @param tag 定义日志来源标志
	 * @param msg 日志内容 
	 * @return 
	 */
	public static int v(String tag,String msg){
		if(ISDEBUG){
			return Log.v(tag, msg);
		}
		return 0;
	}
}
