package com.tz.fileexplorer.utils;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapCache {
	private static Bitmap bm;
	@SuppressLint("UseSparseArrays")
	private static Map<Integer, Bitmap> map = new HashMap<Integer, Bitmap>();
	public static Bitmap getBitmap(Resources res,int resId){
		if(resId<0){
			return null;
		}
		bm = map.get(Integer.valueOf(resId));
		if(bm==null){
			bm = BitmapFactory.decodeResource(res, resId);
			map.put(Integer.valueOf(resId), bm);
			return BitmapFactory.decodeResource(res, resId);
		}
		return bm;
	}
}
