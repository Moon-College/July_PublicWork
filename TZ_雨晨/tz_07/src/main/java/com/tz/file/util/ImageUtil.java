package com.tz.file.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * Created by Tokey on 2015/7/26.
 */
public class ImageUtil {

    private static ImageUtil imageUtil =null;
    private static LruCache<String, Bitmap> bitmapLruCache;

    private ImageUtil() {

    }


    public static ImageUtil getInstance() {
        if (imageUtil == null) {
            imageUtil = new ImageUtil();
            int totalSize = (int) Runtime.getRuntime().maxMemory();
            int maxSize = totalSize / 8;
            bitmapLruCache = new LruCache<>(maxSize);
        }
        return imageUtil;
    }

    /**
     * 添加到图片缓存
     */
    public void setBitmapToMemoryCache(String path,Bitmap bitmap) {

        if(getBitmapFromMemoryCahce(path)==null){
            bitmapLruCache.put(path,bitmap);
        }

    }

    /**
     * 从缓中得到图片
     */
    public Bitmap getBitmapFromMemoryCahce(String path){

        return bitmapLruCache.get(path);
    }


    /**
     * 图片压缩
     */
    public static Bitmap getBitmapByCompress(String path, int rewidth) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int w = options.outWidth;
        int h = options.outHeight;

        float rw = rewidth;
        int inSampleSize = 1;

        if (w > rw) {
            inSampleSize = (int) (w / rw);
        }


        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(path, options);
    }


}
