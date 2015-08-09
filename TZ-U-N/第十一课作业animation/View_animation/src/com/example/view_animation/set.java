package com.example.view_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class set extends Activity {

	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		
			
			super.onCreate(savedInstanceState);
		setContentView(R.layout.set_main);
		ImageView imageView = (ImageView)findViewById(R.id.set);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_aimn);
		imageView.startAnimation(animation);
		
		
		
		}
}
