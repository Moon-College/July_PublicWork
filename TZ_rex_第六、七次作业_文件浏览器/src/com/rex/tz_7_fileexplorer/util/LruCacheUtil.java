package com.rex.tz_7_fileexplorer.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LruCacheUtil
{
    public static LruCache<String, Bitmap> LRU_CACHE = null;

    private static void init()
    {
        if (null == LRU_CACHE)
        {
            Runtime runtime = Runtime.getRuntime();
            int maxSize = (int) (runtime.freeMemory() / 10);
            LRU_CACHE = new LruCache<String, Bitmap>(maxSize)
            {
                @Override
                protected int sizeOf(String key, Bitmap bitmap)
                {
                    return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
                }
            };
        }
    }

    public static void add(String path, Bitmap bitmap)
    {
        init();

        if (null == LRU_CACHE.get(path))
        {
            LRU_CACHE.put(path, bitmap);
        }
    }

    public static Bitmap get(String path)
    {
        init();

        Bitmap bitmap = LRU_CACHE.get(path);
        if (null == bitmap)
        {
            bitmap = BitMapUtil.getCompressedBitmap(path, 100);
            LRU_CACHE.put(path, bitmap);
        }

        return bitmap;
    }
}
