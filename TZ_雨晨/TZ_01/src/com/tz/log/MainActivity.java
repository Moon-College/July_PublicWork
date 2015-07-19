package com.tz.log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private String phoneNum = "110";
	private String tag = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i(tag, this.getClass().getSimpleName() + "----onCreate");
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_main_msg).setOnClickListener(this);
		findViewById(R.id.btn_main_call).setOnClickListener(this);
		findViewById(R.id.btn_main_log).setOnClickListener(this);
	}

	/**
	 * 打电话
	 * 
	 * @param phoneNumber
	 */
	private void sendMessage(String phoneNumber) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("smsto:" + phoneNumber));
		startActivity(intent);
	}

	/**
	 * 发短信
	 * 
	 * @param phoneNumber
	 */
	private void callPhone(String phoneNumber) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + phoneNumber));
		startActivity(intent);
	}

	/**
	 *读取Log
	 */
	private void getLog() {
		// 命令集合
		ArrayList<String> cmdLine = new ArrayList<String>();

		cmdLine.add("logcat");// adb logcat
		cmdLine.add("-d");// 读取
		cmdLine.add("-s");// 过滤
		cmdLine.add(tag);// param

		try {
			Process process = Runtime.getRuntime().exec(
					cmdLine.toArray(new String[cmdLine.size()]));
			InputStream is = process.getInputStream();
			Reader reader = new InputStreamReader(is);
			StringBuffer sb = new StringBuffer();
			BufferedReader bufferedReader = new BufferedReader(reader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_main_msg:
			sendMessage(phoneNum);
			break;
		case R.id.btn_main_call:
			callPhone(phoneNum);
			break;
		case R.id.btn_main_log:
			getLog();
			break;

		default:
			break;
		}
	}

}