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
	 * �����������ã��޷��ı�button�Ŀ�
	 * @param view
	 */
	public static void startAnim(View view){
		ObjectAnimator.ofInt(view, "width", 500).setDuration(5000).start();
	}
	
	/**
	 * ͨ��һ����װ����ʵ�ֶ�button�ĸı䣬set��������Ҫ�ػ����
	 * @param view
	 * @param from
	 * @param to
	 */
	public static void startAnim(ViewWrapper view,int from , int to){
		ObjectAnimator.ofInt(view, "width", from, to).setDuration(2000).start();
	}
	
	/**
	 * ͨ��ValueAnimator��һ����ֵ�����ı�button�Ŀ�ȡ�����button��setWidth�������õ�ǰ��ȣ�����������С������ȣ��̳���TextView������������Ҫ
	 * ��view.getLayoutParams().width��ʽ���ı��ȣ�Ȼ��ǿ���ػ���档
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
	 * ͨ��һ����װ����ʵ�ֶ�button�ĸı䣬set��������Ҫ�ػ���棬����ִ����Ϻ������view��ʧ��
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
	 * ����ִ����Ϻ������view��ʧ��,ʹ��һ����Ϊ�򵥵ļ�����
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
