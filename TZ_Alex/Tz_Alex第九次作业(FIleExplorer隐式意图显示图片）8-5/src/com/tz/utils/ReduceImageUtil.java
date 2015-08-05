package com.tz.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ReduceImageUtil {

	public static ReduceImageUtil imageUtil;
	public static LruCache<String, Bitmap> lruCache;
	
	
	public static ReduceImageUtil getInstance(){
		if(imageUtil == null){
			imageUtil = new ReduceImageUtil();
		}
		return imageUtil;
	}
	
	public ReduceImageUtil(){
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int memory = maxMemory / 6;
		lruCache = new LruCache<String,Bitmap>(memory){
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes()*value.getHeight()/1024;
			};
		};
	}
	
	
	public static Bitmap getBitmapFromMemory(String path){
		return lruCache.get(path);
	}
	
	public static void putBitmapToMemeory(String path,Bitmap bitmap){
		if(getBitmapFromMemory(path)==null){
			lruCache.put(path, bitmap);
		}
	}
	
	public static Bitmap getResuceBitmap(String path, int targetWidth){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int width = options.outWidth;
		int rate = 1;
		if(width>targetWidth){
			rate =  Math.round((float)width/(float)targetWidth);
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = rate;
		return BitmapFactory.decodeFile(path,options);
	}
}
