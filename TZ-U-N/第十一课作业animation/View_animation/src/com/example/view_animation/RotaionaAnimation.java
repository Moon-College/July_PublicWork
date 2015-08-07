package com.example.view_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class RotaionaAnimation extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.rotate);
	//初始化图片
	ImageView imageView = (ImageView)findViewById(R.id.rotate);
	//animation 动画工具类
	Animation animation =AnimationUtils.loadAnimation(this, R.anim.rotate_main);
	//启动动画
	imageView.startAnimation(animation);
}
}
