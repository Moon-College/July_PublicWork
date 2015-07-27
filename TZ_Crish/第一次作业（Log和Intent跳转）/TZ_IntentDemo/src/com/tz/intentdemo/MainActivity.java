package com.tz.intentdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//设置跳转到打电话界面的按钮事件
		((Button) findViewById(R.id.btn_call_phone)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
//				//可以传值到下个Activity里
				//我在此就无需传值测试了
//				Bundle bundle = new Bundle();
//				bundle.putString("key", "value"); //设置需要传递的字符串类型。当然还可以传递其他类型的(如object，List之类的)
//				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		//设置跳转到发短信界面的按钮事件
		((Button) findViewById(R.id.btn_send_sms)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				//系统默认的action，用来打开默认的短信界面
				intent.setAction(Intent.ACTION_SENDTO);
				//需要发短息的号码
				intent.setData(Uri.parse("smsto:"));
				startActivity(intent);
			}
		});
	}

	
}
