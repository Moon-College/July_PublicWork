package com.tz.lsn6.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
	public static Bitmap decodeBitmapFromResource(String iconFile, int reqWdith) {
		
		System.out.println("iconFile:" + iconFile + " reqWdith:" + reqWdith);
		BitmapFactory.Options options = new BitmapFactory.Options();
		
		//����ȡͼƬ��������ֻ����ͼƬ�Ŀ��
		options.inJustDecodeBounds = true; 
		BitmapFactory.decodeFile(iconFile, options);
		// �õ�ԭʼͼƬ�Ŀ��  
		int width = options.outWidth;
		int height = options.outHeight;
		//Ĭ��ѹ������Ϊ1 ���� ����ԭ���ߴ��С
		int inSampleSize = 1;
		int widthRate = 1;
		
		//���ʵ�����ر���Ҫѹ�������ش�Ž���ѹ��  ���򱣳�ԭ���ߴ��С
		if(width > reqWdith) {
			//�����ʵ�ʿ����Ŀ���ȵı���
			widthRate = Math.round(width/reqWdith);
			inSampleSize = widthRate;
		} 
		
		if(height/inSampleSize > reqWdith * 1.5) {
			inSampleSize = Math.round((float)(height * 1.5/reqWdith));
		}
		
		System.out.println("********inSampleSize[" + inSampleSize + "]  iconFile:" + iconFile);
		
		//��ȡͼƬ����
		options.inJustDecodeBounds = false;
		//����ѹ������ �� Ϊ2 ��͸�һ��ѹ�� 0.5 ����3/4��
		options.inSampleSize = inSampleSize;
		//�õ�ѹ�����ͼƬ
		Bitmap bitmap = BitmapFactory.decodeFile(iconFile, options);
		
		return bitmap;
	}
}
