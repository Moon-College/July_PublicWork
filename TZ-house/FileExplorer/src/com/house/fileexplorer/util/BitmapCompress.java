package com.house.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * ͼƬѹ��
 * 
 * @author House
 * 
 */
public class BitmapCompress {

	/**
	 * ͼƬѹ�� ��ȡһ��ͼƬ�ļ���ѹ��������һ��Bitmap
	 * 
	 * @param path
	 *            ͼƬ·��
	 * @param reqWidth
	 *            ͼƬ���ѹ����
	 */
	public static Bitmap decodeBitmapFromResponse(String path, int reqWidth) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// ����ȥͼƬ���ڴ���,ֻ����ͼƬ�Ŀ��
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// ��ȡͼƬ�Ŀ��
		int width = options.outWidth;
		int height = options.outHeight;
		// ͼƬѹ���ı���
		int inSmapleSize = 1;
		// �ж�ͼƬ�Ŀ�ߣ������ȴ��ڸ߶ȣ��򰴿�ȱ���ѹ�������򰴸߶ȱ���ѹ��
		if (width > height) {
			if (width > reqWidth) {
				inSmapleSize = Math.round((float) width / (float) reqWidth);
			}
		} else {
			inSmapleSize = Math.round((float) height / (float) reqWidth);
		}
		
		// ��ȡͼƬ����
		options.inJustDecodeBounds = false;
		options.inSampleSize = inSmapleSize;
		return BitmapFactory.decodeFile(path, options);
	}
}
