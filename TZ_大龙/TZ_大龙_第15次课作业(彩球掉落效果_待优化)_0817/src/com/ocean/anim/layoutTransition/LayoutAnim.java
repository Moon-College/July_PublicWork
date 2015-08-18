package com.ocean.anim.layoutTransition;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;

public class LayoutAnim {

	/**
	 * 根据传递过来的动画类型返回相应的动画，或者返回自定义动画
	 * @param transition
	 * @param anim
	 * @param type
	 * @return
	 */
	@SuppressLint("NewApi")
	public static Animator getTransition(LayoutTransition transition ,Animator anim , int type){
		Animator animator = null;
		if (anim == null) {
			if (type == LayoutTransition.APPEARING) {
				animator = transition.getAnimator(LayoutTransition.APPEARING);
			}else if (type == LayoutTransition.CHANGE_APPEARING) {
				animator = transition.getAnimator(LayoutTransition.CHANGE_APPEARING);
			}else if (type == LayoutTransition.DISAPPEARING) {
				animator = transition.getAnimator(LayoutTransition.DISAPPEARING);
			}else if (type == LayoutTransition.CHANGE_DISAPPEARING) {
				animator = transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING);
			}else {
				animator = transition.getAnimator(LayoutTransition.CHANGING);
			}
		}else{
			animator = anim;
		}
		return animator;
	}
}
