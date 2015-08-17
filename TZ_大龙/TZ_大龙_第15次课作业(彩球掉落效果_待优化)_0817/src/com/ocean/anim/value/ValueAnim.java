package com.ocean.anim.value;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class ValueAnim {

	
	/**
	 * 设置view的平移动画，可设置X或Y方向的平移
	 * @param view	要平移的view
	 */
	public static void startValue(final View view,final String orientation,float from,float to){
		ValueAnimator anim = ValueAnimator.ofFloat(from,to);
		anim.setTarget(view);
		anim.setDuration(2000);
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (orientation.equals("y")) {
					view.setTranslationY((Float)animation.getAnimatedValue());
				}else{
					view.setTranslationX((Float)animation.getAnimatedValue());
				}
			}
		});
	}
	
	/**
	 * 抛物线
	 * @param view
	 * @param ratio  移动一定的高度需要移动的宽度的比值(由于比值一直无法精确，所以在调用时就放弃了这个值，采用物理公式计算)
	 * @param from
	 * @param to
	 */
	public static void startValue(final View view,final float ratio,float from,float to){
		ValueAnimator anim = ValueAnimator.ofFloat(from,to);
		anim.setTarget(view);
		anim.setDuration(2000);
		anim.setInterpolator(new AccelerateInterpolator());
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();//value的值范围是0-1
				view.setTranslationX(200 * value * 3);//看不懂不用纠结，这是物理公式，回想下抛物线的x，y位移计算公式吧
				view.setTranslationY(0.5f * 200 * value*3 * value * 3);
			}
		});
		
	}
	
}
