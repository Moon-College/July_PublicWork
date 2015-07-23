package com.tz.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.test.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText phone_et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		phone_et = (EditText) findViewById(R.id.phone_et);
		Log.i("ACTIVITY_INFO", "MainActivity created.");
	}

	public void saveLog(View view) {
		String log = getLog("ACTIVITY_INFO");
		Toast.makeText(this, log == null ? "读取日志失败" : log, Toast.LENGTH_SHORT).show();
	}

	/*
	 * 打电话
	 */
	public void call(View view) {
		String number = phone_et.getText().toString();
		Intent callIntent = new Intent();
		callIntent.setAction("android.intent.action.CALL");
		callIntent.setData(Uri.parse("tel:" + number));
		startActivity(callIntent);
	}

	/**
	 * 发送信息
	 */
	public void send(View view) {
		String number = phone_et.getText().toString();
		Intent sendIntent = new Intent();
		sendIntent.setAction("android.intent.action.SENDTO");
		sendIntent.setData(Uri.parse("sms:" + number));
		startActivity(sendIntent);
	}

	/**
	 * 得到日志
	 * @param filter
	 * @return
	 */
	private String getLog(String filter) {
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d"); // 只采集一次日志
		cmdLine.add("-s"); // 过滤
		cmdLine.add(filter);
		String[] strs = new String[cmdLine.size()];
		try {
			Process process = Runtime.getRuntime().exec(cmdLine.toArray(strs));
			InputStream is = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
