package com.tz.lsn15_propertyanimation.CoolSafari;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tz.lsn15_propertyanimation.R;

public class CoolSafariAnimationActivity extends Activity {

	private LinearLayout ll_main;
	private LinearLayout ll_sub;

	private int mainView_height;
	private int mainView_width;

	private int subView_height;
	private int subView_width;

	private int screenWidth;
	private int screenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_cool_safari);
		ll_main = (LinearLayout)findViewById(R.id.ll_main);
		ll_sub = (LinearLayout)findViewById(R.id.ll_sub);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		mainView_height = ll_main.getHeight();
		mainView_width = ll_main.getWidth();

		subView_height = ll_sub.getHeight();
		subView_width = ll_sub.getWidth();

		screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
	}



	public void btnClick(View v) {
		switch (v.getId()) {
			case R.id.bt_show:
				startAnimationShow(v);
				break;
			case R.id.bt_hide:
				startAnimationHide(v);
				break;
			default:
				break;
		}
	}

	/**
	 * 隐藏触发
	 * @param v
	 */
	private void startAnimationHide(View v) {
		//X轴方向缩小为0.8倍
		ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(ll_main,"scaleX",0.8f,1.0f);
		fViewScaleXAnim.setDuration(350);

		//Y轴方向缩小为0.8倍
		ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(ll_main,"scaleY",0.8f,1.0f);
		fViewScaleYAnim.setDuration(350);

		//变成半透明
		ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(ll_main,"alpha",0.5f,1.0f);
		fViewAlphaAnim.setDuration(350);

		//先沿X轴旋转10度 经历150ms
		ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(ll_main, "rotationX", 0.0f, -10.0f);
		fViewRotationXAnim.setDuration(150);

		//再反向旋转10度 回去 经历200ms
		ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(ll_main, "rotationX", -10.0f, 0.0f);
		fViewResumeAnim.setDuration(200);
		fViewResumeAnim.setStartDelay(150);

		//Y轴向上移动0.1f* fHeight  移动的距离=scaleY绽放大小的一半 即 （1.0f-0.8f）*mainView_height/2 = 0.1 * mainView_height
		ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(ll_main,"translationY", 0.1f * mainView_height, 0.0f);
		fViewTransYAnim.setDuration(350);

		//Y轴向上移动的同时 隐藏的第二个LinearLayout同时显示出来
		ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(ll_sub,"translationY", 0.0f, subView_height);
		sViewTransYAnim.setDuration(350);

		//设置监听事件，开始
		sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationStart(animation);
				ll_sub.setVisibility(View.INVISIBLE);
			}
		});

		AnimatorSet showAnim = new AnimatorSet();
		showAnim.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim, fViewAlphaAnim, fViewScaleYAnim, sViewTransYAnim);
		showAnim.start();
	}

	/**
	 * 显示触发
	 * @param v
	 */
	private void startAnimationShow(View v) {
		//X轴方向缩小为0.8倍
		ObjectAnimator fViewScaleXAnim = ObjectAnimator.ofFloat(ll_main,"scaleX",1.0f,0.8f);
		fViewScaleXAnim.setDuration(350);

		//Y轴方向缩小为0.8倍
		ObjectAnimator fViewScaleYAnim = ObjectAnimator.ofFloat(ll_main,"scaleY",1.0f,0.8f);
		fViewScaleYAnim.setDuration(350);

		//变成半透明
		ObjectAnimator fViewAlphaAnim = ObjectAnimator.ofFloat(ll_main,"alpha",1.0f,0.5f);
		fViewAlphaAnim.setDuration(350);

		//先沿X轴旋转10度 经历200ms
		ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(ll_main, "rotationX", 0f, 10f);
		fViewRotationXAnim.setDuration(200);

		//再反向旋转10度 回去 经历150ms
		ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(ll_main, "rotationX", 10f, 0f);
		fViewResumeAnim.setDuration(150);
		fViewResumeAnim.setStartDelay(200);

		//Y轴向上移动0.1f* fHeight  移动的距离=scaleY绽放大小的一半 即 （1.0f-0.8f）*mainView_height/2 = 0.1 * mainView_height
		ObjectAnimator fViewTransYAnim = ObjectAnimator.ofFloat(ll_main,"translationY", 0, -0.1f * mainView_height);
		fViewTransYAnim.setDuration(350);

		//Y轴向上移动的同时 隐藏的第二个LinearLayout同时显示出来
		ObjectAnimator sViewTransYAnim = ObjectAnimator.ofFloat(ll_sub,"translationY",subView_height, 0);
		sViewTransYAnim.setDuration(350);

		//设置监听事件，开始
		sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				ll_sub.setVisibility(View.VISIBLE);
			}
		});

		AnimatorSet showAnim = new AnimatorSet();
		showAnim.playTogether(fViewScaleXAnim, fViewRotationXAnim, fViewResumeAnim, fViewTransYAnim, fViewAlphaAnim, fViewScaleYAnim, sViewTransYAnim);
		showAnim.start();
	}

}
