package com.tz.qqlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private Button mQqLayoutBtn; //仿QQ布局按钮
	private Button mDyncLayoutBtn; //代码动态生成布局按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mQqLayoutBtn = (Button) findViewById(R.id.btn_qqlayout);
		mDyncLayoutBtn = (Button) findViewById(R.id.btn_dynclayout);
		
		//设置监听事件
		setOnListener();
	}

	private void setOnListener() {
		mQqLayoutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, QqLayoutActivity.class));
			}
		});
		
		mDyncLayoutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, DyncLayoutActivity.class));
			}
		});
		
	}

	
}
