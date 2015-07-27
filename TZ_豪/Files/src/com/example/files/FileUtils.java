package com.example.files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Environment;
import android.os.StatFs;

public class FileUtils {
	/**
	 * 判断sdCard是否存在
	 * 
	 * @return
	 */
	public static boolean hasSDCard() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			return true;
		}
		return false;
	}

	/**
	 * 获取sd卡存储路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	public static List<MyFile> getSDCardDirectory(String path) {
		File file = new File(path);
		List<MyFile> list = new ArrayList<MyFile>();
		for (File files : file.listFiles()) {
			MyFile myFile = new MyFile();
			String fileName = files.getName();
			myFile.setFileName(fileName);
			if (files.isDirectory()) {
				myFile.setImage(false);
			} else {
				if (fileName.toLowerCase().endsWith(".png")
						|| fileName.toLowerCase().endsWith(".jpg")
						|| fileName.toLowerCase().endsWith(".jpeg")) {
					myFile.setImage(true);
				}
			}
			myFile.setPath(files.getAbsolutePath());
			myFile.setFileSize(files.length() / 1024);// M
			myFile.setCreateTime(getFileCreateTime(files.lastModified()));
			list.add(myFile);
		}
		return list;

	}

	/**
	 * 获取文件大小
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static long getSDFreeSize(String path) {
		StatFs sf = new StatFs(path);

		return sf.getBlockSize() / 1024;

	}

	/**
	 * 获取文件创建时间
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String getFileCreateTime(long time) {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(new Date(time));

	}
}
