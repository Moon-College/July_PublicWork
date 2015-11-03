package com.tz.volleylrucache.util;

import java.io.File;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.tz.volleylrucache.MyApplication;

public class ImageLoadManager {

	// 1024*1024*((ActivityManager)MyApplication.application.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass()/8;
	public static int MAX_LRU_CACHE = 1024 * 1024 * ((ActivityManager) MyApplication.application
			.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

	public static int MAX_DISK_CACHE = 1024*1024*30;//外部存储弄个30M

	public static File DISK_FILE;

	private static ImageLoader imageLoader;//必须一个对象呀，因为其内部是linkedMap

	public static ImageLoader createImageLoader(int diskMaxSize,
			int memoryMaxSize) {
		if(imageLoader!=null)
			return imageLoader;
		if (diskMaxSize > 0)
			MAX_DISK_CACHE = diskMaxSize;
		
		if (memoryMaxSize > 0)
			MAX_LRU_CACHE = memoryMaxSize;
		
		Log.i("jzhao", "MAX_LRU_CACHE"+MAX_LRU_CACHE);
		// 创建
		DISK_FILE = getDiskCacheDir(MyApplication.application, "mylrucache");
		imageLoader = new ImageLoader(
				LruRequestQueueManager.requestQueue, new LruImageCache(
						DISK_FILE, MAX_DISK_CACHE, MAX_LRU_CACHE));
		return imageLoader;
	}

	private static File getDiskCacheDir(Context context, String appLabel) {
		String path;// /mnt/sdcard/cache/appLabel
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// sd卡存储（/mnt/sdcard/cache）
			path = context.getExternalCacheDir().getPath();
		} else {
			// 没有SD卡，缓存到系统存储
			path = context.getCacheDir().getPath();
		}
		return new File(path + File.separator + appLabel);
	}

	/**
	 * 带自定义的LRU缓存大小的图片加载
	 * 
	 * @param listener
	 * @return
	 */
	public static Bitmap loadImageByVolleyAndLru(String url,
			ImageListener listener, int maxdiscache,int maxlrucache) {

		ImageLoader imageLoader = createImageLoader(maxdiscache,maxlrucache);
		ImageContainer imageContainer = imageLoader.get(url, listener);
		if (imageContainer != null) {
			return imageContainer.getBitmap();
		}
		return null;
	}

	/**
	 * 使用默认的LRU缓存大小的图片加载
	 * 
	 * @param listener
	 * @return
	 */
	public static Bitmap loadImageByVolleyAndLru(String url,
			ImageListener listener) {
		return loadImageByVolleyAndLru(url, listener,0, 0);
	}
}
