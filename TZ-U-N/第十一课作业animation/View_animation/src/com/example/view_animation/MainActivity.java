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
		//���ͼƬ��Դ
	ImageView image =(ImageView)findViewById(R.id.image);
		//���ض���������
	Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
	image.startAnimation(animation);
	//���䶯����Ч�����Ӳ�͸����͸��
	
	}
}
