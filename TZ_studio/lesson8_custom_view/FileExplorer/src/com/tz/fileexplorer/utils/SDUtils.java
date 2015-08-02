package com.tz.fileexplorer.utils;

import java.io.File;

import android.os.Environment;

public class SDUtils {

	/**
	 * 此方法描述的是：SD卡是否可用
	 * @author:  studio
	 * @最后修改人： studio
	 * SDAvaliable
	 * @return boolean
	 */
	public static boolean SDAvaliable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}
	/**
	 * 此方法描述的是：得到SD卡路径
	 * @author:  Sky	 
	 * getSDPath
	 * @return String
	 */
	public static String getSDPath(){
		String SDPath="";
		if (SDAvaliable()) {
			SDPath=Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return SDPath;
	}

}
