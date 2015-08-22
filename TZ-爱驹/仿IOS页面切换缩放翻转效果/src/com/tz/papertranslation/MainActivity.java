package com.tz.papertranslation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author wztscau
 * @lastedit 2015年8月21日
 *
 */
public class MainActivity extends Activity {

	private LinearLayout mainView;
	private LinearLayout writeView;
	private int mainViewHeight;
	private int writeViewHeight;
	private TextView tv_duration;
	private static final int DURATION = 200;// generally 200,but you can change
											// it higher or lower
	private static final float SCALE = 0.6f;// I think 0.6f would be better
	private static int duration = DURATION;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainView = (LinearLayout) findViewById(R.id.mainView);
		writeView = (LinearLayout) findViewById(R.id.writeView);
		tv_duration = (TextView) findViewById(R.id.tv_duration);
		tv_duration.setText("0.2s");
		mainView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						mainViewHeight = mainView.getHeight();
						mainView.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
		writeView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() { 
						writeViewHeight = writeView.getHeight();
						writeView.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
	}

	/**
	 * begin to show the animator of two views
	 * 
	 * @param v
	 */
	public void onShow(View v) {
		// there are three steps in the whole transformation,alpha is
		// the first and second steps
		ObjectAnimator alpha = ObjectAnimator.ofFloat(mainView, "alpha", 1f,
				0.5f).setDuration((duration + duration / 2) * 2);
		// scaleX and scaleY is the second step
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(mainView, "scaleX", 1f,
				SCALE).setDuration(duration + duration / 2);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mainView, "scaleY", 1f,
				SCALE).setDuration(duration + duration / 2);
		scaleX.setStartDelay(duration);
		scaleY.setStartDelay(duration);
		// rotationX is the first step
		ObjectAnimator rotationX = ObjectAnimator.ofFloat(mainView,
				"rotationX", 0, 10f).setDuration(duration);
		// rotationXResume is the second step
		ObjectAnimator rotationXResume = ObjectAnimator.ofFloat(mainView,
				"rotationX", 10f, 0).setDuration(duration + duration / 2);
		rotationXResume.setStartDelay(duration);
		// set this interpolator will look smoother
		rotationX.setInterpolator(new LinearInterpolator());
		rotationXResume.setInterpolator(new LinearInterpolator());
		// translationY is the last(third) step
		ObjectAnimator translationY = ObjectAnimator.ofFloat(mainView,
				"translationY", 0, -0.1f * mainViewHeight)
				.setDuration(duration);
		translationY.setStartDelay(duration * 2 + duration / 2);
		// this is another view's transformation, totally one step
		// that's the translationY to make view visible or invisible
		ObjectAnimator translationY2 = ObjectAnimator.ofFloat(writeView,
				"translationY", writeViewHeight, 0).setDuration(
				duration * 2 + duration / 2);

		// begin to start the animators
		AnimatorSet set = new AnimatorSet();
		set.playTogether(alpha, scaleX, scaleY, rotationX, rotationXResume,
				translationY, translationY2);
		set.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				writeView.setVisibility(View.VISIBLE);
				// mainView and it's child can not be edited
				mainView.setEnabled(false);
				setViewEnabled(mainView, false);
				setViewEnabled(writeView, false);
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				setViewEnabled(writeView, true);
			}
		});
		set.start();
	}

	/**
	 * the reversible animator of the show action
	 * 
	 * @param v
	 */
	public void onResume(View v) {
		ObjectAnimator translationY = ObjectAnimator.ofFloat(mainView,
				"translationY", -0.1f * mainViewHeight, 0)
				.setDuration(duration);
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(mainView, "scaleX",
				SCALE, 1f).setDuration(duration + duration / 2);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mainView, "scaleY",
				SCALE, 1f).setDuration(duration + duration / 2);
		scaleX.setStartDelay(duration);
		scaleY.setStartDelay(duration);
		ObjectAnimator alpha = ObjectAnimator.ofFloat(mainView, "alpha", 0.5f,
				1f).setDuration((duration + duration / 2) * 2);
		alpha.setStartDelay(duration);
		ObjectAnimator rotationXResume = ObjectAnimator.ofFloat(mainView,
				"rotationX", 0, 10f).setDuration(duration + duration / 2);
		rotationXResume.setStartDelay(duration);
		ObjectAnimator rotationX = ObjectAnimator.ofFloat(mainView,
				"rotationX", 10f, 0).setDuration(duration);
		rotationX.setStartDelay(duration * 2 + duration / 2);
		rotationX.setInterpolator(new LinearInterpolator());
		rotationXResume.setInterpolator(new LinearInterpolator());
		ObjectAnimator translationY2 = ObjectAnimator.ofFloat(writeView,
				"translationY", 0, writeViewHeight).setDuration(
				duration * 2 + duration / 2);
		AnimatorSet set = new AnimatorSet();
		set.playTogether(alpha, scaleX, scaleY, rotationX, rotationXResume,
				translationY, translationY2);
		set.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				setViewEnabled(writeView, false);
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				writeView.setVisibility(View.INVISIBLE);
				// mainView and it's child can be edited
				mainView.setEnabled(true);
				setViewEnabled(mainView, true);
			}
			
		});
		set.start();
	}
	
	/**
	 * 递归函数，遍历父View下所有的view，如果是ViewGroup，则继续找下去
	 * @param v 父容器
	 * @param b 是否可操作
	 */
	private static void setViewEnabled(ViewGroup v,boolean b) {
		for (int i = 0; i < v.getChildCount(); i++) {
			View child = v.getChildAt(i);
			child.setEnabled(b);
			if(child instanceof ViewGroup){
				setViewEnabled((ViewGroup)child, b);
			}
		}
	}
	
	/**
	 * 让动画加快速度的动作
	 * @param v
	 */
	public void onPlus(View v){
		String tmp = (String) tv_duration.getText();
		tmp = tmp.substring(0, tmp.length()-1);
		if(tmp.equals("5.0")){
			return;
		}
		duration +=100;
		tv_duration.setText((float)duration / 1000+"s");
	}
	
	/**
	 * 让动画减慢速度的动作
	 * @param v
	 */
	public void onMinus(View v){
		String tmp = (String) tv_duration.getText();
		tmp = tmp.substring(0, tmp.length()-1);
		if(tmp.equals("0.1")){
			return;
		}
		duration -=100;
		tv_duration.setText((float)duration / 1000+"s");
	}
}
