package com.house.fileexplorer.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * ��ȡ�ֻ���һЩ��Ϣ
 * @author House
 *
 */
public class PhoneUtils {
	/**
	 * ��ȡ��Ļ�Ŀ��
	 * @param context
	 * @return
	 */
	public static int[] screenWidthAndHeight(Context context){
		WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return new int[]{display.getWidth(),display.getHeight()};
	}
	
	/**
	 * dpת����px
	 * @param dp
	 * @param context
	 * @return
	 */
	public static int dip2px (int dp, Context context) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
	
	/**
	 * pxת����dp
	 * @param px
	 * @param context
	 * @return
	 */
	public static int px2dip(int px, Context context) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f) ;
	}
}
