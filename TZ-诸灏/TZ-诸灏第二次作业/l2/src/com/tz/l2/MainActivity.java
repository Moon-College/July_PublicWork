package com.tz.l2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	
	private LinearLayout layout;
	private EditText reaserch;
	private Button btn_serch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new LinearLayout(this);
		LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setLayoutParams(params);
		
		reaserch = new EditText(this);
		LinearLayout.LayoutParams editParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
		editParams.weight= 1f;
		reaserch.setLayoutParams(editParams);
		
		
		btn_serch = new Button(this);
		LinearLayout.LayoutParams btnParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
		btnParams.weight = 2f;
		btn_serch.setLayoutParams(btnParams);
		
		layout.addView(reaserch);
		layout.addView(btn_serch);
		setContentView(layout);
	}
	
	

}
