package com.tz.lsn2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button btn_log;
	private Button btn_dail;
	private Button btn_dail2;
	private Button btn_sendmsg;
	private Button btn_sendmsg2;
	
	private EditText et_number;
	private EditText et_smscontent;
	
	private final String TAG = "dallon";
	private Context mCnt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCnt = this;
		
		btn_log = (Button) findViewById(R.id.btn_log);
		btn_dail = (Button) findViewById(R.id.btn_dail);
		btn_dail2 = (Button) findViewById(R.id.btn_dail2);
		btn_sendmsg = (Button) findViewById(R.id.btn_sendmsg);
		btn_sendmsg2 = (Button) findViewById(R.id.btn_sendmsg2);
		
		btn_log.setOnClickListener(this);
		btn_dail.setOnClickListener(this);
		btn_dail2.setOnClickListener(this);
		btn_sendmsg.setOnClickListener(this);
		btn_sendmsg2.setOnClickListener(this);
		
		et_number = (EditText) findViewById(R.id.et_number);
		et_smscontent = (EditText) findViewById(R.id.et_smscontent);
		
		Log.v(TAG, "================我是系统 verbise 信息===========");
		Log.d(TAG, "================我是系统 debug 信息=============");
		Log.i(TAG, "================我是系统 info 信息==============");
		Log.w(TAG, "================我是系统 warn 信息==============");
		Log.e(TAG, "================我是系统 error 信息=============");
		Log.e(TAG, "================我是系统 assert 信息============");
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btn_log:
				
				ArrayList<String> cmdArr = new ArrayList<String>();
				cmdArr.add("logcat");
				cmdArr.add("-d"); //只收集一次
				cmdArr.add("-s"); //过滤
				cmdArr.add(TAG);//过滤标签
				
				String[] cmdString = new String[cmdArr.size()]; 
				
			try {
				Process process = Runtime.getRuntime().exec(cmdArr.toArray(cmdString));
				InputStream is = process.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				String str = null;
				StringBuffer sb = new StringBuffer();
				while((str = br.readLine()) != null) {
					sb.append(str + "\n");
				}
				Toast.makeText(mCnt, sb.toString(), Toast.LENGTH_LONG).show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
				break;
				
			case R.id.btn_dail:
				
				Log.i(TAG, "======" + et_number.getText().toString());
				Toast.makeText(mCnt, et_number.getText().toString(), Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + et_number.getText().toString()));  
                startActivity(intent);
	
				break;
			case R.id.btn_dail2:
				
				Intent intent2 = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + et_number.getText().toString()));  
                startActivity(intent2);
                
                //跳到通话记录
//              Intent intentPhone = new Intent();
//    			intentPhone.setAction("android.intent.action.CALL_BUTTON");
//    			startActivity(intentPhone);
	
				break;	
			case R.id.btn_sendmsg:
				
				//PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, testSms.class), 0);
				 
		        SmsManager sms = SmsManager.getDefault();
		 
		        sms.sendTextMessage(et_number.getText().toString(), null,
					et_smscontent.getText().toString(), null, null);

				break;
				
			case R.id.btn_sendmsg2:
				
				Uri uri = Uri.parse("smsto:" + et_number.getText().toString());
			    Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
			    sendIntent.putExtra("sms_body", et_smscontent.getText().toString());
			    startActivity(sendIntent);
			    
			    //另外一种方法
//			    Intent sendIntent2 = new Intent(Intent.ACTION_VIEW);
//			    sendIntent2.putExtra("sms_body", et_smscontent.getText().toString());
//			    sendIntent2.setType("vnd.android-dir/mms-sms");

				break;
			default:
				break;
		}
	}

}
