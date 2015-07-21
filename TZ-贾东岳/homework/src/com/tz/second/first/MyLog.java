package com.tz.second.first;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

import com.tz.main.MyApplication;

/**
 * 第二节课第一个作业的类，输出指定tag信息的log的信息.
 * 可用于查看Log的基本使用;
 * @author JDY
 * 
 */
public class MyLog {

	/**
	 * 调用log输出内容. log按照日志级别从高到低为ERROR, WARN, INFO, DEBUG, VERBOSE.
	 * 颜色依次为黑，蓝，绿，橙，红。
	 */
	public void showLogInfo() {
		String tag = "MY_INFO";
		// Log.v 的输出颜色为黑色的，输出大于或等于VERBOSE日志级别的信息
		Log.v(tag, "VERBOSE msg");

		// Log.d的输出颜色是蓝色的，输出大于或等于DEBUG日志级别的信息
		Log.d(tag, "DEBUG msg");

		// Log.i的输出为绿色，输出大于或等于INFO日志级别的信息
		Log.i("INFO", "INFO msg");

		// Log.w的输出为橙色, 输出大于或等于WARN日志级别的信息
		Log.w("WARN", "WARN msg");

		// Log.e的输出为红色，仅输出ERROR日志级别的信息.
		Log.e("ERROR", "ERROR msg");

		outPutLog(tag);
	}

	/**
	 * 输出包含tag信息的log内容。
	 * 
	 * @param tag
	 *            即log tag的值。
	 */
	public static void outPutLog(String tag) {
		if (MyApplication.debuger) {
			// Log.i("MY_INFO", "log_btn_OnClick");
			ArrayList<String> cmdLine = new ArrayList<String>();
			cmdLine.add("logcat");
			// 只采集一次日志信息
			cmdLine.add("-d");
			// s 设置过滤器，例如在其后面后面指定 '*:s' *:w
			cmdLine.add("-s");
			cmdLine.add(tag);
			String cmdStr[] = new String[cmdLine.size()];
			try {
				Process process = Runtime.getRuntime().exec(
						cmdLine.toArray(cmdStr));
				InputStream is = process.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String str = null;
				StringBuffer sb = new StringBuffer();
				while ((str = br.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				Toast.makeText(MyApplication.appContext(), sb.toString(),
						Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
