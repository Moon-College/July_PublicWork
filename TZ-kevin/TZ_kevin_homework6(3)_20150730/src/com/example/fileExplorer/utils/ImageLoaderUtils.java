package com.example.fileExplorer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * @author kevin.li
 * @version 1.0.1
 * @create 20150728
 * @function ͼƬ���ع����� �DƬ�������� ע��ʹ����LRU�㷨 ���������ôʹ�õ�ͼƬ���� linkedHashMap ʹ��ǿ���ñ���
 */
public class ImageLoaderUtils {
	private static ImageLoaderUtils instance;
	private static LruCache<String, Bitmap> cache;

	/**
	 * ���췽��
	 */
	private ImageLoaderUtils() {
		// ��ȡ����ڴ�
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		// �����ڴ�
		int cacheMemory = maxMemory / 8;
		cache = new LruCache<String, Bitmap>(cacheMemory) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// ��д�˷���������ÿһ��ͼƬ�Ĵ�С ����ͼƬ����
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};

	}

	/**
	 * ʹ�õ���ģʽ����
	 * 
	 * @return
	 */
	public static ImageLoaderUtils getInstance() {
		if (null == instance) {
			instance = new ImageLoaderUtils();
		}
		return instance;

	}

	/**
	 * ��ͼƬ���浽LruCache�� ͼƬ��·�� �� key ͼƬ��value
	 */
	public void addBitmapToMemoryCache(String key, Bitmap value) {

		if (null != getBitmapFromMemoryCache(key)) {
			cache.put(key, value);

		}
	}

	/**
	 * ��LruCache�����ȡһ��ͼƬ ��ȡͼƬ��·����Ϊkey
	 */
	public Bitmap getBitmapFromMemoryCache(String path) {
		return cache.get(path);

	}
	
	/**
	 * ͼƬѹ��  ��ͼƬ�ļ�  ����һ����ѹ������ ����һ��bitmap
	 */
	public static Bitmap decodeBitmapFromResoure(String path,int reWidth){
		BitmapFactory.Options options = new BitmapFactory.Options();
		//��ȡ���  
		options.inJustDecodeBounds = true;
		
		BitmapFactory.decodeFile(path, options);
		int width = options.outWidth;
		int inSampleSize = 1;
		if (width >reWidth) {
			//�����ʵ�ʿ�Ⱥ�Ŀ���ȵı���
			int widthRatio = Math.round((float)width/(float)reWidth);
			inSampleSize = widthRatio;
		}
		
		//��ʽѹ��
		options.inJustDecodeBounds =false;	
		options.inSampleSize =inSampleSize;

		return BitmapFactory.decodeFile(path, options);
		
	}
	
}
