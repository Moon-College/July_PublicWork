package com.tz.lsn6.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
	public static Bitmap decodeBitmapFromResource(String iconFile, int reqWdith) {
		
		System.out.println("iconFile:" + iconFile + " reqWdith:" + reqWdith);
		BitmapFactory.Options options = new BitmapFactory.Options();
		
		//不读取图片本身，而是只解析图片的宽高
		options.inJustDecodeBounds = true; 
		BitmapFactory.decodeFile(iconFile, options);
		// 得到原始图片的宽高  
		int width = options.outWidth;
		int height = options.outHeight;
		//默认压缩比例为1 即不 保持原来尺寸大小
		int inSampleSize = 1;
		int widthRate = 1;
		
		//如果实际像素比需要压缩的像素大才进行压缩  否则保持原来尺寸大小
		if(width > reqWdith) {
			//计算出实际宽度与目标宽度的比率
			widthRate = Math.round(width/reqWdith);
			inSampleSize = widthRate;
		} 
		
		if(height/inSampleSize > reqWdith * 1.5) {
			inSampleSize = Math.round((float)(height * 1.5/reqWdith));
		}
		
		System.out.println("********inSampleSize[" + inSampleSize + "]  iconFile:" + iconFile);
		
		//读取图片本身
		options.inJustDecodeBounds = false;
		//设置压缩比例 （ 为2 宽和高一起压缩 0.5 丢了3/4）
		options.inSampleSize = inSampleSize;
		//得到压缩后的图片
		Bitmap bitmap = BitmapFactory.decodeFile(iconFile, options);
		
		return bitmap;
	}
}
