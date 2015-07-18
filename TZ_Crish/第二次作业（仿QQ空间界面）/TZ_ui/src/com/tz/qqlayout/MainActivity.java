package com.tz.qqlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private Button mQqLayoutBtn; //��QQ���ְ�ť
	private Button mDyncLayoutBtn; //���붯̬���ɲ��ְ�ť

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mQqLayoutBtn = (Button) findViewById(R.id.btn_qqlayout);
		mDyncLayoutBtn = (Button) findViewById(R.id.btn_dynclayout);
		
		//���ü����¼�
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
