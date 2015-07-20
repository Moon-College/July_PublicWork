package com.my.pixtodp;

import android.content.Context;

/**
 * 对pix与dip进行相互转化
 * @author Think
 *
 */

public class DensityUtil {
	
	public static int dip2px(Context context,float dpValue){
		final float scale=context.getResources().getDisplayMetrics().density;
		return (int) (dpValue*scale+0.5f);
	}
	
	public static int px2dip(Context context,float pxValue){
		final float scale=context.getResources().getDisplayMetrics().density;
		return (int) (pxValue/scale+0.5f);
	}

}
