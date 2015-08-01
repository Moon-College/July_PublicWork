package com.tz.logcatdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "TEST";
	private Button btn_logcat_collect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		Log.i(TAG, "logcatest");
		initView();
	}

	/**
	 * 初始化View
	 * 
	 * @author 屈发
	 */
	private void initView() {
		btn_logcat_collect = (Button) findViewById(R.id.btn_logcat_collect);
		btn_logcat_collect.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_logcat_collect:
			// CMD: logcat -s "TAG"
			ArrayList<String> cmdLine = new ArrayList<String>();
			cmdLine.add("logcat");
			cmdLine.add("-d");
			cmdLine.add("-s");
			cmdLine.add(TAG);
			String[] strs = new String[cmdLine.size()];
			// cmdLine.toArray(strs) 将array分解成对象元素
			try {
				Process process = Runtime.getRuntime().exec(
						cmdLine.toArray(strs));
				InputStream in = process.getInputStream();
				BufferedReader bReader = new BufferedReader(
						new InputStreamReader(in));
				StringBuffer sb = new StringBuffer();
				String str = null;
				while ((str = bReader.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

	}
}
