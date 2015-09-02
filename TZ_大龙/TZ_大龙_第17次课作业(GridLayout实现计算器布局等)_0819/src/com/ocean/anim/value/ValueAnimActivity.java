package com.ocean.anim.value;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.tz.lsn15_propertyanimation.R;

public class ValueAnimActivity extends Activity implements OnClickListener{

	private ImageView iv_global;
	private Button btn_one;
	private Button btn_two;
	private int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_value);
		findView();
		getWindowData();
		setOnClick();
	}
	
	private void setOnClick() {
		btn_one.setOnClickListener(this);
		btn_two.setOnClickListener(this);
	}

	private void findView() {
		iv_global = (ImageView) findViewById(R.id.iv_global);
		btn_one = (Button) findViewById(R.id.btn_one);
		btn_two = (Button) findViewById(R.id.btn_two);
	}

	@SuppressWarnings("deprecation")
	private void getWindowData(){
		WindowManager manager = getWindowManager();
		height = manager.getDefaultDisplay().getHeight();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_one:
			System.out.println("height:" + height);
			System.out.println("iv_global.getHeight():" + iv_global.getHeight());
			System.out.println("iv_global.getMeasuredHeight():" + iv_global.getMeasuredHeight());
			ValueAnim.startValue(iv_global, "y", 0, height-iv_global.getHeight()-btn_one.getHeight()-
					iv_global.getMeasuredHeight() - btn_one.getMeasuredHeight());
			break;
		case R.id.btn_two:
			ValueAnim.startValue(iv_global, 0, 0, 1);
			break;

		default:
			break;
		}
	}
}
