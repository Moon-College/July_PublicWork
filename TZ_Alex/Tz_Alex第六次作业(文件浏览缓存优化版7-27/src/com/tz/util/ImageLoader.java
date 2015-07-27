package com.tz.util;

import android.R.anim;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {

	private static ImageLoader mImageLoader;
	private static LruCache< String, Bitmap> lruCache;
	/**
	 * 计算lrucache的缓存大小
	 */
	public ImageLoader() {
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory / 8;
		lruCache = new LruCache<String,Bitmap>(maxSize){
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes()*value.getHeight()/1024;
			};
		};
	}
	
	/**
	 * 将图片添加至缓存中
	 * @param path
	 */
	public static void addBitmapToMemeory(String path,Bitmap bitmap){
		if(getBitmapFromMemory(path)==null){
			lruCache.put(path, bitmap);
		}
	}
	
	/**
	 * 从缓存中得到已缓存的图片
	 * @param path
	 * @return  缓冲中的图片
	 */
	public static Bitmap getBitmapFromMemory(String path){
		return lruCache.get(path);
	}
	
	//避免ImageLoader对象重复生成，应用单例模式
	public static ImageLoader getInstance(){
		if(mImageLoader==null){
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	
	/**
	 * 压缩图片
	 * @param path  图片路径
	 * @param targetWidth	要压缩的目标宽度
	 * @return 图片
	 * 7.27 16:18 Alex
	 */
	public static Bitmap getReduceBitmap(String path,int targetWidth){
	
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		//计算压缩比
		int width = options.outWidth;
		int rate = 1;
		if(width>targetWidth){
			rate = Math.round((float)width/(float)targetWidth);
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = rate;
		return BitmapFactory.decodeFile(path,options);
	}
	
}
