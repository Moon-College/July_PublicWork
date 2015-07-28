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
	 * ��ָ����·���м���ͼƬ ��
	 * @param path ͼƬ·�� 
	 * @param width ͼƬ����
	 * @return
	 */
	public Bitmap getBitmapWithPath(String path,int width) {
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// ��ȡͼƬ �Ŀ��
		int bitmapWidth = options.outWidth;
		int bitmapHeight = options.outHeight;
		
		int height = (width * bitmapHeight) / bitmapWidth;
		
		// ����ͼƬ ѹ����
		int inSampleSize = Math.round((bitmapWidth*1.0f / width + bitmapHeight * 1.0f / height)/2);
		Log.d("ImageLoader", "width = " + width + ",height = " + height + ", inSampleSize = " + inSampleSize);
		
		options.inSampleSize = inSampleSize;
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}
	
	/**
	 * ��ͼƬ ��ӵ��ڴ滺����
	 * @param path ͼƬ·�� 
	 * @param bitmap ͼƬ 
	 */
	public void addBitmapToMemoryCache(String path, Bitmap bitmap) {
		mMemoryCache.put(path, bitmap);
	}
	
	/**
	 * ����ͼƬ·�����ڴ滺�� �л�ȡͼƬ
	 * @param path ͼƬ·��
	 * @return
	 */
	public Bitmap getBitmapFromMemoryCache(String path) {
		return mMemoryCache.get(path);
	}
	
}
