package com.ws.log;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_call, btn_log, btn_sms;
	private EditText edt_call;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		Log.e("TAG", "error日志");
		Log.i("TAG", "info日志");

	}

	// 初始化控件
	private void initView() {
		btn_log = (Button) findViewById(R.id.btn_log);
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_sms = (Button) findViewById(R.id.btn_sms);
		edt_call = (EditText) findViewById(R.id.edt_call);
		btn_log.setOnClickListener(this);
		btn_call.setOnClickListener(this);
		btn_sms.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_call:
			Phone(edt_call.getText().toString());
			break;
		case R.id.btn_sms:
			Messages(edt_call.getText().toString());
			break;
		case R.id.btn_log:
			saveLog();
			break;
		}

	}

	// 打电话
	public void Phone(String phonenNumber) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ phonenNumber));
		startActivity(intent);

	}
	// 跳到发短信界面
	public void Messages(String phonenNumber) {
		Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:"
				+ phonenNumber));
		startActivity(intent);
	}
	public void saveLog(){
		ArrayList<String> al = new ArrayList<String>();
		al.add("logcat");
		al.add("-d");
		al.add("-s");
		al.add("TAG");
		try{
			Process process = Runtime.getRuntime().exec(al.toArray(new String[al.size()]));
			InputStream is = process.getInputStream();
			Reader reader = new InputStreamReader(is);
			StringBuffer sb = new StringBuffer();
			BufferedReader bufferedReader = new BufferedReader(reader);
			String str = null;
			while ((str=bufferedReader.readLine())!=null) {
				sb.append(str );
				sb.append("\n");
			}
			Toast.makeText(MainActivity.this, sb.toString(), 1).show();
		}catch(Exception e){
			
		}
		
	}

}
