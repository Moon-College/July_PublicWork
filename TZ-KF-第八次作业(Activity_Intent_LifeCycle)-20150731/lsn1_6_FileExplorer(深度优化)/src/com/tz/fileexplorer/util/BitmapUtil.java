package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class BitmapUtil {
	/**
	 * 缩小图片
	 * 
	 * @param path
	 * @param maxSize
	 *            最大内存占用(单位:字节)
	 * @return
	 */
	public static Bitmap shrinkBitmap(String path, long maxSize) {
		long realSize = getBitmapSize(path);
		Bitmap bmp;
		if (realSize <= maxSize) {
			bmp = BitmapFactory.decodeFile(path);
		} else {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = (int) Math.max(1, getBitmapSize(path, opts) / maxSize);
			opts.inJustDecodeBounds = false;
			bmp = BitmapFactory.decodeFile(path, opts);
		}
		return bmp;
	}

	public static long getBitmapSize(String path) {
		return getBitmapSize(path, null);
	}

	public static long getBitmapSize(String path, Options opts) {
		if (opts == null) {
			opts = new BitmapFactory.Options();
		}
		boolean lastInJustDecodeBounds = opts.inJustDecodeBounds;
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		opts.inJustDecodeBounds = lastInJustDecodeBounds;
		return opts.outWidth * opts.outHeight * 4;
	}
}
