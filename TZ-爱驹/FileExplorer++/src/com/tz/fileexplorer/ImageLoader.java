package com.tz.fileexplorer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {

	private static ImageLoader mImageLoader;
	private LruCache<String, Bitmap> mMemoryCache;
	private ImageLoader() {
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory/8;
		
		mMemoryCache = new LruCache<String, Bitmap>(maxSize){;
			protected int sizeOf(String key, Bitmap b) {
				return b.getRowBytes()*b.getHeight()/1024;
			};
		};
	}
	
	public void addBitmapToCache(String path,Bitmap bitmap){
		if(getBitmapFromCache(path)==null){
			mMemoryCache.put(path, bitmap);
		}
	}
	
	public Bitmap getBitmapFromCache(String path){
		return mMemoryCache.get(path);
	}
	
	
	public synchronized static ImageLoader getInstance(){
		if (mImageLoader==null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	public static Bitmap loadImage(String path,int widthAndHeight){
		BitmapFactory.Options opts = new BitmapFactory.Options();
		
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		int width = opts.outWidth;
		int ratio = Math.round((float)width/(float)widthAndHeight);
		opts.inSampleSize = ratio;
		opts.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, opts);
	}
}
