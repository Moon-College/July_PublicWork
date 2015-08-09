package com.studio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TweenAnimationActivity extends Activity {
	
	private ImageView iv_tween;
	private Animation alphaAnimation;
	private Animation scaleAnimation;
	private Animation rotateAnimation;
	private Animation translateAnimation;
	private Animation setAnimation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween_animation_activity);
		iv_tween = (ImageView) findViewById(R.id.iv_tween);
		
		
		

	}
	
	//透明动画
	public void alphaClick(View view){
		iv_tween.setBackgroundResource(R.drawable.start_top);
		alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
		iv_tween.startAnimation(alphaAnimation);
	}
	//伸缩动画
	public void scaleClick(View view){
		iv_tween.setBackgroundResource(R.drawable.start_top);
		scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
		iv_tween.startAnimation(scaleAnimation);
	}
	//旋转动画
	public void rotateClick(View view){
		iv_tween.setBackgroundResource(R.drawable.start_top);
		rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
		iv_tween.startAnimation(rotateAnimation);
	}
	//位移动画
	public void translateClick(View view){
		iv_tween.setBackgroundResource(R.drawable.start_top);
		translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
		iv_tween.startAnimation(translateAnimation);
	}
	
	public void setAnimationClick(View view){
		iv_tween.setBackgroundResource(R.drawable.start_top);
		setAnimation = AnimationUtils.loadAnimation(this, R.anim.set_animation);
		iv_tween.startAnimation(setAnimation);
		setAnimation.setAnimationListener(new MyAnimationListener());
	}
	
	class MyAnimationListener  implements AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Intent i=new Intent(TweenAnimationActivity.this,ResultActivity.class);
			startActivity(i);
			overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	

}
