package com.tz.systempopupwindow.util;

import android.graphics.Color;

/**
 * 颜色工具类
 * @author 赵建祥
 *
 */
public class ColorUtil {

	/**
	 * 生成一个随机色
	 * @return 颜色值
	 */
	public static int generateRandomColor(){
		//计算黑、白两色对应的数值 
		long black = Long.parseLong("ff000000", 16);
		//计算白色对应的数据
		long white = Long.parseLong("ffffffff", 16);
		long colorLong =(long) (Math.random()*(black-white+1)+white);
		String colorhex="#"+Long.toHexString(colorLong);
		return Color.parseColor(colorhex);
	}
	
}
