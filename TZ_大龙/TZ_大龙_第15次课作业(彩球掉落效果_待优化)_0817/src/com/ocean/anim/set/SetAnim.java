package com.ocean.anim.set;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class SetAnim {

	/**
	 * 利用AnimatorSet同时执行动画
	 * @param view
	 */
	public static void startAnim(View view){
		ObjectAnimator obj1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f,0.5f,1.0f);
		ObjectAnimator obj2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f,0.5f,1.0f);
		ObjectAnimator obj3 = ObjectAnimator.ofFloat(view, "rotationX", 0.0f,180.0f,0.0f);
		AnimatorSet set = new AnimatorSet();
		set.setDuration(2000);
		set.setInterpolator(new LinearInterpolator());
		set.playTogether(obj1,obj2,obj3);
		set.start();
	}
	
	/**
	 * 利用AnimatorSet分步骤执行动画
	 * @param view
	 */
	public static void startAnimSync(View view){
		ObjectAnimator obj1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f,0.5f,1.0f);
		ObjectAnimator obj2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f,0.5f,1.0f);
		ObjectAnimator obj3 = ObjectAnimator.ofFloat(view, "rotationX", 0.0f,180.0f,0.0f);
		AnimatorSet set = new AnimatorSet();
		set.setDuration(2000);
		set.setInterpolator(new LinearInterpolator());
		set.play(obj2).after(obj3);  //obj2在obj3后面执行
		set.play(obj1).after(obj2);  //obj1在obj2后面执行
		//依次执行 3->2->1 各执行2秒，共6秒
		
		set.start();
	}
}
