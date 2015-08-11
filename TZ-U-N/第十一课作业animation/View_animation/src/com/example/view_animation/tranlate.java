package com.example.view_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class tranlate extends Activity {

	private ImageView tranlate;
	private ImageView tranlate_tow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tranlate_main);
		tranlate=(ImageView)findViewById(R.id.tranlate_one);
		tranlate_tow=(ImageView)findViewById(R.id.tranlate_two);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.tranlate_anim);
		tranlate.startAnimation(animation);
		tranlate_tow.startAnimation(animation);
		
	}
}
