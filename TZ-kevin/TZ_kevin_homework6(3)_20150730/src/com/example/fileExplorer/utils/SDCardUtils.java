package com.example.fileExplorer.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function SD���ļ����������
 */
public class SDCardUtils {

	/**
	 * �ж�SD���Ƿ����
	 */
	public static boolean isSDCardExist() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;

	}

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
