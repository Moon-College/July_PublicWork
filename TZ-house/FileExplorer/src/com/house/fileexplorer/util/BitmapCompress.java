package com.house.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片压缩
 * 
 * @author House
 * 
 */
public class BitmapCompress {

	/**
	 * 图片压缩 读取一个图片文件，压缩后生成一个Bitmap
	 * 
	 * @param path
	 *            图片路径
	 * @param reqWidth
	 *            图片宽度压缩到
	 */
	public static Bitmap decodeBitmapFromResponse(String path, int reqWidth) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 不读去图片到内存中,只解析图片的宽高
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// 获取图片的宽度
		int width = options.outWidth;
		int height = options.outHeight;
		// 图片压缩的比率
		int inSmapleSize = 1;
		// 判断图片的宽高，如果宽度大于高度，则按宽度比率压缩，否则按高度比率压缩
		if (width > height) {
			if (width > reqWidth) {
				inSmapleSize = Math.round((float) width / (float) reqWidth);
			}
		} else {
			inSmapleSize = Math.round((float) height / (float) reqWidth);
		}
		
		// 读取图片本身
		options.inJustDecodeBounds = false;
		options.inSampleSize = inSmapleSize;
		return BitmapFactory.decodeFile(path, options);
	}
}
