package com.tz.bouncingballs.util;

import java.math.BigDecimal;

/**
 * ���ݹ�����
 * @author Administrator
 *
 */
public class MathUtil {

	/**
	 * ���漸λС��
	 * @param d 
	 * @param size С��λ��
	 * @return
	 */
	public double scale(double d,int size){
		 // ����6λС��
		 BigDecimal b = new BigDecimal(d);
		 double result = b.setScale(6, BigDecimal.ROUND_HALF_UP)
		 .doubleValue();
		 return result;
	}
	
}
