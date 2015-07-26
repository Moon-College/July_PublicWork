package com.tz.l1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn_saveLog,btn_call,btn_sms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findView();
		setOnClick();
		Log.i("Info", "123456789");
		
	}
	//注册组件
	private void findView(){
		btn_saveLog = (Button) findViewById(R.id.btn_saveLog);
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_sms = (Button) findViewById(R.id.btn_sms);
		
	}
	//设置监听
	private void setOnClick(){
		btn_saveLog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<String> cmdLine = new ArrayList<String>();
				cmdLine.add("logcat");
				//采集一次日志
				cmdLine.add("-d");
				//过滤
				cmdLine.add("-s");
				cmdLine.add("Info");
				try {
					Process pro = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
					InputStream is = pro.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String str = null;
					StringBuffer sb = new StringBuffer();
					while((str=br.readLine())!=null){
						sb.append(str);
						sb.append("\n");
					}
					Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
					
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btn_call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//跳转到打电话界面
				Intent intent = new Intent(Intent.ACTION_CALL);
				
				MainActivity.this.startActivity(intent); 
			}
		});
		
		
		btn_sms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//跳转到发送信息界面
				Intent intent = new Intent(Intent.ACTION_SENDTO);  
				   
				MainActivity.this.startActivity(intent);  
			}
		});
		
	}
}
