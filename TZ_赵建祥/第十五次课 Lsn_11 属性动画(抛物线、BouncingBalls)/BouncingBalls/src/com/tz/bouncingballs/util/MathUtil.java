package com.tz.bouncingballs.util;

import java.math.BigDecimal;

/**
 * 数据工具类
 * @author Administrator
 *
 */
public class MathUtil {

	/**
	 * 保存几位小数
	 * @param d 
	 * @param size 小数位数
	 * @return
	 */
	public double scale(double d,int size){
		 // 保留6位小数
		 BigDecimal b = new BigDecimal(d);
		 double result = b.setScale(6, BigDecimal.ROUND_HALF_UP)
		 .doubleValue();
		 return result;
	}
	
}
