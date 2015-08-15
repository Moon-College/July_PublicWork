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
			// {��׺����MIME����}
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
	 * ���ļ�
	 * 
	 * @param file
	 */
	public static void openFile(Context context, File file) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// ����intent��Action����
		intent.setAction(Intent.ACTION_VIEW);
		// ��ȡ�ļ�file��MIME����
		String type = getMIMEType(file);
		// ����intent��data��Type���ԡ�
		intent.setDataAndType(/* uri */Uri.fromFile(file), type);
		// ��ת
		context.startActivity(intent);

	}

	/**
	 * �����ļ���׺����ö�Ӧ��MIME���͡�
	 * 
	 * @param file
	 */
	public static String getMIMEType(File file) {

		String type = "*/*";
		String fName = file.getName();
		// ��ȡ��׺��ǰ�ķָ���"."��fName�е�λ�á�
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* ��ȡ�ļ��ĺ�׺�� */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// ��MIME���ļ����͵�ƥ������ҵ���Ӧ��MIME���͡�
		for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??��������һ�������ʣ����MIME_MapTable��ʲô��
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param file
	 *            Ҫɾ�����ļ�
	 */
	public static boolean deleteFile(File file) {
		if (file == null) {
			return false;
		}
		// ������ļ�ֱ��ɾ��
		if (file.isFile()) {
			return file.delete();
		} else if(file.isDirectory()){
			File[] files=file.listFiles();
			if(files!=null){
				for(File f: files){
					deleteFile(f);//�����������ļ���ɾ��
				}
			}
			return file.delete();
		}else{
			return false;
		}

	}
	
	/**
	 * ��ȡ�ļ���С
	 */
	public static long getFileSize(File file) {
		long size = 0;
		// ������ļ�ֱ��ɾ��
		if (file.exists() && file.isFile()) {
			size += file.length();// ������ļ������ļ��ֽڳ���
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					size += getFileSize(f);// �����������ļ���ɾ��
				}
			}
		} else {
			return 0;
		}
		MyLog.i("jzhao", "size=" + size);
		return size;
	}
	
	/**
	 * �ж��ļ��ı����ʽ
	 * @param filepath
	 * @return �ļ������ʽ
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
	 * ��ȡ�ļ�����
	 * 
	 * @param file
	 * @param code
	 *            �����ʽ
	 * @return String
	 */
	public static String getFileContent(File file, String code) {
		StringBuffer sBuffer = new StringBuffer();
		FileInputStream fInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader in = null;
		try {
			fInputStream = new FileInputStream(file);
			// codeΪ���淽���ﷵ�صı��뷽ʽ
			inputStreamReader = new InputStreamReader(fInputStream, code);
			in = new BufferedReader(inputStreamReader);
			String strTmp = "";
			// ���ж�ȡ
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
	 * ��ȡ�ļ�����
	 * 
	 * @param file
	 * @param code
	 *            �����ʽ
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
			// codeΪ���淽���ﷵ�صı��뷽ʽ
			inputStreamReader = new InputStreamReader(fInputStream, code);
			in = new BufferedReader(inputStreamReader);
			String strTmp = "";
			// ���ж�ȡ
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
	 * ��HttpURLConnection��ȡ�ļ���
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
