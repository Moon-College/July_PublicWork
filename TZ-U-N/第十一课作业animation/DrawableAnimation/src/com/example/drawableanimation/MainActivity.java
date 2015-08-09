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
			//�õ�����ǿת��֡����
		mAnimationDrawable=(AnimationDrawable)image.getBackground();
	//��Ҫһ����activity��ʱ�򣬾�����֡����
		//start��������������onCreate����
	image.post(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mAnimationDrawable.start();
		}
	});
	}
	

	//�����ý��� ������һ�����ͻ�ý���
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		mAnimationDrawable.start();//����֡��������
		super.onWindowFocusChanged(hasFocus);
	}


	public void action(View view ){
		if(mAnimationDrawable.isRunning())
		mAnimationDrawable.start();
		
	}

	public void stop(View view){
		if(mAnimationDrawable.isRunning())
		mAnimationDrawable.stop();//ֹͣ����
		
	}
}
