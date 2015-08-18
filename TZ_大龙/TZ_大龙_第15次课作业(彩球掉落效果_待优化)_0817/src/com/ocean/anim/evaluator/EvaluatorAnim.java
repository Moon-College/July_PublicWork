package com.ocean.anim.evaluator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class EvaluatorAnim {

	
	/**
	 * ������
	 * @param view	��Ҫִ�ж�����view
	 */
	public static void startValue(final View view){

		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(2000);
		valueAnimator.setObjectValues(new PointF(0, 0));
		valueAnimator.setInterpolator(new LinearInterpolator());
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>(){
			// fraction = t / duration  ȡֵ��Χ0-1,����0-2000ȡֵ����ͷβ����2000.
			@Override
			public PointF evaluate(float fraction, PointF startValue,PointF endValue){
				PointF point = new PointF();
				point.x = 200 * fraction * 3;
				point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
				return point;
			}
		});

		valueAnimator.start();
		valueAnimator.addUpdateListener(new AnimatorUpdateListener(){
			@Override
			public void onAnimationUpdate(ValueAnimator animation){
				PointF point = (PointF) animation.getAnimatedValue();
				view.setX(point.x);
				view.setY(point.y);
			}
		});
	}
	
	/**
	 * ʹ��InTValuator�����б任��ע�����ַ���ֻ�ʺ�ValueAnimator����Ϊ����ִ�в��漰Getter��Setter������
	 * @param view
	 */
	public static void startValue2(final View view){
		ValueAnimator anim = new ValueAnimator();
		anim.setDuration(1000);
		anim.setObjectValues(0.0f,360.0f);
		anim.setEvaluator(new FloatEvaluator());
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				view.setRotationX((Float)animation.getAnimatedValue());
			}
		});
	}
}
