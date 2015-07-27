package com.hacket.filemanager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Created by zengfansheng on 2015/7/27 0027.
 */
public class ImageLoader {

    private Context mContext;
    private LruCache<String, Bitmap> mLruCache;

    private static ImageLoader ourInstance = null;
    private int mWidthPixels;
    private int mHeightPixels;

    public synchronized static ImageLoader getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ImageLoader(context);
        }
        return ourInstance;
    }

    private ImageLoader(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 4;
        mLruCache = new LruCache<String, Bitmap>((int) maxMemory) { // 取单个应用内存最大值的1/4
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight(); // 图片的大小
            }
        };

        mWidthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        mHeightPixels = mContext.getResources().getDisplayMetrics().heightPixels;

    }

    public Bitmap addBitmapToMemory(String path) {

        if (TextUtils.isEmpty(path)) {
            return null;
        }

        Bitmap bm = FileManagerUtil.sacleBitmap(path, mWidthPixels, mHeightPixels);
        mLruCache.put(path, bm);

        return bm;
    }

    public Bitmap addBitmapToMemory(String path, Bitmap bm) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        mLruCache.put(path, bm);
        return bm;
    }

    public Bitmap getBitmapFromMemory(String path) {
        Log.e("tag", "从cache中取：" + path);
        return mLruCache.get(path);
    }

    public void display(ImageView iv, String path) {
        if (TextUtils.isEmpty(path) || iv == null) {
            return;
        }

        Bitmap bm = getBitmapFromMemory(path);
        if (bm == null) {
            bm = addBitmapToMemory(path);
        }
        iv.setImageBitmap(bm);
    }

}
