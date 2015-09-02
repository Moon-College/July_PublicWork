package com.tz.lsn6.Util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class SDCardUtils {
	
	/**
	 * 判断SD卡是否可用
	 */
	public static boolean isSDCardExist() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取SD卡根目录
	 * @param mContext
	 * @return
	 */
	public static String getRootFilePath(Context mContext) {
		if (isSDCardExist()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			Toast.makeText(mContext, "SD Card Error!", Toast.LENGTH_SHORT)
					.show();
		}
		return "";
	}
	
}
