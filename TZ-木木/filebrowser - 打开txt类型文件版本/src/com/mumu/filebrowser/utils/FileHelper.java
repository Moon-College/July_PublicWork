package com.mumu.filebrowser.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.mumu.filebrowser.R;
import com.mumu.filebrowser.bean.FileInfo;

public class FileHelper {
	
	public static List<FileInfo> getDirectionFileList(Context context, String path) {
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		getDirectionFileList(context, path, fileList);
		return fileList;
	}

	public static List<FileInfo> getDirectionFileList(Context context, String path, List<FileInfo> fileList) {
		fileList.clear();
		File[] files = new File(path).listFiles();
		for (File file : files) {
			//不显示系统隐藏文件及文件夹
			if(file.isHidden()) continue;
			
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(file.getName());
			fileInfo.setFilePath(file.getAbsolutePath());
			switch (getFileType(file)) {
			case FileInfo.FILE_TYPE_DIR:
				fileInfo.setType(FileInfo.FILE_TYPE_DIR);
				break;
			case FileInfo.FILE_TYPE_PDF:
				fileInfo.setType(FileInfo.FILE_TYPE_PDF);
				break;
			case FileInfo.FILE_TYPE_DOC:
				fileInfo.setType(FileInfo.FILE_TYPE_DOC);
				break;
			case FileInfo.FILE_TYPE_IMAGE:
				fileInfo.setType(FileInfo.FILE_TYPE_IMAGE);
				break;
			case FileInfo.FILE_TYPE_SOUND:
				fileInfo.setType(FileInfo.FILE_TYPE_SOUND);
				break;
			default:
				fileInfo.setType(FileInfo.FILE_TYPE_UNKNOW);
				break;
			}
			fileList.add(fileInfo);
		}
		
		return fileList;
	}

	public static int getFileType(File file) {
		int type = FileInfo.FILE_TYPE_UNKNOW;
		if (file.isDirectory()) {
			type = FileInfo.FILE_TYPE_DIR;
		} else if (file.getName().toLowerCase().endsWith(".pdf")) {
			type = FileInfo.FILE_TYPE_PDF;
		} else if (file.getName().toLowerCase().endsWith(".jpg")
				|| file.getName().toLowerCase().endsWith(".png")) {
			type = FileInfo.FILE_TYPE_IMAGE;
		} else if (file.getName().toLowerCase().endsWith(".mp3")
				|| file.getName().toLowerCase().endsWith(".wav")) {
			type = FileInfo.FILE_TYPE_SOUND;
		} else if (file.getName().toLowerCase().endsWith(".txt")
				|| file.getName().toLowerCase().endsWith(".doc")) {
			type = FileInfo.FILE_TYPE_DOC;
		}
		return type;
	}

}
