package com.tz.fileexplorer.utils;

import java.io.File;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.BaseFile;

public class FileReader {

	public static <E extends BaseFile> List<E> readFile(final Context c,
			final File[] dirs,  List<E> fileList, Type type,
			String[] suffixs) {
		for (int i = 0; dirs != null && i < dirs.length; i++) {
			String fileName = dirs[i].getName().toLowerCase(Locale.ENGLISH);
			// 判读是否文件以及文件后缀名
			if (dirs[i].isFile()) {

				int resId = 0;
				switch (type) {
				case MUSIC:
					for (int j = 0; suffixs != null && j < suffixs.length; j++) {
						if (fileName.endsWith(suffixs[j])) {
							Bitmap bm = BitmapCache.getBitmap(c.getResources(), R.drawable.music);
							E file = (E) new BaseFile(dirs[i].getName(),
									dirs[i].getAbsolutePath(), bm, true);
							fileList.add(file);
						}
					}
//					fileList  =setFileIcon(R.drawable.music, c, suffixs, fileName, dirs, resId, fileList);
//					setFileIcon(R.drawable.music, c, suffixs, fileName, dirs,
//							resId, fileList);
					break;
				case MEDIA:
					for (int j = 0; suffixs != null && j < suffixs.length; j++) {
						if (fileName.endsWith(suffixs[j])) {
							Bitmap bm = BitmapCache.getBitmap(c.getResources(), R.drawable.media);
							E file = (E) new BaseFile(dirs[i].getName(),
									dirs[i].getAbsolutePath(), bm, true);
							fileList.add(file);
						}
					}
					break;
				case IMAGE:
					for (int j = 0; suffixs != null && j < suffixs.length; j++) {
						if (fileName.endsWith(suffixs[j])) {
							Bitmap bm = BitmapCache.getBitmap(c.getResources(), -1);
							E file = (E) new BaseFile(dirs[i].getName(),
									dirs[i].getAbsolutePath(), bm, true);
							fileList.add(file);
						}
					}
					break;
				case DOCUMENT:
					for (int j = 0; suffixs != null && j < suffixs.length; j++) {
						if (fileName.endsWith(suffixs[j])) {
							Bitmap bm = BitmapCache.getBitmap(c.getResources(), R.drawable.document);
							E file = (E) new BaseFile(dirs[i].getName(),
									dirs[i].getAbsolutePath(), bm, true);
							fileList.add(file);
						}
					}
					break;
				case ZIP:
					
					break;
				case APK:
//					Drawable apkIcon = getApkIcon(c, dirs[i].getAbsolutePath());
					for (int j = 0; suffixs != null && j < suffixs.length; j++) {
						if (fileName.endsWith(suffixs[j])) {
//							Bitmap bm = ((BitmapDrawable)apkIcon).getBitmap();
							Bitmap bm = BitmapCache.getBitmap(c.getResources(),R.drawable.emo_im_surprised);
							E file = (E) new BaseFile(dirs[i].getName(),
									dirs[i].getAbsolutePath(), bm, true);
							fileList.add(file);
						}
					}
					break;
				case FAVOURITE:
					break;
				case BLUETOOTH:
					break;
				case LOG:
					break;
				case DEFAULT:
					break;
				}
			}

			// 这里应如何加快速度捏
			else if (dirs[i].isDirectory()) {
				final File[] newFileList = new File(dirs[i].getAbsolutePath())
						.listFiles();
				readFile(c, newFileList, fileList, type, suffixs);
			}
		}
		return fileList;
	}

	
	 public static Drawable getApkIcon(Context context, String apkPath) {
	        PackageManager pm = context.getPackageManager();
	        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
	                PackageManager.GET_ACTIVITIES);
	        if (info != null) {
	            ApplicationInfo appInfo = info.applicationInfo;
	            appInfo.sourceDir = apkPath;
	            appInfo.publicSourceDir = apkPath;
	            try {
	                return appInfo.loadIcon(pm);
	            } catch (OutOfMemoryError e) {
	                Log.e("FileReader", e.toString());
	            }
	        }
	        return null;
	    }
//	@SuppressWarnings("unchecked")
//	private static <E extends BaseFile> List<E>  setFileIcon(int resId, Context c,
//			String[] suffixs, String fileName, File[] dirs, int i,
//			List<E> fileList) {
//		for (int j = 0; suffixs != null && j < suffixs.length; j++) {
//			if (fileName.endsWith("mp3")) {
//				System.out.println(fileName);
//				Bitmap bm = BitmapCache.getBitmap(c.getResources(), resId);
//				E file = (E) new BaseFile(dirs[i].getName(),
//						dirs[i].getAbsolutePath(), bm, true);
//				fileList.add(file);
//			}
//		}
//		return fileList;
//	}

	public static enum Type {
		MUSIC, MEDIA, IMAGE, DOCUMENT, ZIP, APK, FAVOURITE, BLUETOOTH, LOG, DEFAULT
	}

	// public static <E extends MyFile> List<E> readFile(final Context c,final
	// File[] dirs,
	// final List<E> fileList) {
	// // System.out.println(Thread.currentThread().getName());
	// for (int i = 0; dirs != null && i < dirs.length; i++) {
	// String fileName = dirs[i].getName().toLowerCase(Locale.ENGLISH);
	// // 判读是否文件以及文件后缀名
	// if (dirs[i].isFile()) {
	//
	// E file = null;
	// if(fileName.endsWith("jpg")||fileName.endsWith("png")){
	// // file = (E) new MyFile(dirs[i].getName(), dirs[i].getAbsolutePath(),
	// null, false);
	// }
	// else if(fileName.endsWith("cfg")){
	// Bitmap bm = BitmapFactory.decodeResource(c.getResources(),
	// R.drawable.file);
	// file = (E) new MyFile(dirs[i].getName(), dirs[i].getAbsolutePath(), bm,
	// true);
	// System.out.println(file);
	// fileList.add(file);
	// }
	// // fileList.add(file[i].toString());
	//
	// }
	// // 如果是文件夹，递归扫描
	// else if (dirs[i].isDirectory()) {
	//
	// final File[] newFileList = new File(dirs[i].getAbsolutePath())
	// .listFiles();
	// // readFile(newFileList);
	//
	// // 通过多线程来加速
	// new Thread(new Runnable() {
	// public void run() {
	// readFile(c,newFileList, fileList);
	// }
	// }).start();
	// }
	// }
	// // dialog.dismiss();
	// return fileList;
	// }
}
