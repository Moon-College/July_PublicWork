package com.ws.androidapp_ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		LinearLayout ll = new LinearLayout(this);
//		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//		ll.setLayoutParams(lp);
//		ll.setOrientation(LinearLayout.VERTICAL);
//		TextView tv = new TextView(this);
//		tv.setText("你好啊！！！！！！！！！！！！");
//		LayoutParams ld = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//		tv.setLayoutParams(ld);
//		ll.addView(tv);
//		Button bt = new Button(this);
//		bt.setLayoutParams(ld);
//		bt.setText("一个按钮");
//		ll.addView(bt);
//		setContentView(ll);
		setContentView(R.layout.main);
	}

 
	
}

