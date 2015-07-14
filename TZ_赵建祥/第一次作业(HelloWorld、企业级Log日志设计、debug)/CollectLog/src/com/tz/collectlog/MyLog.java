package com.tz.collectlog;

import android.util.Log;

/**
 * ����־����п��ƣ������Ƿ��¼��־
 * @author �Խ���
 */
public class MyLog {

	/**
	 * ��־����
	 */
	public static boolean ISDEBUG=false;
	
	/**
	 * ��ӡINFO�������־
	 * @param tag ������־��Դ��־
	 * @param msg ��־���� 
	 * @return 
	 */
	public static int i(String tag,String msg){
		if(ISDEBUG){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	/**
	 * ��ӡERROR�������־
	 * @param tag ������־��Դ��־
	 * @param msg ��־���� 
	 * @return 
	 */
	public static int e(String tag,String msg){
		if(ISDEBUG){
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	/**
	 * ��ӡWARN�������־
	 * @param tag ������־��Դ��־
	 * @param msg ��־���� 
	 * @return 
	 */
	public static int w(String tag,String msg){
		if(ISDEBUG){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
	/**
	 * ��ӡVERBOSE�������־
	 * @param tag ������־��Դ��־
	 * @param msg ��־���� 
	 * @return 
	 */
	public static int v(String tag,String msg){
		if(ISDEBUG){
			return Log.v(tag, msg);
		}
		return 0;
	}
}
