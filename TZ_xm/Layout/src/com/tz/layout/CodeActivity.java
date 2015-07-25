package com.tz.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CodeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Layout
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(params);

		// editText
		EditText et = new EditText(this);
		LinearLayout.LayoutParams et_params = new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(et_params);
		et.setSingleLine();	// 限制单行
		et.setText("美女");

		// Button
		Button btn = new Button(this);
		LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btn_params);
		btn.setText("search");

		// 将子控件加入布局
		ll.addView(et);
		ll.addView(btn);

		setContentView(ll);
	}

}
