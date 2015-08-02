package com.example.qquidemo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class UseCodeDrawActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_use_code_draw);
		LinearLayout ll=new LinearLayout(UseCodeDrawActivity.this);
		LinearLayout.LayoutParams layoutParams=new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.setLayoutDirection(LinearLayout.HORIZONTAL);
		ll.setLayoutParams(layoutParams);
		
		
		EditText editText=new EditText(UseCodeDrawActivity.this);
		LinearLayout.LayoutParams etParams=new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		editText.setLayoutParams(etParams);
		editText.setGravity(5);
		
		Button btn=new Button(UseCodeDrawActivity.this);
		LinearLayout.LayoutParams btnParams=new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setText("我叫按钮");
		btn.setLayoutParams(btnParams);
		btn.setGravity(1);
		
		ll.addView(editText);
		ll.addView(btn);
		setContentView(ll);
	}


}
