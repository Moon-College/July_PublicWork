package com.example.view_animation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获得图片资源
	ImageView image =(ImageView)findViewById(R.id.image);
		//加载动画工具类
	Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
	image.startAnimation(animation);
	//渐变动画的效果，从部透明到透明
	
	}
}
