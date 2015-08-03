package com.ws.file.listview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

;

public class ImageLoader {
	private static LruCache<String, Bitmap> mMemoryCache;
	private static ImageLoader mImageLoader;

	private ImageLoader() {
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(maxSize) {
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
			}

		};
	}

	public static ImageLoader getIntance() {
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}

	public void addBitmapToMemoryCache(String path,Bitmap bitmap){
		if(getBitmapFromMemoryCache(path)==null){
			mMemoryCache.put(path, bitmap);
		}
	}

	public Bitmap getBitmapFromMemoryCache(String path){
		return mMemoryCache.get(path);
	}
	
	public static Bitmap decodeBitmapFromResource(String iconPath, int reqWidth){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(iconPath, options);
		int width = options.outWidth;
		int height = options.outHeight;
		int inSampleSize = 1;
		if(width > reqWidth){
			int widthRatio = Math.round((float)width/(float)reqWidth);
			inSampleSize = widthRatio;
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = inSampleSize;
		return BitmapFactory.decodeFile(iconPath, options);
	}


}
