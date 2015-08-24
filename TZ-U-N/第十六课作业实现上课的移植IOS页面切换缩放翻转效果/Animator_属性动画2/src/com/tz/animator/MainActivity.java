package com.tz.animator;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;

public class MainActivity extends Activity {

	private View iv;
	private View ib;
	
	private int width;
	private int height;
	private int width2;
	private int height2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=findViewById(R.id.linear);
		ib=findViewById(R.id.linear2);
	
		ib.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			

			@Override
			public void onGlobalLayout() {
				width2 = ib.getWidth();
				height2 = ib.getHeight();
				ib.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		iv.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			


			@Override
			public void onGlobalLayout() {
					width = iv.getWidth();
					height = iv.getHeight();
					iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}

	
	

	@SuppressLint("NewApi")
	public void animator1(View v){
		//显示第一个view:1.透明；2.缩放；3.底部为原点进行翻转；4.向下平移效果
		//rotation旋转 ，alpha 透明  ，scale 缩放， translation平移
				
				//2.缩放
				ObjectAnimator animator=ObjectAnimator.ofFloat(iv, "scaleX",1.0f,0.8f);
				animator.setDuration(300);				
				ObjectAnimator animator2= ObjectAnimator.ofFloat(iv, "scaleY", 1.0f,0.8f);
				animator2.setDuration(300);
				
				//alpha 透明
				ObjectAnimator animator3=ObjectAnimator.ofFloat(iv, "alpha", 1.0f,0.8f);
				animator3.setDuration(200);
				//rotation旋转
				ObjectAnimator animator4=ObjectAnimator.ofFloat(iv, "rotationX", 15f,0.0f);
				animator4.setDuration(200);
				ObjectAnimator animator7=ObjectAnimator.ofFloat(iv, "rotationX", 0.0f,15.0f);
				animator7.setDuration(200);
				animator7.setStartDelay(200);
				//translation平移
				ObjectAnimator animator5=ObjectAnimator.ofFloat(iv, "translationY", 0.0f,-0.1f*height);
				animator5.setDuration(300);
				//translation平移
				ObjectAnimator animator6=ObjectAnimator.ofFloat(ib, "translationY", height2,0.0f);
				animator6.setDuration(300);
				
				animator6.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						ib.setVisibility(View.VISIBLE);
						
					}
					
				});
				AnimatorSet animatorSet =new AnimatorSet();
				animatorSet.playTogether(animator,animator2,animator3,animator4,animator5,animator6,animator7);
				animatorSet.start();
	}



@SuppressLint("NewApi")
public void animator2(View v){
	//显示第一个view:1.透明；2.缩放；3.底部为原点进行翻转；4.向下平移效果
	//rotation旋转 ，alpha 透明  ，scale 缩放， translation平移
	
	//2.缩放
	ObjectAnimator animator=ObjectAnimator.ofFloat(iv, "scaleX",0.8f,1.0f);
	animator.setDuration(300);				
	ObjectAnimator animator2= ObjectAnimator.ofFloat(iv, "scaleY", 0.8f,1.0f);
	animator2.setDuration(300);
	
	//alpha 透明
	ObjectAnimator animator3=ObjectAnimator.ofFloat(iv, "alpha", 0.8f,1.0f);
	animator3.setDuration(200);
	//rotation旋转
	ObjectAnimator animator4=ObjectAnimator.ofFloat(iv, "rotationX", 0.0f,15f);
	animator4.setDuration(200);
	ObjectAnimator animator7=ObjectAnimator.ofFloat(iv, "rotationX", 15.0f,0);
	animator7.setDuration(200);
	animator7.setStartDelay(200);
	//translation平移
	ObjectAnimator animator5=ObjectAnimator.ofFloat(iv, "translationY", -0.1f*height,0);
	animator5.setDuration(300);
	//translation平移
	ObjectAnimator animator6=ObjectAnimator.ofFloat(ib, "translationY", 0,height2);
	animator6.setDuration(300);
	
	animator6.addListener(new AnimatorListenerAdapter() {
		@Override
		public void onAnimationEnd(Animator animation) {
			super.onAnimationEnd(animation);
			ib.setVisibility(View.INVISIBLE);
			
		}
		
	});
	AnimatorSet animatorSet =new AnimatorSet();
	animatorSet.playTogether(animator,animator2,animator3,animator4,animator5,animator6,animator7);
	animatorSet.start();
}
	
	
}