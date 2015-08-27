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
		//��ʾ��һ��view:1.͸����2.���ţ�3.�ײ�Ϊԭ����з�ת��4.����ƽ��Ч��
		//rotation��ת ��alpha ͸��  ��scale ���ţ� translationƽ��
				
				//2.����
				ObjectAnimator animator=ObjectAnimator.ofFloat(iv, "scaleX",1.0f,0.8f);
				animator.setDuration(300);				
				ObjectAnimator animator2= ObjectAnimator.ofFloat(iv, "scaleY", 1.0f,0.8f);
				animator2.setDuration(300);
				
				//alpha ͸��
				ObjectAnimator animator3=ObjectAnimator.ofFloat(iv, "alpha", 1.0f,0.8f);
				animator3.setDuration(200);
				//rotation��ת
				ObjectAnimator animator4=ObjectAnimator.ofFloat(iv, "rotationX", 15f,0.0f);
				animator4.setDuration(200);
				ObjectAnimator animator7=ObjectAnimator.ofFloat(iv, "rotationX", 0.0f,15.0f);
				animator7.setDuration(200);
				animator7.setStartDelay(200);
				//translationƽ��
				ObjectAnimator animator5=ObjectAnimator.ofFloat(iv, "translationY", 0.0f,-0.1f*height);
				animator5.setDuration(300);
				//translationƽ��
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
	//��ʾ��һ��view:1.͸����2.���ţ�3.�ײ�Ϊԭ����з�ת��4.����ƽ��Ч��
	//rotation��ת ��alpha ͸��  ��scale ���ţ� translationƽ��
	
	//2.����
	ObjectAnimator animator=ObjectAnimator.ofFloat(iv, "scaleX",0.8f,1.0f);
	animator.setDuration(300);				
	ObjectAnimator animator2= ObjectAnimator.ofFloat(iv, "scaleY", 0.8f,1.0f);
	animator2.setDuration(300);
	
	//alpha ͸��
	ObjectAnimator animator3=ObjectAnimator.ofFloat(iv, "alpha", 0.8f,1.0f);
	animator3.setDuration(200);
	//rotation��ת
	ObjectAnimator animator4=ObjectAnimator.ofFloat(iv, "rotationX", 0.0f,15f);
	animator4.setDuration(200);
	ObjectAnimator animator7=ObjectAnimator.ofFloat(iv, "rotationX", 15.0f,0);
	animator7.setDuration(200);
	animator7.setStartDelay(200);
	//translationƽ��
	ObjectAnimator animator5=ObjectAnimator.ofFloat(iv, "translationY", -0.1f*height,0);
	animator5.setDuration(300);
	//translationƽ��
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