package com.tz.systempopupwindow.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڹ�����
 * @author �Խ���
 *
 */
public class DateUtil {

	/**
	 * ��ʽ����ǰʱ�䣬��ʽΪ yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowTimeStr(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * ��ʽ��ָ��ʱ�䣬��ʽΪ yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeStr(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
