package com.ocean.anim.animate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lsn15_propertyanimation.R;

public class ViewActivity extends Activity {

	private TextView tv_rotate;
	private ImageView iv_view;
	private static final String TAG = "View.animate";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		findView();
	}

	@SuppressLint("NewApi")
	private void findView() {
		tv_rotate = (TextView) findViewById(R.id.tv_rotate);
		iv_view = (ImageView) findViewById(R.id.iv_view);
		
		tv_rotate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_view.animate()
						.rotationX(180.f)
						.alpha(0.5f)
						.scaleX(0.5f)
						.scaleY(0.5f)
						.setDuration(2000)
						.setInterpolator(new LinearInterpolator())
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationStart(Animator animation) {}
							@Override
							public void onAnimationRepeat(Animator animation) {}
							
							@Override
							public void onAnimationEnd(Animator animation) {
								Log.e(TAG, "animation end");
								iv_view.setAlpha(1.0f);
								iv_view.setRotationX(0.f);
							}
							
							@Override
							public void onAnimationCancel(Animator animation) {
								onAnimationEnd(animation);
							}
						})
						//API 12
						.withStartAction(new Runnable() {
							@Override
							public void run() {
								Log.e(TAG, "with start action");
							}
						})
						//API 16
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								Log.e(TAG, "with end action");
								iv_view.setScaleX(1.0f);
								iv_view.setScaleY(1.f);
							}
						})
						.start();
//						.setStartDelay(5000)//设置延迟时间
				}
		});
	}
}
