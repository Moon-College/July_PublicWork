package com.qingzhu.log4j;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String LOG4j = "Info";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i(LOG4j, "helloWorld");
		Log.i(LOG4j, "bye bye");
		
		Button button = (Button)findViewById(R.id.click);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<String> cmdLineList = new ArrayList<String>();
				//���������
				cmdLineList.add("logcat");
				cmdLineList.add("-d");
				cmdLineList.add("-s");
				cmdLineList.add(LOG4j);
				String[] str = new String[cmdLineList.size()];
				
				try {
	                //ͨ�������д���һ������
					Process process = Runtime.getRuntime().exec(cmdLineList.toArray(str));
					InputStream in = process.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuffer strBuffer = new StringBuffer();
					String logString = null;
					//��ȡ����
					while(null != (logString = reader.readLine())) {
						strBuffer.append(logString);
						strBuffer.append("\n");
					}
					//���ַ���������ת��Ϊ�ַ���
					logString = strBuffer.toString();
					//�ж��ַ����Ƿ�Ϊ��
					if(!logString.isEmpty()) {
						Toast.makeText(v.getContext(), logString, Toast.LENGTH_LONG).show();
					}
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
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
