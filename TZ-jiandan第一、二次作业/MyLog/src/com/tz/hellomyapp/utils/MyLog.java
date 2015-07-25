package com.tz.hellomyapp.utils;

import android.util.Log;

public class MyLog {

	 public  static boolean isDebug;//��־
	
	 /**
	 * INFO��־ 
     * @param tag	��־��ǩ
	 * @param msg ��־��Ϣ
	 * @return int 
	 */
	public static int i(String tag,String msg){
		if(isDebug){//����Ϊtrueʱ�����������־
			return Log.i(tag, msg);
		}		
		return 0; 
	}
	/**
	  * DEBUG��־ 
	 * @param tag	��־��ǩ
	 * @param msg ��־��Ϣ
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
	  * error��־ 
	 * @param tag	��־��ǩ
	 * @param msg ��־��Ϣ
	 * @return int 
	 */
	public static int e(String tag,String msg){
		if(isDebug){
			return Log.e(tag, msg);
		}		
		return 0;
	}
	/**
	  * WARN��־ 
	 * @param tag	��־��ǩ
	 * @param msg ��־��Ϣ
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
	 * �����־��Ϣ
	 * @param bufid ��־����
	 * @param tag	��־��ǩ
	 * @param msg ��־��Ϣ
	 * @return int 
	 */
	public static int println(int bufid,String tag,String msg){
		if(isDebug){ 
			return Log.println(bufid, tag, msg);
		}
		return 0;	
	}
}
