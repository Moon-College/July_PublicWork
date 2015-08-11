package com.tz.fileexplorer.utils;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtils {

	private static DecimalFormat format;

	static {
		format = new DecimalFormat("###.00");
	}

	public static String getFileSize(File file) {
		if (file == null) {
			return "0B";
		} else {
			long size = file.length();
			return sizeToStr(size);
		}

	}

	/**
	 * 将文件大小转换字符串，如： xxM xxKB
	 * 
	 * @param size
	 * @return
	 */
	public static String sizeToStr(long size) {
		if (size / 1024 < 1) {
			return size + "B";
		} else if (size / (1024 * 1024) < 1) {
			return format.format((size / (1024 * 1.0))) + "KB";
		} else {

			return format.format(size / (1024 * 1024 * 1.0)) + "M";
		}
	}

	/**
	 * 递归删除文件夹下的所有文件
	 * 
	 * @param file
	 */
	public static boolean deleteFile(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFile(f);
				} else {
					f.delete();
				}
			}
		}
		return file.delete();
	}

}
