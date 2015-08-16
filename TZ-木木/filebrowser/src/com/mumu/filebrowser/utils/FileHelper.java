package com.mumu.filebrowser.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
				//fileInfo.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file_type_folder));
				fileInfo.setType(FileInfo.FILE_TYPE_DIR);
				break;
			case FileInfo.FILE_TYPE_PDF:
				//fileInfo.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file_type_pdf));
				fileInfo.setType(FileInfo.FILE_TYPE_PDF);
				break;
			case FileInfo.FILE_TYPE_IMAGE:
				//fileInfo.setBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
				//fileInfo.setBitmap(null);
				fileInfo.setType(FileInfo.FILE_TYPE_IMAGE);
				break;
			case FileInfo.FILE_TYPE_SOUND:
				//fileInfo.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file_type_audio));
				fileInfo.setType(FileInfo.FILE_TYPE_SOUND);
				break;
			default:
				//fileInfo.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file_type_default));
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
		}
		return type;
	}

}
