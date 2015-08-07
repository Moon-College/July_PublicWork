package com.tz.systempopupwindow.util;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Util {

	/**
	 * 图片缩放并使用软引用
	 * @param path 图片路径
	 * @param width 要缩放到的宽度
	 * @return SoftReference<Bitmap> 返回一个装图片的软引用对象
	 */
	public static SoftReference<Bitmap> scaleImageToSoftReference(String path,int width){
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;//如果设置为true，解码图片将返回null
		Bitmap bitmap=BitmapFactory.decodeFile(path, options);
		int imgWidth=options.outWidth;
		int imgHeight=options.outHeight;
		options.inJustDecodeBounds=false;
		options.inSampleSize=imgWidth/width;//缩放图片比例
		bitmap=BitmapFactory.decodeFile(path, options);
		SoftReference<Bitmap> sr=new SoftReference<Bitmap>(bitmap);
		return sr;
	}
	
	/** 
	* 判断当前界面是否是桌面 
	*/
	public static boolean isHome(Context context){ 
	  ActivityManager mActivityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE); 
	  List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
	  List<String> strs = getHomes(context);
	  if(strs != null && strs.size() > 0){
	   return strs.contains(rti.get(0).topActivity.getPackageName());
	  }else{
	   return false;
	  }
	}
	
	/** 
	* 获得属于桌面的应用的应用包名称 
	* @return 返回包含所有包名的字符串列表 
	*/
	private static List<String> getHomes(Context context) { 
	  List<String> names = new ArrayList<String>(); 
	  PackageManager packageManager = context.getPackageManager(); 
	  //属性 
	  Intent intent = new Intent(Intent.ACTION_MAIN);
	  intent.addCategory(Intent.CATEGORY_HOME); 
	  List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, 
	    PackageManager.MATCH_DEFAULT_ONLY); 
	  for(ResolveInfo ri : resolveInfo){ 
	   names.add(ri.activityInfo.packageName); 
	   Log.i("zhangyinfu PinyinIME.java", "packageName =" + ri.activityInfo.packageName);
	  } 
	  return names;
	}
}
