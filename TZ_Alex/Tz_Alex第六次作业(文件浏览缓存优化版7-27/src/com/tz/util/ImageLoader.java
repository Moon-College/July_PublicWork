package com.tz.util;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;

public class ImageLoader {

	public static ImageLoader mImageLoader;
	public static LruCache<String, Bitmap> lruCache;
	
	public ImageLoader() {
		//计算缓存的总大小
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory / 8;
		lruCache = new LruCache<String,Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// 重写此方法来衡量每一张图片的大小，默认返回图片的数量大小   每一行字节数x图片高/1024  即图片大小单位K
				return value.getRowBytes()*value.getHeight()/1024;
			}
		};
	}

	
	/**
	 * 将最近经常使用的图片放进lruCache中
	 */
	
	public void addBitmapToLruCache(String path,Bitmap bitmap){
		if(getBitmFromLruCache(path)==null){
			lruCache.put(path, bitmap);
		}
	}
	
	
	/**
	 * 从缓存中获取已缓存图片
	 */
	public Bitmap getBitmFromLruCache(String path){
		return lruCache.get(path);
	}
	
	/**
	 * 单例模式，避免创建多个ImageLoader对象
	 * @return
	 */
	public static ImageLoader getInstance(){
		if(mImageLoader == null){
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	/**
	 * 压缩图片,一个图片路径参数，一个压缩后的宽度参数
	 */
	public static Bitmap getReduceBitmap(String iconPath, int reqWidth) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		
		options.inJustDecodeBounds = true;
		
		BitmapFactory.decodeFile(iconPath, options);
		int width = options.outWidth;
		int inSampleSize = 1;
		if (width > reqWidth) {
			int rate = Math.round((float)width/(float)reqWidth);
			inSampleSize = rate;
		}

		options.inJustDecodeBounds = false;
		options.inSampleSize = inSampleSize;
		return BitmapFactory.decodeFile(iconPath, options);

	}
}
