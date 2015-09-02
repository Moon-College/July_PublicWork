package com.tz.lsn15_propertyanimation.interpolator;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import com.tz.lsn15_propertyanimation.R;

public class InterpolatorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interpolator);
		
	}
	
	public void runAnimation(View v){
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0,300);
		animator.setDuration(2000);
//		animator.setInterpolator(new AccelerateInterpolator(5));
//		animator.setInterpolator(new DecelerateInterpolator());
//		animator.setInterpolator(new AccelerateDecelerateInterpolator());
//		animator.setInterpolator(new OvershootInterpolator(5));
//		animator.setInterpolator(new BounceInterpolator());
//		animator.setInterpolator(new CycleInterpolator(4));
//		animator.setInterpolator(new AnticipateOvershootInterpolator());
		animator.setInterpolator(new AnticipateInterpolator());
		animator.start();
	}

}
