package com.tz.filebrowser.utils;

import android.os.Environment;

public class FileUtil {
	
	/**
	 * �ж��Ƿ����SD��
	 * @return true ���ڣ���֮������
	 */
	public static boolean isExistSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
}
