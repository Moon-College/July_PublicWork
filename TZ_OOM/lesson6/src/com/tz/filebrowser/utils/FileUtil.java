package com.tz.filebrowser.utils;

import android.os.Environment;

public class FileUtil {
	
	/**
	 * 判断是否存在SD卡
	 * @return true 存在，反之不存在
	 */
	public static boolean isExistSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
}
