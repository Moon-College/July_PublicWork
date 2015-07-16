package com.tz.logcollector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	Button btnLogCollector, btnCall;
	TextView tvLog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("MyLog", "系统日志收集");
		Log.d("MyDebug", "这是我的debug信息");
		init();

	}

	/**
	 * 初始化控件
	 */
	private void init() {
		btnLogCollector = (Button) findViewById(R.id.btn_log_collector);
		btnCall = (Button) findViewById(R.id.btn_call);
		tvLog = (TextView) findViewById(R.id.tv_log);
		btnCall.setOnClickListener(this);
		btnLogCollector.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_log_collector:
			// 控制cmd命令保存日志
			ArrayList<String> cmdLine = new ArrayList<String>();
			cmdLine.add("logcat");
			cmdLine.add("-d");// 只采集一次日志
			cmdLine.add("-s");// 过滤
			cmdLine.add("MyLog");

			String[] strs = new String[cmdLine.size()];
			try {
				Process process = Runtime.getRuntime().exec(cmdLine.toArray(strs));// 执行cmdline数组里面的命令，返回一个进程
				InputStream is = process.getInputStream();// 获取返回流(字节流)
				// 将得到的字节流转化成字符流
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String str = null;
				StringBuffer sb = new StringBuffer();
				while ((str = br.readLine()) != null) {
					sb.append(str);// 采集字符流
					sb.append("\n");
				}
				tvLog.setText(sb.toString());
				Toast.makeText(this, "采集成功", Toast.LENGTH_LONG).show();
			} catch (Exception e) {

				e.printStackTrace();
			}

			break;

		case R.id.btn_call:
			// Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10010"));
			Intent intent = new Intent();
			// 系统默认的action，用来打开默认的电话界面
			intent.setAction(Intent.ACTION_CALL);
			// 需要拨打的号码
			intent.setData(Uri.parse("tel:112"));

			startActivity(intent);
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
