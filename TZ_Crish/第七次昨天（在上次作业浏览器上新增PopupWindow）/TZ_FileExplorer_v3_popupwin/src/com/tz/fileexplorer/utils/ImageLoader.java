package com.tz.fileexplorer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
	//ImageLoader对象
    private static ImageLoader instance = null;
    //LruCache对象
    private static LruCache<String, Bitmap> lruCache;
    //缓存大小
    private double cacheSize;

    private ImageLoader() {
        cacheSize = Runtime.getRuntime().maxMemory() / 8;
        lruCache = new LruCache<String, Bitmap>((int)cacheSize){ 
        	protected int sizeOf(String key, Bitmap value) { 
        		return value.getByteCount(); 
        	}
        }; 
    }

    //单例模式获得ImageLoader对象
    public static ImageLoader getInstance() {
        if(instance == null) {
        	instance =  new ImageLoader();
        }
        return instance;
    }

    /**
     * @param path 图片路径，这里作为LinkedHashMap的key
     * @param bitmap 图片，这里作为LinkedHashMap的value
     */
    public static void addBitmapToCache(String path, Bitmap bitmap) {
        if(lruCache.get(path) == null) {
            lruCache.put(path, bitmap);
        }
    }


    /**
     * @param path 图片路径，key
     * @return key对应的图片
     */
    public static Bitmap getBitmapFromCache(String path) {
        return lruCache.get(path);
    }

    /**
     * @param path 图片路径
     * @param width_req 压缩后的图片宽度
     * @param height_req 压缩后的图片高度
     * @return 压缩后的图片
     */
    public static Bitmap decodeBitmapFromFile(String path, int width_req, int height_req) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只加载图片边界
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int width_origin = options.outWidth;
        int height_origin = options.outHeight;

        int ratio = 1;
        //保证压缩后的图片宽度、高度都在设定的范围内
        if(width_req <= width_origin) {
            ratio = width_origin / width_req;
        }
        else if(height_req<=height_origin) {
            ratio = height_origin / height_req;
        }
        options.inJustDecodeBounds = false;
        //设置压缩比
        options.inSampleSize = ratio;
        return BitmapFactory.decodeFile(path, options);
    }
    
}