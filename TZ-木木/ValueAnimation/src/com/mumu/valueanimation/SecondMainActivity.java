package com.mumu.valueanimation;

import android.R.id;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SecondMainActivity extends Activity {
	
	private LinearLayout firstLayout;
	private LinearLayout secondLayout;
	private Button showButton;
	private Button hiddenButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_main);
		firstLayout = (LinearLayout) this.findViewById(R.id.first);
		secondLayout = (LinearLayout) this.findViewById(R.id.second);
		showButton = (Button) this.findViewById(R.id.show);
		hiddenButton = (Button) this.findViewById(R.id.hidden);
	}
	
	
	public void showAnimator(View v) {
		int height = firstLayout.getHeight();

		//
		ObjectAnimator beginRotation = ObjectAnimator.ofFloat(firstLayout, "rotationX", 0f, 10f);
		beginRotation.setDuration(200);
		
		ObjectAnimator endRotation = ObjectAnimator.ofFloat(firstLayout, "rotationX", 10f, 0f);
		endRotation.setDuration(200);
		
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(firstLayout, "scaleX", 1f, 0.85f);
		scaleX.setDuration(400);
		
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(firstLayout, "scaleY", 1f, 0.85f);
		scaleY.setDuration(400);
		
		ObjectAnimator alpha = ObjectAnimator.ofFloat(firstLayout, "alpha", 1f, 0.5f);
		alpha.setDuration(500);
		
		ObjectAnimator translationY = ObjectAnimator.ofFloat(secondLayout, "translationY", height, 0);
		translationY.setDuration(500);
		secondLayout.setVisibility(View.VISIBLE);
		
		AnimatorSet set = new AnimatorSet();
		
		set.play(beginRotation).with(scaleX).with(scaleY).with(alpha).with(translationY);
		set.play(endRotation).after(beginRotation);
		set.start();
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				showButton.setEnabled(false);
				hiddenButton.setEnabled(true);
			}
		});
	}
	
	public void hiddenAnimator(View v) {
		int height = firstLayout.getHeight();

		ObjectAnimator beginRotation = ObjectAnimator.ofFloat(firstLayout, "rotationX", 0f, 10f);
		beginRotation.setDuration(200);
		
		ObjectAnimator endRotation = ObjectAnimator.ofFloat(firstLayout, "rotationX", 10f, 0f);
		endRotation.setDuration(200);
		
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(firstLayout, "scaleX", 0.85f, 1f);
		scaleX.setDuration(400);
		
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(firstLayout, "scaleY", 0.85f, 1f);
		scaleY.setDuration(400);
		
		ObjectAnimator alpha = ObjectAnimator.ofFloat(firstLayout, "alpha", 0.5f, 1f);
		alpha.setDuration(500);
		
		ObjectAnimator translationY = ObjectAnimator.ofFloat(secondLayout, "translationY", 0, height);
		translationY.setDuration(500);
		secondLayout.setVisibility(View.VISIBLE);
		
		AnimatorSet set = new AnimatorSet();
		
		set.play(beginRotation).with(scaleX).with(scaleY).with(alpha).with(translationY);
		set.play(beginRotation).before(endRotation);
		set.start();
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				secondLayout.setVisibility(View.GONE);
				showButton.setEnabled(true);
				hiddenButton.setEnabled(false);
			}
		});
	}

}
