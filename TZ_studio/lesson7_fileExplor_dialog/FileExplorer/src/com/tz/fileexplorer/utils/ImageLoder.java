package com.tz.fileexplorer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoder {
	private static ImageLoder imageLoder;

	private LruCache<String, Bitmap> LruMap = null;

	private ImageLoder() {
		initLruCash();
	}

	public static ImageLoder getInstance() {
		if (imageLoder == null) {
			imageLoder = new ImageLoder();
		}
		return imageLoder;
	}

	/**
	 * 此方法描述的是：初始化LruCash
	 * 
	 * @author: studio initLruCash void
	 */
	private void initLruCash() {
		int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
		LruMap = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return super.sizeOf(key, value);
			}
		};
	}

	/**
	 * 
	 * 此方法描述的是：压缩图片
	 * 
	 * @author: studio imageCompress void
	 */
	public Bitmap imageCompress(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		
		int outHeight = options.outHeight;
		int outWidth = options.outWidth;
		
		if (outHeight > outWidth) {
			
		}
		options.inSampleSize = 8;
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}

	public void addImageToCash(String path, Bitmap bitmap) {
		LruMap.put(path, bitmap);
	}

	public Bitmap getImageFromCash(String path) {
		return LruMap.get(path);
	}

}
