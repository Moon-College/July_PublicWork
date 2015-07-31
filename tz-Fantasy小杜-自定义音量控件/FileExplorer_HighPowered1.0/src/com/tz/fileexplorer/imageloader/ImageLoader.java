package com.tz.fileexplorer.imageloader;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * created at:2015年7月27日 上午9:46:59 project name:lsn1_6_FileExplorer
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:ImageLoader.java description:
 */

public class ImageLoader {
	private static LruCache<String, Bitmap> mLruCache;
	private static ImageLoader mImageLoader;

	private ImageLoader() {
		// TODO Auto-generated constructor stub

		int maxMemorySize = (int) (Runtime.getRuntime().maxMemory() / 8);
		mLruCache = new LruCache<String, Bitmap>(maxMemorySize) {

			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getRowBytes() * value.getHeight() / 1024;
			}

		};
	}

	
	public void addBitmapToMemory(String path,Bitmap bitmap) {		
		mLruCache.put(path, bitmap);
	}
	
	public Bitmap getBitmapFromMemoryCache(String path) {
		
		return mLruCache.get(path);
	}
	
	
	
	
	public static ImageLoader getInstance() {
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}

		return mImageLoader;
	}

	public static Bitmap getCompressedImage(String path, int requiredLength) {
		Bitmap mBitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 设置选项不加载图片，只测量宽高
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int height = options.outHeight;
		int width = options.outWidth;
		// 得到压缩比例
		int ratio = Math.round(Math.max(height / requiredLength, width
				/ requiredLength));
		// 取消仅测量边界选项
		options.inJustDecodeBounds = false;
		// 设置压缩比例
		options.inSampleSize = ratio;
		return BitmapFactory.decodeFile(path, options);
	}

}
