package com.tz.lsn13_applaunchermode;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.view.WindowManager;

/***
 * 辅助函数
 * @author dallon2
 *
 */
public class FunctionHelper {
	
	/**
	 * 获取屏蔽的宽
	 * @param cnt
	 * @return
	 */
	public static int getWindowWidth(Context cnt) {
		//获取手机屏幕的宽
		WindowManager wm = (WindowManager) cnt.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}
	
	/**
	 * 根据包名检测是否运行状态
	 * @param cnt
	 * @param packageName
	 * @return
	 */
	public static boolean checkRunningActivityStatus(Context cnt,String packageName) {
		boolean isAppRunning = false;
		
		ActivityManager am = (ActivityManager)cnt.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> list = am.getRunningTasks(100);
	    for (RunningTaskInfo info : list) {
	        if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
	            isAppRunning = true;
	            //find it, break
	            break;
	        }
	    }
	    return isAppRunning;
	}
}
