package com.tz.fileexplorer.util;

import android.util.Log;

public class MyLog {

	public static boolean ISDEBUG=true;
	
	public static int v(String tag,String msg){
		if(ISDEBUG){
			return Log.v(tag, msg);
		}
		return 0;
	}
	
	public static int i(String tag,String msg){
		if(ISDEBUG){
			return Log.i(tag, msg);
		}
		return 0;
	}
	
	public static int d(String tag,String msg){
		if(ISDEBUG){
			return Log.d(tag, msg);
		}
		return 0;
	}

	public static int e(String tag,String msg){
		if(ISDEBUG){
			return Log.e(tag, msg);
		}
		return 0;
	}
	
	public static int w(String tag,String msg){
		if(ISDEBUG){
			return Log.w(tag, msg);
		}
		return 0;
	}
	
}
