package com.tz.imageloader.util;

import android.graphics.drawable.Drawable;

public interface ImageLoaderCallBack {
	//����ͼƬ������ϻص�����
	/**
	 * ��ȡ
	 * @param ID 0������������ 1����SD���� 3����������
	 * @param drawable
	 */
	void loadedImage(int ID,Drawable drawable);
}
