package com.tz.ls6.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.util.LruCache;

public class ImageLoader {
	private static ImageLoader imgLoader=null;
	private LruCache imgCache=null;
	private ImageLoader(){
		long maxSize=Runtime.getRuntime().maxMemory()/8;
		imgCache=new LruCache<String, Bitmap>((int)maxSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				//返回每个bitmap的大小
				return value.getRowBytes()*value.getHeight();
			}
		};
	}
	
	public static ImageLoader getInstance(){
		if(imgLoader==null){
			imgLoader=new ImageLoader();
		}
		return imgLoader;
	}

	
	public Bitmap getBitmap(String path){
		return (Bitmap) imgCache.get(path);
	}
	
	public void addBitmap(String path,Bitmap bm){
		imgCache.put(path, bm);
	}
}
