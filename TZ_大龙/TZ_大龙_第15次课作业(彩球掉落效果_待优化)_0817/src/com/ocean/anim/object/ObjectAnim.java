package com.ocean.anim.object;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ObjectAnim {
	
	/**
	 * 根据属性值设置不同的属性动画
	 * @param view  属性的所有者
 	 * @param property  属性名称
	 * @param from  初始值
	 * @param to	结束值
	 */
	public static void startObj(View view,String property,float from , float to){
		ObjectAnimator.ofFloat(view, property, from , to)
						.setDuration(500)
						.start();
	}
	
	/**
	 * 通过ObjectAnimator实现多动画同时操作
	 * @param view	 要操作的view
	 * @param from	 初始值
	 * @param to	 结束值
	 */
	public static void startObj(final View view,float from ,float to){
		//这里将属性设为ocean是以为Android不认识这个属性，所以不会做任何改变，而我们要用from和to，所以就这样设定。
		ObjectAnimator anim = ObjectAnimator.ofFloat(view, "ocean", from , to);//这里的参数可以设置三个，就是先小后大了。
		anim.setDuration(2000);
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float x = (Float) animation.getAnimatedValue();
				view.setAlpha(x);
				view.setScaleX(x);
				view.setScaleY(x);
			}
		});
	}
	
	/**
	 * 使用PropertyValuesHolder来实现ObjectAnimator的多属性动画
	 * @param view	要操作的控件
	 */
	public static void startObj(View view){
		//这里可以加上对rotation的判断
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX",1.0f, 0.0f,1.0f),//这里的参数设置三个，先小后大。
                PropertyValuesHolder.ofFloat("scaleY", 1.0f,0.0f,1.0f),
                PropertyValuesHolder.ofFloat("alpha", 1.0f,0.0f,1.0f),
                PropertyValuesHolder.ofFloat("rotation", 0.0f,270.0f,0.0f)
        ).setDuration(2000);
        animator.setInterpolator(new LinearInterpolator()); //作何用我暂时还不知道
        animator.start();
	}
}
