package com.tz.papertranslation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * @author wztscau
 * @lastedit 2015Äê8ÔÂ20ÈÕ
 *
 */
public class MainActivity extends Activity {

	private LinearLayout mainView;
	private LinearLayout writeView;
	private int mainViewHeight;
	private int writeViewHeight;
	private static final int DURATION = 200;// generally 200,but you can change
											// it higher or lower
	private static final float SCALE = 0.6f;// I think 0.6f would be better

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainView = (LinearLayout) findViewById(R.id.mainView);
		writeView = (LinearLayout) findViewById(R.id.writeView);
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
				0.5f).setDuration((DURATION + DURATION / 2) * 2);
		// scaleX and scaleY is the second step
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(mainView, "scaleX", 1f,
				SCALE).setDuration(DURATION + DURATION / 2);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mainView, "scaleY", 1f,
				SCALE).setDuration(DURATION + DURATION / 2);
		scaleX.setStartDelay(DURATION);
		scaleY.setStartDelay(DURATION);
		// rotationX is the first step
		ObjectAnimator rotationX = ObjectAnimator.ofFloat(mainView,
				"rotationX", 0, 10f).setDuration(DURATION);
		// rotationXResume is the second step
		ObjectAnimator rotationXResume = ObjectAnimator.ofFloat(mainView,
				"rotationX", 10f, 0).setDuration(DURATION + DURATION / 2);
		rotationXResume.setStartDelay(DURATION);
		// set this interpolator will look smoother
		rotationX.setInterpolator(new LinearInterpolator());
		rotationXResume.setInterpolator(new LinearInterpolator());
		// translationY is the last(third) step
		ObjectAnimator translationY = ObjectAnimator.ofFloat(mainView,
				"translationY", 0, -0.1f * mainViewHeight)
				.setDuration(DURATION);
		translationY.setStartDelay(DURATION * 2 + DURATION / 2);
		// this is another view's transformation, totally one step
		// that's the translationY to make view visible or invisible
		ObjectAnimator translationY2 = ObjectAnimator.ofFloat(writeView,
				"translationY", writeViewHeight, 0).setDuration(
				DURATION * 2 + DURATION / 2);
		translationY2.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationStart(Animator animation) {
				writeView.setVisibility(View.VISIBLE);
				//mainView and it's child can not be edited
				mainView.setEnabled(false);
				for(int i=0;i<mainView.getChildCount();i++){
					mainView.getChildAt(i).setEnabled(false);
				}
			}
		});

		// begin to start the animators
		AnimatorSet set = new AnimatorSet();
		set.playTogether(alpha, scaleX, scaleY, rotationX, rotationXResume,
				translationY, translationY2);
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
				.setDuration(DURATION);
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(mainView, "scaleX",
				SCALE, 1f).setDuration(DURATION + DURATION / 2);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mainView, "scaleY",
				SCALE, 1f).setDuration(DURATION + DURATION / 2);
		scaleX.setStartDelay(DURATION);
		scaleY.setStartDelay(DURATION);
		ObjectAnimator alpha = ObjectAnimator.ofFloat(mainView, "alpha", 0.5f,
				1f).setDuration((DURATION + DURATION / 2) * 2);
		alpha.setStartDelay(DURATION);
		ObjectAnimator rotationXResume = ObjectAnimator.ofFloat(mainView,
				"rotationX", 0, 10f).setDuration(DURATION + DURATION / 2);
		rotationXResume.setStartDelay(DURATION);
		ObjectAnimator rotationX = ObjectAnimator.ofFloat(mainView,
				"rotationX", 10f, 0).setDuration(DURATION);
		rotationX.setStartDelay(DURATION * 2 + DURATION / 2);
		rotationX.setInterpolator(new LinearInterpolator());
		rotationXResume.setInterpolator(new LinearInterpolator());
		ObjectAnimator translationY2 = ObjectAnimator.ofFloat(writeView,
				"translationY", 0, writeViewHeight).setDuration(
				DURATION * 2 + DURATION / 2);
		translationY2.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				writeView.setVisibility(View.INVISIBLE);
				//mainView and it's child can be edited
				mainView.setEnabled(true);
				for(int i=0;i<mainView.getChildCount();i++){
					mainView.getChildAt(i).setEnabled(true);
				}
			}
		});
		AnimatorSet set = new AnimatorSet();
		set.playTogether(alpha, scaleX, scaleY, rotationX, rotationXResume,
				translationY, translationY2);
		set.start();
	}
}
