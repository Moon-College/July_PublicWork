package com.tz.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void codeLayout(View v) {
		startActivity(new Intent(this, CodeLayoutActivity.class));
	}
	
	public void createLayout(View v) {
		startActivity(new Intent(this, CreateLayoutActivity.class));
	}
	
	public void qqZone(View v) {
		startActivity(new Intent(this, QqZoneActivity.class));
	}
}
