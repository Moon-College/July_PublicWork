package com.example.view_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Scale_animation extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scale_main);
		
		ImageView scale = (ImageView)findViewById(R.id.scale);
		
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
		scale.startAnimation(animation);
	}
}
