package com.ws.toos.util;

import android.content.Context;

public class DensityUtil {
	/**
	 * 根据手机的分辨率从dp的单位 转成px;
	 * 
	 * */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);

	}

	/**
	 * 根据手机的分辨率从px的单位转成dp;
	 * */
	public static int px2dip(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue / scale + 0.5f);
	}
}
