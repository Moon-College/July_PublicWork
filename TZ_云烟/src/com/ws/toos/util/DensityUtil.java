package com.ws.toos.util;

import android.content.Context;

public class DensityUtil {
	/**
	 * �����ֻ��ķֱ��ʴ�dp�ĵ�λ ת��px;
	 * 
	 * */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);

	}

	/**
	 * �����ֻ��ķֱ��ʴ�px�ĵ�λת��dp;
	 * */
	public static int px2dip(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue / scale + 0.5f);
	}
}
