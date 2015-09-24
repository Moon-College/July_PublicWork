package com.tz.userlogin.util;

public class StringUtil {

	public static boolean equals(String str1,String str2){
		if(str1==null||str2==null)
			return false;
		if(str1.equalsIgnoreCase(str2)){
			return true;
		} else {
			return false;
		}
	}
}
