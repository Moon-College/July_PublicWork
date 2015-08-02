package com.tz.lsn3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

	private View btn_homework1;
	private View btn_homework2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_homework1 = findViewById(R.id.btn_homework1);
		btn_homework2 = findViewById(R.id.btn_homework2);
		
		btn_homework1.setOnClickListener(this);
		btn_homework2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
			case R.id.btn_homework1:
				Intent hw1Intent = new Intent(this, Homework1Activity.class);
				startActivity(hw1Intent);
				break;
				
			case R.id.btn_homework2:
				Intent hw2Intent = new Intent(this, Homework2Activity.class);
				startActivity(hw2Intent);
				break;
			default:
				break;
		}
	}


}
