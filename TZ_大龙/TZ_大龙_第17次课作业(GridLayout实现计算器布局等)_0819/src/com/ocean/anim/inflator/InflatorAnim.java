package com.ocean.anim.inflator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class InflatorAnim {

	/**
	 * 通过AnimatorInflator加载动画
	 * @param context
	 * @param id
	 * @param view
	 */
	public static void startAnim(Context context ,int id,View view){
		Animator animator = AnimatorInflater.loadAnimator(context, id);
		animator.setTarget(view);
		animator.setDuration(2000);
		animator.setInterpolator(new LinearInterpolator());
		animator.start();
	}
}
