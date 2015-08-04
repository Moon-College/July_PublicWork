package com.tz.filebrowser.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.Log;

public class ImageLoader {
	
	private static ImageLoader mImageLoader;
	private LruCache<String, Bitmap> mMemoryCache;
	private ImageLoader() {
		
		long memory = Runtime.getRuntime().maxMemory();
		int maxSize = (int)(memory / 8);
		mMemoryCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight() / 1024;
			}
			
		};
	}
	
	public static ImageLoader getNewInstance() {
		if(mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	
	/**
	 * 从指定的路径中加载图片 。
	 * @param path 图片路径 
	 * @param width 图片宽义
	 * @return
	 */
	public Bitmap getBitmapWithPath(String path,int width) {
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// 获取图片 的宽高
		int bitmapWidth = options.outWidth;
		int bitmapHeight = options.outHeight;
		
		int height = (width * bitmapHeight) / bitmapWidth;
		
		// 计算图片 压缩比
		int inSampleSize = Math.round((bitmapWidth*1.0f / width + bitmapHeight * 1.0f / height)/2);
		Log.d("ImageLoader", "width = " + width + ",height = " + height + ", inSampleSize = " + inSampleSize);
		
		options.inSampleSize = inSampleSize;
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}
	
	/**
	 * 将图片 添加到内存缓存中
	 * @param path 图片路径 
	 * @param bitmap 图片 
	 */
	public void addBitmapToMemoryCache(String path, Bitmap bitmap) {
		mMemoryCache.put(path, bitmap);
	}
	
	/**
	 * 根据图片路径从内存缓存 中获取图片
	 * @param path 图片路径
	 * @return
	 */
	public Bitmap getBitmapFromMemoryCache(String path) {
		return mMemoryCache.get(path);
	}
	
}
