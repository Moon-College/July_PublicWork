package com.example.drawableanimation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private AnimationDrawable mAnimationDrawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView image = (ImageView)findViewById(R.id.image);
		image.setBackgroundResource(R.drawable.ation_animation);
			//得到背景强转成帧动画
		mAnimationDrawable=(AnimationDrawable)image.getBackground();
	//需要一进入activity的时候，就启动帧动画
		//start（）方法部能在onCreate调用
	image.post(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mAnimationDrawable.start();
		}
	});
	}
	

	//窗体获得焦点 当窗体一启动就获得焦点
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		mAnimationDrawable.start();//启动帧动画方法
		super.onWindowFocusChanged(hasFocus);
	}


	public void action(View view ){
		if(mAnimationDrawable.isRunning())
		mAnimationDrawable.start();
		
	}

	public void stop(View view){
		if(mAnimationDrawable.isRunning())
		mAnimationDrawable.stop();//停止方法
		
	}
}
