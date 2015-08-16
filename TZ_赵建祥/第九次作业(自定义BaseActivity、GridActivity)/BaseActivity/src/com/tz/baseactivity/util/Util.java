package com.tz.baseactivity.util;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Util {

	/**
	 * 图片缩放并使用软引用
	 * @param path 图片路径
	 * @param width 要缩放到的宽度
	 * @return SoftReference<Bitmap> 返回一个装图片的软引用对象
	 */
	public static SoftReference<Bitmap> scaleImageToSoftReference(String path,int width){
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;//如果设置为true，解码图片将返回null
		Bitmap bitmap=BitmapFactory.decodeFile(path, options);
		int imgWidth=options.outWidth;
		int imgHeight=options.outHeight;
		options.inJustDecodeBounds=false;
		options.inSampleSize=imgWidth/width;//缩放图片比例
		bitmap=BitmapFactory.decodeFile(path, options);
		SoftReference<Bitmap> sr=new SoftReference<Bitmap>(bitmap);
		return sr;
	}
}
