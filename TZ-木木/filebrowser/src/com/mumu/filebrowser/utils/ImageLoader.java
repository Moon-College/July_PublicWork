package com.mumu.filebrowser.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageLoader {
	
	private static ImageLoader mImageLoader = null;
	private LruCache<String, Bitmap> mLruCache;

	private ImageLoader() {
		//获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		//给LruCache分配1/8 4M
		int maxSize = maxMemory / 8;
		mLruCache = new LruCache<String, Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				//必须重写此方法，来测量Bitmap的大小
				//每一行像素占用字节数*行数
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}
	
	/**
	 * 通过单例模式获取ImageLoader
	 */
	public static ImageLoader getInstance(){
		if(mImageLoader == null){
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	/**
	 * 从Cache中取得Bitmap对象
	 */
	public Bitmap getBitmapFromCache(String key){
		return mLruCache.get(key);
	}
	
	/**
	 * 将bitmap放至cache缓存中
	 */
	public void putBitmapToCache(String key, Bitmap bitmap){
		if(getBitmapFromCache(key) == null && bitmap != null){
			mLruCache.put(key, bitmap);
		}
	}

}
