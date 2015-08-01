package com.tz.fileexplorer.util;

import java.util.Set;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class BitmapCache {
	private LruCache<String, Bitmap> lruCache;
	private static BitmapCache bitmapCache;

	private BitmapCache() {

	}

	public static final BitmapCache getInstance() {
		if (bitmapCache == null) {
			bitmapCache = new BitmapCache();
		}
		return bitmapCache;
	}

	public void init(int maxSize) {
		bitmapCache.release();
		bitmapCache.lruCache = new LruCache<String, Bitmap>(maxSize);
	}

	public void addBitmap(String key, Bitmap bmp) {
		bitmapCache.lruCache.put(key, bmp);
	}

	public Bitmap getBitmap(String key) {
		return bitmapCache.lruCache.get(key);
	}

	public void release() {
		if (bitmapCache != null && bitmapCache.lruCache != null) {
			try {
				Set<String> keySet = bitmapCache.lruCache.snapshot().keySet();
				for (String key : keySet) {
					bitmapCache.getBitmap(key).recycle();
				}
			} catch (Exception e) {

			}
			bitmapCache.lruCache.evictAll();
		}
	}

}
