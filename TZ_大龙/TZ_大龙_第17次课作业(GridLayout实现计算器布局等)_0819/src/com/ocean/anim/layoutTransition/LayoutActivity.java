package com.ocean.anim.layoutTransition;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;

import com.tz.lsn15_propertyanimation.R;

public class LayoutActivity extends Activity implements OnCheckedChangeListener, OnClickListener {

	private Button btn_add;
	private CheckBox cb_appear;
	private CheckBox cb_change_appear;
	private CheckBox cb_disappear;
	private CheckBox cb_change_disappear;
	private CheckBox cb_change;
	private GridLayout gl_wrapper;
	private LayoutTransition transition;
	private int flag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);
		findView();
		setOnChecked();
		initTransition();
	}

	private void findView() {
		btn_add = (Button) findViewById(R.id.btn_add);
		cb_appear = (CheckBox) findViewById(R.id.cb_appear);
		cb_change_appear = (CheckBox) findViewById(R.id.cb_change_appear);
		cb_disappear = (CheckBox) findViewById(R.id.cb_disappear);
		cb_change_disappear = (CheckBox) findViewById(R.id.cb_change_disappear);
		cb_change = (CheckBox) findViewById(R.id.cb_change);
		gl_wrapper = (GridLayout) findViewById(R.id.gl_wrapper);
	}
	
	private void setOnChecked() {
		cb_appear.setOnCheckedChangeListener(this);
		cb_change_appear.setOnCheckedChangeListener(this);
		cb_disappear.setOnCheckedChangeListener(this);
		cb_change_disappear.setOnCheckedChangeListener(this);
		cb_change.setOnCheckedChangeListener(this);
		btn_add.setOnClickListener(this);
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		initTransition();
	}
	
	@SuppressLint("NewApi")
	private void initTransition(){
		
		transition = new LayoutTransition();//这里注意，如果想让GridLayout重新设置动画，必须新建一个LayoutTrasition。
		
		transition.setAnimator(LayoutTransition.APPEARING, 
				cb_appear.isChecked() ? LayoutAnim.getTransition(transition, 
						ObjectAnimator.ofFloat(this, "scaleX", 0, 1) ,LayoutTransition.APPEARING ): null);
		transition.setAnimator(LayoutTransition.CHANGE_APPEARING, 
				cb_change_appear.isChecked() ? transition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null);
		transition.setAnimator(LayoutTransition.DISAPPEARING, 
				cb_disappear.isChecked() ? LayoutAnim.getTransition(transition,
						ObjectAnimator.ofFloat(this, "rotationX", 0.0f,360.0f), LayoutTransition.DISAPPEARING): null);
		transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, 
				cb_change_disappear.isChecked() ? transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null);
		transition.setAnimator(LayoutTransition.CHANGING, 
				cb_change.isChecked() ? transition.getAnimator(LayoutTransition.CHANGING) : null);
		
		transition.setDuration(500);
		
		gl_wrapper.setLayoutTransition(transition);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_add:
				final Button button = new Button(this);
				button.setText(String.valueOf(++flag));
				gl_wrapper.addView(button, Math.min(1, gl_wrapper.getChildCount()));
				button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						gl_wrapper.removeView(button);
					}
				});
				break;
			default:
				break;
		}
	}
}
