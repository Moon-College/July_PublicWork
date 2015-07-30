package com.tz.fileexplorer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

/**
 * 常量类
 * 
 * @author 赵建祥 QQ:451966221 DATE:2015/7/9
 */
public class MyConstant {

	public static final String MY_TAG = "jzhao";
	// 内置SD卡绝对路径
	public static final String INNER_SD_ABSOLUTE_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	/**
	 * 获取外置SD卡路径
	 * 
	 * @return 应该就一条记录或空
	 */
	public static List<String> getExtSDCardPath() {
		List<String> lResult = new ArrayList<String>();
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("mount");
			InputStream is = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("extSdCard")) {
					String[] arr = line.split(" ");
					String path = arr[1];
					File file = new File(path);
					if (file.isDirectory()) {
						lResult.add(path);
					}
				}
			}
			isr.close();
		} catch (Exception e) {
		}
		return lResult;
	}

	/**
	 * 获取一个SD卡路径
	 * 
	 * @return 应该就一条记录或空
	 */
	public static String getOneExtSDCardPath() {
		String sdcard_path = null;
		String sd_default = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		if (sd_default.endsWith("/")) {
			sd_default = sd_default.substring(0, sd_default.length() - 1);
		}
		// 得到路径
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("mount");
			InputStream is = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			String line;
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line.contains("secure"))
					continue;
				if (line.contains("asec"))
					continue;
				if (line.contains("fat") && line.contains("/mnt/")) {
					String columns[] = line.split(" ");
					if (columns != null && columns.length > 1) {
						if (sd_default.trim().equals(columns[1].trim())) {
							continue;
						}
						sdcard_path = columns[1];
					}
				} else if (line.contains("fuse") && line.contains("/mnt/")) {
					String columns[] = line.split(" ");
					if (columns != null && columns.length > 1) {
						if (sd_default.trim().equals(columns[1].trim())) {
							continue;
						}
						sdcard_path = columns[1];
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdcard_path;
	}
}
