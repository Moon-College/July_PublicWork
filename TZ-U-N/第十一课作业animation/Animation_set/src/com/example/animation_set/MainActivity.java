package com.example.animation_set;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//向上动画
		ImageView imageView = (ImageView) findViewById(R.id.tranlate_one);	
	    Animation animation = AnimationUtils.loadAnimation(this, R.anim.translte);
	    imageView.startAnimation(animation);
	
	    ImageView tranlate_two = (ImageView) findViewById(R.id.tranlate_two);	
	    Animation animationbottom = AnimationUtils.loadAnimation(this, R.anim.translate_main);
	    tranlate_two.startAnimation(animationbottom);
	    
	    ImageView alpha_main = (ImageView) findViewById(R.id.image);	
	    Animation animation_anim = AnimationUtils.loadAnimation(this, R.anim.alpha_main);
	    alpha_main.startAnimation(animation_anim);
	    
	    animation_anim.setAnimationListener(new AnimationListener() {
			
			@Override//动画开始
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override//动画重复
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}
			
			@Override//动画结束
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,OtherActivity_animtion.class));
				overridePendingTransition(R.anim.eitx_main, R.anim.etix_animation);
			}
		});
	}

	

}
