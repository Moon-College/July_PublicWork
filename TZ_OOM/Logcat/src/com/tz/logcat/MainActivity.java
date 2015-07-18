package com.tz.logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText mPhoneNumberEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPhoneNumberEditText = (EditText) findViewById(R.id.phone_number);
		Log.i("MAINACTIVITY_INFO", "MainActivity onCreate");
		
	}
	
	/**
	 * 参数收集按钮点击事件 
	 * @param v 
	 */
	public void saveLog(View v) {
		// 收集日志 
		String log = getLog("MAINACTIVITY_INFO");
		// Toast 显示 日志 
		Toast.makeText(this, log == null ? "读取日志失败" : log, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 打开信息发送界面 
	 * @param v
	 */
	public void openSendMessage(View v) {
		// 获取电话号码
		String phoneNumber = mPhoneNumberEditText.getText().toString();
		// 使用隐式意图打开系统发送SMS程序
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SENDTO");
		// 传入电话号码
		intent.setData(Uri.parse("sms:" + phoneNumber));
		startActivity(intent);
	}
	
	/**
	 * 拨打电话
	 * @param v
	 */
	public void callPhone(View v) {
		// 获取电话号码
		String phoneNumber = mPhoneNumberEditText.getText().toString();
		// 使用隐式意图打开系统拨打电话程序
		Intent intent = new Intent();
		intent.setAction("android.intent.action.CALL");
		// 传入电话号码
		intent.setData(Uri.parse("tel:" + phoneNumber));
		startActivity(intent);
	}
	
	
	/**
	 * 根据参数过虑，获取日志 
	 * @param filter 过虑参数  如：
	 * @return 返回日志字符串
	 */
	private String getLog(String filter) {
		// 组装采集日志命令
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		// 读取日志
		cmdLine.add("-d"); 
		// 过虑 
		cmdLine.add("-s");
		// 过虑参数
		cmdLine.add(filter);
		// 执行采集命令
		try {
			Process process = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
			InputStream inputStream = process.getInputStream();
			
			Reader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			
			// 将日志缓存到StringBuffer
			StringBuffer buffer = new StringBuffer();
			// 读取一行，如果读取不为null 继续读取
			while((line = bufferedReader.readLine()) != null) {
				// 将读取的数据保存到StringBuffer 中
				buffer.append(line).append("\n");
			}
			// 返回日志
			return buffer.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
