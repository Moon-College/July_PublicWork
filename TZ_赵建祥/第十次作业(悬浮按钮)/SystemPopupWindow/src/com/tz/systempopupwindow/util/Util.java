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
	 * ͼƬ���Ų�ʹ��������
	 * @param path ͼƬ·��
	 * @param width Ҫ���ŵ��Ŀ��
	 * @return SoftReference<Bitmap> ����һ��װͼƬ�������ö���
	 */
	public static SoftReference<Bitmap> scaleImageToSoftReference(String path,int width){
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;//�������Ϊtrue������ͼƬ������null
		Bitmap bitmap=BitmapFactory.decodeFile(path, options);
		int imgWidth=options.outWidth;
		int imgHeight=options.outHeight;
		options.inJustDecodeBounds=false;
		options.inSampleSize=imgWidth/width;//����ͼƬ����
		bitmap=BitmapFactory.decodeFile(path, options);
		SoftReference<Bitmap> sr=new SoftReference<Bitmap>(bitmap);
		return sr;
	}
	
	/** 
	* �жϵ�ǰ�����Ƿ������� 
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
	* ������������Ӧ�õ�Ӧ�ð����� 
	* @return ���ذ������а������ַ����б� 
	*/
	private static List<String> getHomes(Context context) { 
	  List<String> names = new ArrayList<String>(); 
	  PackageManager packageManager = context.getPackageManager(); 
	  //���� 
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
