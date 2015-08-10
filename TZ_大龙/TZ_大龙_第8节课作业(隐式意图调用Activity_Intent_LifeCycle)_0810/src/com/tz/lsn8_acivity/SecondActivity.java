package com.tz.lsn8_acivity;

import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends Activity {
	public static final int RESULT_CODE = 112;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		View view = null;
		try {
			view = LayoutInflater.from(this).createView("sec", "sec", null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InflateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建一个Linearlayout
		LinearLayout linearLayout = new LinearLayout(this);
		//设置竖起方向
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		//创建一个属性
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		//将属性加载到LinearLayout
		linearLayout.setLayoutParams(layoutParams);
		
		//创建一个TextView
		TextView tv = new TextView(this);
		tv.setText(intent.getExtras().getString("tag"));
		LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvParams.setMargins(100, 100, 100, 100);
		tv.setLayoutParams(tvParams);
		tv.setTextSize(20);
		tv.setGravity(Gravity.CENTER);
		tv.setLayoutParams(tvParams);
		
		
		//将TextView加载到LinearLayout中
		linearLayout.addView(tv);
		
		//设置视图
		setContentView(linearLayout);
		
		//设置返回结果
		setResult(RESULT_CODE, intent);
	}
}
