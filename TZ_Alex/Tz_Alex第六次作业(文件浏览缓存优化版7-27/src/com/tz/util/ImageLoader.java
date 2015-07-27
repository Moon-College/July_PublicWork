package com.tz.util;

import android.R.anim;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {

	private static ImageLoader mImageLoader;
	private static LruCache< String, Bitmap> lruCache;
	/**
	 * ����lrucache�Ļ����С
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
	 * ��ͼƬ�����������
	 * @param path
	 */
	public static void addBitmapToMemeory(String path,Bitmap bitmap){
		if(getBitmapFromMemory(path)==null){
			lruCache.put(path, bitmap);
		}
	}
	
	/**
	 * �ӻ����еõ��ѻ����ͼƬ
	 * @param path
	 * @return  �����е�ͼƬ
	 */
	public static Bitmap getBitmapFromMemory(String path){
		return lruCache.get(path);
	}
	
	//����ImageLoader�����ظ����ɣ�Ӧ�õ���ģʽ
	public static ImageLoader getInstance(){
		if(mImageLoader==null){
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	
	/**
	 * ѹ��ͼƬ
	 * @param path  ͼƬ·��
	 * @param targetWidth	Ҫѹ����Ŀ����
	 * @return ͼƬ
	 * 7.27 16:18 Alex
	 */
	public static Bitmap getReduceBitmap(String path,int targetWidth){
	
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		//����ѹ����
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
