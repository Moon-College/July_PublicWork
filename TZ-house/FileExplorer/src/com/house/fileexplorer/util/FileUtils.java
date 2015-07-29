package com.house.fileexplorer.util;

import java.io.File;
import java.text.DecimalFormat;

import android.os.Environment;

/**
 * �����ļ������Ĺ�����
 */
public class FileUtils {
	
	private static final double ROUND_KB = 1024.0;
	private static final double ROUND_MB = ROUND_KB * ROUND_KB;
	private static final double ROUND_GB = ROUND_MB * ROUND_KB;
	/**
	 * �ж��Ƿ����SD��
	 * @return true:����  false:������
	 */
	public static boolean isSDCard(){
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}
	
	/**
	 * �õ��ļ�(��)�Ĵ�С
	 * @param file Ҫ������ļ����У�
	 * @return �ļ����У��Ĵ�С
	 */
	public static long getFileSize(File file){
		long fileSize = 0;
		if (fileExists(file)) {
			// ������ļ��У������ļ��л�ȡ�ܴ�С
			if (file.isDirectory()) {
				File[] listFile = file.listFiles();
				for (File f : listFile) {
					if (f.isDirectory()) {// ������ļ��У�ѭ���˷���
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
	 * ɾ���ļ����У�
	 * @param path
	 */
	public static void deleteFile(String path) {
		if (fileExists(path)) {
			File file = new File(path);
			if (file.isDirectory()) {// �ļ���
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
				System.out.println("ɾ���ɹ�");
			}
		} else {
			System.out.println("�ļ����У�������");
		}
	}
	
	/**
	 * �ݹ�ɾ���ļ����ļ���
	 * 
	 * @param file
	 *            Ҫɾ���ĸ�Ŀ¼
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
	 * �ж��ļ����У��Ƿ����
	 * @param path �ļ����У�·��
	 * @return true������ false��������
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
	 * �ж��ļ����У��Ƿ����
	 * @param path �ļ����У�·��
	 * @return true������ false��������
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
	 * ��ʽ����ȡ�����ļ����У��Ĵ�С
	 * @param size �ļ����У��Ĵ�С
	 * @return 
	 */
	public static String getFormatSize(long size) {
		String formatSize ;
		// ������λС��
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
