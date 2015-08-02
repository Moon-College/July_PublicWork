package com.tz.baseactivity.util;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Util {

	/**
	 * ͼƬ���Ų�ʹ��������
	 * @param path ͼƬ·��
	 * @param width Ҫ���ŵ��Ŀ��
	 * @return SoftReference<Bitmap> ����һ��װͼƬ�������ö���
	 */
	public static SoftReference<Bitmap> scaleImageToSoftReference(String path,int width){
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;//�������Ϊtrue������ͼƬ������null
		Bitmap bitmap=BitmapFactory.decodeFile(path, options);
		int imgWidth=options.outWidth;
		int imgHeight=options.outHeight;
		options.inJustDecodeBounds=false;
		options.inSampleSize=imgWidth/width;//����ͼƬ����
		bitmap=BitmapFactory.decodeFile(path, options);
		SoftReference<Bitmap> sr=new SoftReference<Bitmap>(bitmap);
		return sr;
	}
}
