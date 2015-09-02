package com.tz.lsn6.Util;

import android.content.Context;
import android.view.WindowManager;

/***
 * 辅助函数类
 * @author dallon2
 *
 */
public class FunctionHelper {
	
	/**
	 * 获取屏蔽的宽度
	 * @param cnt
	 * @return
	 */
	public static int getWindowWidth(Context cnt) {
		//获取手机屏幕的宽高
		WindowManager wm = (WindowManager) cnt.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}
}
