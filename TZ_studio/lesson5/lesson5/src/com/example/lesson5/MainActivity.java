package com.example.lesson5;

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
	
	/**
	 * 
	 * 此方法描述的是：跳转到好逗Activity
	 * @author:  studio 
	 * linkHaoDou
	 * @param view void
	 */
	public void linkHaoDou(View view){
		Intent intent=new Intent(this,HaoDouActivity.class);
		startActivity(intent);
	}
	
	/**
	 * 
	 * 此方法描述的是：跳转到ProfileActivity
	 * @author:  studio
	 * linkHaoDou
	 * @param view void
	 */
	public void linkMyProfile(View view){
		Intent intent=new Intent(this,ProfileListActivity.class);
		startActivity(intent);
	}
}
