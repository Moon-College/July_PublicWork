package com.tz.l2.qqzonelayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tz_qqzonelayout.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);
	}
	
	public void work1(View view){
		Intent intent=new Intent(this,ZoneLayout.class);
		startActivity(intent);
	}
	
	public void work2(View view){
		Intent intent=new Intent(this,LayoutByCode.class);
		startActivity(intent);
	}

	public void work3(View view){
		Intent intent=new Intent(this,TableLayoutByCode.class);
		startActivity(intent);
	}
}
