package com.tz.imageloader.util;

import android.graphics.drawable.Drawable;

public interface ImageLoaderCallBack {
	//定义图片加载完毕回调方法
	/**
	 * 获取
	 * @param ID 0：从软引用来 1：从SD卡来 3：从网络来
	 * @param drawable
	 */
	void loadedImage(int ID,Drawable drawable);
}
