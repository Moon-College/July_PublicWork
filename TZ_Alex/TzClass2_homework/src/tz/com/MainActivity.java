package tz.com;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_save, btn_phone, btn_sms;
	private ArrayList<String> logArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化控件
		init();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_phone = (Button) findViewById(R.id.btn_phone);
		btn_sms = (Button) findViewById(R.id.btn_sms);
		// 设置监听事件
		btn_save.setOnClickListener(this);
		btn_phone.setOnClickListener(this);
		btn_sms.setOnClickListener(this);
		
		logArray = new ArrayList<String>();
		//生成log日志
		Log.i("TAG", "我是info");
		Log.e("TAG", "我是error");
		Log.v("TAG", "我是verbose");
		Log.w("TAG", "我是warning");
		Log.d("TAG", "我是debug");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//获取日志
		case R.id.btn_save:
			logArray.add("logcat");
			logArray.add("-d");  // 筛选重复数据只收集一次
			logArray.add("-s");  //过滤日志信息
			logArray.add("TAG");  //过滤条件
			try {
				Process process = Runtime.getRuntime().exec(logArray.toArray(new String[logArray.size()]));
				InputStream is = process.getInputStream();
				//将字节流转成字符流
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String str = null;
				StringBuffer sb = new StringBuffer();
				while((str = br.readLine())!=null){
					sb.append(str);
					sb.append("\n");
				}
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		//跳转到打电话
		case R.id.btn_phone:
			Intent intentPhone = new Intent();
			intentPhone.setAction("android.intent.action.CALL_BUTTON");
			startActivity(intentPhone);
			break;
		//跳转到发信息
		case R.id.btn_sms:
			Intent intentSms = new Intent(Intent.ACTION_VIEW);  
			intentSms.setType("vnd.android-dir/mms-sms"); 
			startActivity(intentSms);
			break;
		default:
			break;
		}
	}
}
