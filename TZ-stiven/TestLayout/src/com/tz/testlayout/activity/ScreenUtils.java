package com.tz.testlayout.activity;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {
	
	public static int[] getScreenWH(Context context){
		
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		return new int[] {outMetrics.widthPixels, outMetrics.heightPixels};
	}
}
