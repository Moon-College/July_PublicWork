package com.tz.handlerloadremoteresource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class FileUtil {

	public static final String[][] MIME_MapTable = {
			// {后缀名，MIME类型}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/html" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

	/**
	 * 打开文件
	 * 
	 * @param file
	 */
	public static void openFile(Context context, File file) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置intent的Action属性
		intent.setAction(Intent.ACTION_VIEW);
		// 获取文件file的MIME类型
		String type = getMIMEType(file);
		// 设置intent的data和Type属性。
		intent.setDataAndType(/* uri */Uri.fromFile(file), type);
		// 跳转
		context.startActivity(intent);

	}

	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * 
	 * @param file
	 */
	public static String getMIMEType(File file) {

		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            要删除的文件
	 */
	public static boolean deleteFile(File file) {
		if (file == null) {
			return false;
		}
		// 如果是文件直接删除
		if (file.isFile()) {
			return file.delete();
		} else if(file.isDirectory()){
			File[] files=file.listFiles();
			if(files!=null){
				for(File f: files){
					deleteFile(f);//迭代调用子文件的删除
				}
			}
			return file.delete();
		}else{
			return false;
		}

	}
	
	/**
	 * 获取文件大小
	 */
	public static long getFileSize(File file) {
		long size = 0;
		// 如果是文件直接删除
		if (file.exists() && file.isFile()) {
			size += file.length();// 如果是文件返回文件字节长度
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					size += getFileSize(f);// 迭代调用子文件的删除
				}
			}
		} else {
			return 0;
		}
		MyLog.i("jzhao", "size=" + size);
		return size;
	}
	
	/**
	 * 判断文件的编码格式
	 * @param filepath
	 * @return 文件编码格式
	 * @throws Exception
	 */
	public static String codeString(String filepath) throws Exception{
		BufferedInputStream bin = new BufferedInputStream(
		new FileInputStream(filepath));
		int p = (bin.read() << 8) + bin.read();
		String code = null;
		
		switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
			default:
				code = "GBK";
		}
		
		return code;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param file
	 * @param code
	 *            编码格式
	 * @return String
	 */
	public static String getFileContent(File file, String code) {
		StringBuffer sBuffer = new StringBuffer();
		FileInputStream fInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		try {
			fInputStream = new FileInputStream(file);
			// code为上面方法里返回的编码方式
			inputStreamReader = new InputStreamReader(fInputStream, code);
			in = new BufferedReader(inputStreamReader);
			String strTmp = "";
			// 按行读取
			while ((strTmp = in.readLine()) != null) {
				sBuffer.append(strTmp + "\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (fInputStream != null) {
					fInputStream.close();
				}
			} catch (Exception e) {

			}
		}

		return sBuffer.toString();

	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param file
	 * @param code
	 *            编码格式
	 * @return String
	 */
	public static String getFileContent(File file) {
		StringBuffer sBuffer = new StringBuffer();
		FileInputStream fInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		try {
			fInputStream = new FileInputStream(file);
			String code=codeString(file.getAbsolutePath());
			// code为上面方法里返回的编码方式
			inputStreamReader = new InputStreamReader(fInputStream, code);
			in = new BufferedReader(inputStreamReader);
			String strTmp = "";
			// 按行读取
			while ((strTmp = in.readLine()) != null) {
				sBuffer.append(strTmp + "\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (fInputStream != null) {
					fInputStream.close();
				}
			} catch (Exception e) {
				
			}
		}
		
		return sBuffer.toString();
		
	}
	
	/**
	 * 从HttpURLConnection获取文件名
	 * @param conn
	 */
	public static String getFileNameFromHttpURLConnection(HttpURLConnection conn){
		String file = conn.getHeaderField(6);
		file = file.substring(file.lastIndexOf('/') + 1);
		String[] contents=conn.getHeaderField("Content-Disposition").split(";");
		if(contents!=null&&contents.length>0){
			for(String content:contents){
				if(content.contains("filename=")){
					file=content.replace("filename=", "");
					break;
				}
			}
		}
		return file;
	}

}
