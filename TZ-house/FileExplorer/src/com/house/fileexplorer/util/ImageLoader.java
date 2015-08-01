package com.house.fileexplorer.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * ͼƬ����
 * 
 * @author House
 *
 */
public class ImageLoader {

	/**
	 * ͼƬ��������ࣨ�����ʹ���ٵ�ͼƬ���գ������������ʹ�ô������ͼƬ��
	 */

	private static LruCache<String, Bitmap> mMemoryCache;
	private static ImageLoader mImageLoader;

	private ImageLoader() {
		//maxSize��໺�����ڴ�(���Ӧ�ó����������ڴ�/8)
		// ��ȡӦ�ÿ��õ�����ڴ���
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// ��д�˷���������ÿ��ͼƬ�ķ�����Ĭ�Ϸ���ͼƬ��������С  ����kb
				return bitmap.getRowBytes() / bitmap.getHeight() / 1024;
			}
		};
	}
	
	/**
	 * ��ͼƬ���浽LruCache��
	 * ��ͼƬ��·����ΪͼƬ�����key
	 * @param path ͼƬ��·��
	 * @param bitmap ͼƬ
	 */
	public void addBitmapAddMemoryCache(String path, Bitmap bitmap){
		if (getBitmapFromMemoryCache(path) == null) { // ��������в���������ͼƬ���򻺴棻
			mMemoryCache.put(path, bitmap);
		}
	}

	/**
	 * ����key�ӻ�����ȡͼƬ
	 * @param path
	 * @return
	 */
	public Bitmap getBitmapFromMemoryCache(String path){
		return mMemoryCache.get(path);
	}
	
	/**
	 * ʹ�õ���ģʽ������ûMemoryCache�ظ�����
	 * @return
	 */
	public static ImageLoader getInstance() {
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
}
