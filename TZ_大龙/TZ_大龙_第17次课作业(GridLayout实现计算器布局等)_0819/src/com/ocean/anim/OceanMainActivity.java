package com.ocean.anim;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ocean.anim.animate.ViewActivity;
import com.ocean.anim.evaluator.EvaluatorAnimActivity;
import com.ocean.anim.inflator.InflatorAnimActivity;
import com.ocean.anim.layoutTransition.LikeTopTags;
import com.ocean.anim.object.ObjectAnimActivity;
import com.ocean.anim.other.OtherActivity;
import com.ocean.anim.set.SetAnimActivity;
import com.ocean.anim.value.ValueAnimActivity;
import com.tz.lsn15_propertyanimation.R;

public class OceanMainActivity extends Activity implements OnClickListener{

	private TextView tv_object;
	private TextView tv_value;
	private TextView tv_set;
	private TextView tv_inflater;
	private TextView tv_evaluator;
	private TextView tv_interpolator;
	private TextView tv_listener;
	private TextView tv_layout;
	private TextView tv_animate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ocean);
		findView();
		setOnClick();
		setViewBack();
		setTextColor();
	}

	private void setTextColor() {
		setBackRepeat(tv_object,"textColor",0XFF8080FF,0XFFFF8080);
		setBackRepeat(tv_inflater,"textColor", 0xff000000, 0xffffffff);
		setBackRepeat(tv_interpolator,"textColor", 0xffededed, 0xdedede);
		setBackRepeat(tv_listener,"textColor", 0xff000000, 0xffffffff);
	}

	private void setViewBack() {
		setBackRepeat(tv_object,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_value,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_set,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_inflater,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_evaluator,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_interpolator,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_listener,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_layout,"backgroundColor",0XFFFF8080,0XFF8080FF);
		setBackRepeat(tv_animate,"backgroundColor",0XFFFF8080,0XFF8080FF);
	}

	private void setOnClick() {
		tv_object.setOnClickListener(this);
		tv_value.setOnClickListener(this);
		tv_set.setOnClickListener(this);
		tv_inflater.setOnClickListener(this);
		tv_evaluator.setOnClickListener(this);
		tv_interpolator.setOnClickListener(this);
		tv_listener.setOnClickListener(this);
		tv_layout.setOnClickListener(this);
		tv_animate.setOnClickListener(this);
	}

	private void findView() {
		tv_object = (TextView) findViewById(R.id.tv_object);
		tv_value = (TextView) findViewById(R.id.tv_value);
		tv_set = (TextView) findViewById(R.id.tv_set);
		tv_inflater = (TextView) findViewById(R.id.tv_inflater);
		tv_evaluator = (TextView) findViewById(R.id.tv_evaluator);
		tv_interpolator = (TextView) findViewById(R.id.tv_interpolator);
		tv_listener = (TextView) findViewById(R.id.tv_listener);
		tv_layout = (TextView) findViewById(R.id.tv_layout);
		tv_animate = (TextView) findViewById(R.id.tv_animate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_object:
			Intent objIntent = new Intent(this,ObjectAnimActivity.class);
			this.startActivity(objIntent);
			break;
		case R.id.tv_value:
			Intent valueIntent = new Intent(this,ValueAnimActivity.class);
			this.startActivity(valueIntent);
			break;
		case R.id.tv_set:
			Intent setIntent = new Intent(this,SetAnimActivity.class);
			this.startActivity(setIntent);
			break;
		case R.id.tv_inflater:
			Intent inflatorIntent = new Intent(this,InflatorAnimActivity.class);
			this.startActivity(inflatorIntent);
			break;
		case R.id.tv_evaluator:
			Intent evaluatorIntent = new Intent(this,EvaluatorAnimActivity.class);
			this.startActivity(evaluatorIntent);
			break;
		case R.id.tv_interpolator:
			
			break;
		case R.id.tv_layout:
			Intent layoutIntent = new Intent(this,LikeTopTags.class);
			this.startActivity(layoutIntent);
			break;
		case R.id.tv_listener:
			Intent otherIntent = new Intent(this,OtherActivity.class);
			this.startActivity(otherIntent);
			break;
		case R.id.tv_animate:
			Intent viewIntent = new Intent(this,ViewActivity.class);
			this.startActivity(viewIntent);
			break;
		default:
			break;
		}
	}
	
	private void setBackRepeat(View view,String property,int from , int to){
		ValueAnimator animator = ObjectAnimator.ofInt(view,property,from,to);
		animator.setDuration(3000);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setEvaluator(new ArgbEvaluator());
		animator.start();
	}
	
}
