package com.house.fileexplorer.util;

import java.io.File;
import java.text.DecimalFormat;

import android.os.Environment;

/**
 * 关于文件操作的工具类
 */
public class FileUtils {
	
	private static final double ROUND_KB = 1024.0;
	private static final double ROUND_MB = ROUND_KB * ROUND_KB;
	private static final double ROUND_GB = ROUND_MB * ROUND_KB;
	/**
	 * 判断是否存在SD卡
	 * @return true:存在  false:不存在
	 */
	public static boolean isSDCard(){
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 得到文件(夹)的大小
	 * @param file 要计算的文件（夹）
	 * @return 文件（夹）的大小
	 */
	public static long getFileSize(File file){
		long fileSize = 0;
		if (fileExists(file)) {
			// 如果是文件夹，遍历文件夹获取总大小
			if (file.isDirectory()) {
				File[] listFile = file.listFiles();
				for (File f : listFile) {
					if (f.isDirectory()) {// 如果是文件夹，循环此方法
						fileSize += getFileSize(f);
					} else {
						fileSize += f.length();
					}
				}
			} else {
				fileSize += file.length();
			}
		}
		return fileSize;
	}
	
	/**
	 * 删除文件（夹）
	 * @param path
	 */
	public static void deleteFile(String path) {
		if (fileExists(path)) {
			File file = new File(path);
			if (file.isDirectory()) {// 文件夹
				File[] listFile = file.listFiles();
				for (File f : listFile) {
					if (f.isDirectory()) {
						deleteFile(f.getAbsolutePath());
					} else {
						file.delete();
					}
				}
			} else {
				file.delete();
				System.out.println("删除成功");
			}
		} else {
			System.out.println("文件（夹）不存在");
		}
	}
	
	/**
	 * 递归删除文件和文件夹
	 * 
	 * @param file
	 *            要删除的根目录
	 */
	public static void deleteFile(File file) {
		if (!fileExists(file)) {
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();
					return;
				}
				for (File f : childFile) {
					deleteFile(f);
				}
				file.delete();
			}
		}
	}
	
	/**
	 * 判断文件（夹）是否存在
	 * @param path 文件（夹）路径
	 * @return true：存在 false：不存在
	 */
	public static boolean fileExists(String path){
		
		try{
			File file = new File(path);
			if (!file.exists()) {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 判断文件（夹）是否存在
	 * @param path 文件（夹）路径
	 * @return true：存在 false：不存在
	 */
	public static boolean fileExists(File file){
		try{
			if (!file.exists()) {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 格式化获取到的文件（夹）的大小
	 * @param size 文件（夹）的大小
	 * @return 
	 */
	public static String getFormatSize(long size) {
		String formatSize ;
		// 保留两位小数
		DecimalFormat df = new DecimalFormat("#.##");
		if (size < ROUND_KB) {
			formatSize = df.format(size) + "B";
		} else if (size < ROUND_MB) {
			formatSize = df.format(size / ROUND_KB) + "KB";
		} else if (size < ROUND_GB) {
			formatSize = df.format(size / ROUND_MB) + "MB";
		} else {
			formatSize = df.format(size) + "GB";
		}
		return formatSize;
	}
}
