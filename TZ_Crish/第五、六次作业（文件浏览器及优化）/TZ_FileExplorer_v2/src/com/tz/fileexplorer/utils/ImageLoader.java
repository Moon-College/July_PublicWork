package com.tz.fileexplorer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
	//ImageLoader����
    private static ImageLoader instance = null;
    //LruCache����
    private static LruCache<String, Bitmap> lruCache;
    //�����С
    private double cacheSize;

    private ImageLoader() {
        cacheSize = Runtime.getRuntime().maxMemory() / 8;
        lruCache = new LruCache<String, Bitmap>((int)cacheSize){ 
        	protected int sizeOf(String key, Bitmap value) { 
        		return value.getByteCount(); 
        	}
        }; 
    }

    //����ģʽ���ImageLoader����
    public static ImageLoader getInstance() {
        if(instance == null) {
        	instance =  new ImageLoader();
        }
        return instance;
    }

    /**
     * @param path ͼƬ·����������ΪLinkedHashMap��key
     * @param bitmap ͼƬ��������ΪLinkedHashMap��value
     */
    public static void addBitmapToCache(String path, Bitmap bitmap) {
        if(lruCache.get(path) == null) {
            lruCache.put(path, bitmap);
        }
    }


    /**
     * @param path ͼƬ·����key
     * @return key��Ӧ��ͼƬ
     */
    public static Bitmap getBitmapFromCache(String path) {
        return lruCache.get(path);
    }

    /**
     * @param path ͼƬ·��
     * @param width_req ѹ�����ͼƬ���
     * @param height_req ѹ�����ͼƬ�߶�
     * @return ѹ�����ͼƬ
     */
    public static Bitmap decodeBitmapFromFile(String path, int width_req, int height_req) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //ֻ����ͼƬ�߽�
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int width_origin = options.outWidth;
        int height_origin = options.outHeight;

        int ratio = 1;
        //��֤ѹ�����ͼƬ��ȡ��߶ȶ����趨�ķ�Χ��
        if(width_req <= width_origin) {
            ratio = width_origin / width_req;
        }
        else if(height_req<=height_origin) {
            ratio = height_origin / height_req;
        }
        options.inJustDecodeBounds = false;
        //����ѹ����
        options.inSampleSize = ratio;
        return BitmapFactory.decodeFile(path, options);
    }
    
}