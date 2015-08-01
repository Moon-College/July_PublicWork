package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class BitmapUtil {
	/**
	 * ��СͼƬ
	 * 
	 * @param path
	 * @param maxSize
	 *            ����ڴ�ռ��(��λ:�ֽ�)
	 * @return
	 */
	public static Bitmap shrinkBitmap(String path, int maxSize) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;

		for (opts.inSampleSize = 1, BitmapFactory.decodeFile(path, opts); opts.outWidth * opts.outHeight * 4 > maxSize; opts.inSampleSize *= 2) {
			BitmapFactory.decodeFile(path, opts);
		}
		opts.inJustDecodeBounds = false;
		Bitmap bmp = BitmapFactory.decodeFile(path, opts);
		Log.d("lkf", String.format("final: size=%d  max=%d", bmp.getWidth() * bmp.getHeight() * 4, maxSize));
		return bmp;
	}
}
