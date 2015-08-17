package com.ocean.anim.other;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import com.ocean.anim.other.OtherActivity.ViewWrapper;

public class OtherAnim {

	/**
	 * 仅仅这样设置，无法改变button的宽
	 * @param view
	 */
	public static void startAnim(View view){
		ObjectAnimator.ofInt(view, "width", 500).setDuration(5000).start();
	}
	
	/**
	 * 通过一个包装类来实现对button的改变，set方法里需要重绘界面
	 * @param view
	 * @param from
	 * @param to
	 */
	public static void startAnim(ViewWrapper view,int from , int to){
		ObjectAnimator.ofInt(view, "width", from, to).setDuration(2000).start();
	}
	
	/**
	 * 通过ValueAnimator和一个估值器来改变button的宽度。由于button的setWidth不是设置当前宽度，而是设置最小和最大宽度（继承自TextView），所以我们要
	 * 用view.getLayoutParams().width方式来改变宽度，然后强制重绘界面。
	 * @param view
	 * @param start
	 * @param end
	 */
	@SuppressLint("NewApi")
	public static void changeWidthByValue(final View view,final int start, final int end){
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);

        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

            private IntEvaluator mEvaluator = new IntEvaluator();

			@Override
            public void onAnimationUpdate(ValueAnimator animator) {
				
                float currentValue = (Float)animator.getAnimatedValue();
                view.getLayoutParams().width = mEvaluator.evaluate(currentValue, start, end);
                view.requestLayout();
            }
        });

        valueAnimator.setDuration(2000).start();
	}
	
	
	/**
	 * 通过一个包装类来实现对button的改变，set方法里需要重绘界面，动画执行完毕后可以让view消失掉
	 * @param view
	 * @param from
	 * @param to
	 */
	public static void startAnim(final ViewWrapper view,int from , int to , boolean isDisappear){
		ObjectAnimator animator = ObjectAnimator.ofInt(view, "width", from, to);
		animator.setDuration(2000);
		
		if (isDisappear) {
			animator.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// do nothing
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// do nothing
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					ViewGroup parent = (ViewGroup) view.getView().getParent();
					if (parent != null)
						parent.removeView(view.getView());
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					onAnimationEnd(animation);
				}
			});
		}
		
		animator.start();
	}
	
	
	/**
	 * 动画执行完毕后可以让view消失掉,使用一个较为简单的监听器
	 * @param view
	 * @param from
	 * @param to
	 */
	public static void startAnimAndDisappear(final View view,float from , float to){
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", from, to);
		animator.setDuration(2000);
		
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				ViewGroup parent = (ViewGroup) view.getParent();
				if (parent != null) {
					parent.removeView(view);
				}
			}
		});
		
		animator.start();
	}

}
