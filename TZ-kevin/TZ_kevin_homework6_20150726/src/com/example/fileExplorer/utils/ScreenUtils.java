package com.example.fileExplorer.utils;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function 获取屏幕的长和宽
 */
public class ScreenUtils {
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";

	/**
	 * 获取屏幕的宽高
	 * 
	 */
	public static Map<String, Integer> getSrennSize(Activity act) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int width = act.getWindowManager().getDefaultDisplay().getWidth();
		int height = act.getWindowManager().getDefaultDisplay().getHeight();
		map.put(WIDTH, width);
		map.put(HEIGHT, height);
		return map;

	}

}