package com.mumu.filebrowser.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageLoader {
	
	private static ImageLoader mImageLoader = null;
	private LruCache<String, Bitmap> mLruCache;

	private ImageLoader() {
		//��ȡϵͳ�����ÿ��Ӧ�ó��������ڴ棬ÿ��Ӧ��ϵͳ����32M
		int maxMemory = (int)Runtime.getRuntime().maxMemory();
		//��LruCache����1/8 4M
		int maxSize = maxMemory / 8;
		mLruCache = new LruCache<String, Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				//������д�˷�����������Bitmap�Ĵ�С
				//ÿһ������ռ���ֽ���*����
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}
	
	/**
	 * ͨ������ģʽ��ȡImageLoader
	 */
	public static ImageLoader getInstance(){
		if(mImageLoader == null){
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	/**
	 * ��Cache��ȡ��Bitmap����
	 */
	public Bitmap getBitmapFromCache(String key){
		return mLruCache.get(key);
	}
	
	/**
	 * ��bitmap����cache������
	 */
	public void putBitmapToCache(String key, Bitmap bitmap){
		if(getBitmapFromCache(key) == null && bitmap != null){
			mLruCache.put(key, bitmap);
		}
	}

}
