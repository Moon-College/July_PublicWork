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
		//���㻺����ܴ�С
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory / 8;
		lruCache = new LruCache<String,Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// ��д�˷���������ÿһ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ��������С   ÿһ���ֽ���xͼƬ��/1024  ��ͼƬ��С��λK
				return value.getRowBytes()*value.getHeight()/1024;
			}
		};
	}

	
	/**
	 * ���������ʹ�õ�ͼƬ�Ž�lruCache��
	 */
	
	public void addBitmapToLruCache(String path,Bitmap bitmap){
		if(getBitmFromLruCache(path)==null){
			lruCache.put(path, bitmap);
		}
	}
	
	
	/**
	 * �ӻ����л�ȡ�ѻ���ͼƬ
	 */
	public Bitmap getBitmFromLruCache(String path){
		return lruCache.get(path);
	}
	
	/**
	 * ����ģʽ�����ⴴ�����ImageLoader����
	 * @return
	 */
	public static ImageLoader getInstance(){
		if(mImageLoader == null){
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	/**
	 * ѹ��ͼƬ,һ��ͼƬ·��������һ��ѹ����Ŀ�Ȳ���
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
