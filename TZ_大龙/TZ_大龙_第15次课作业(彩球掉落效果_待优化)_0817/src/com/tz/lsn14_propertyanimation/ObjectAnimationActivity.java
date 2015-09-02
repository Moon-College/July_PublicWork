package com.tz.lsn14_propertyanimation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.tz.lsn15_propertyanimation.R;

public class ObjectAnimationActivity extends Activity {

    private ImageView iv_anim;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        iv_anim = (ImageView)findViewById(R.id.iv_objAnim);
        /*
         * 需要3.0以上版本才有这些方法
         * setAlpha setTranslationX setScaleX setRotation setRotationX setRotationY
         */
        iv_anim.setAlpha(0.5F);
    }
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		btnClick(iv_anim);
	}
	
	public void btnClick(View view) {
		// TODO Auto-generated method stub
		iv_anim.setVisibility(View.VISIBLE);
		
		/**
		 * alpha 0.0F, 1.0F	渐显效果
		 * rotation	0.0F, 360.0F	旋转效果
		 * rotationX 0.0F, 360.0F	依据X轴转
		 * rotationY 0.0F, 360.0F	依据Y轴转
		 * translationX 0F, 200F	向X轴方向移动200
		 * translationY 0F, 200F	向Y轴方向移动200
		 * scaleX	1.0F, 2.0F	向X轴2个方向从1倍到2倍的效果 
		 * scaleY	1.0F, 2.0F	向Y轴2个方向从1倍到2倍的效果 
		 **/
		
		/*
		  调用setInterpolator设置曲线函数
			setInterpolator(new BounceInterpolator())
			setInterpolator(new LinearInterpolator());
			setInterpolator(new AccelerateInterpolator());
			setInterpolator(new DecelerateInterpolator());
			setInterpolator(new ArgbEvaluator());
			
		 */
		
		/* 1.单个动画执行 ***********************************/
//		ObjectAnimator animator = ObjectAnimator.ofFloat(iv_anim, "dallon", 0F,1F); //dallon随意写， 不起作用，属性不会发生改变,但会计算时间
//		animator.setDuration(200);
//		//animator.setScaleX(1F);
//		animator.start();
		
		/* 2.多个动画同时执行******************************** */
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//			
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				// TODO Auto-generated method stub
//				//获得
//				float value = (Float) animation.getAnimatedValue();
//
//				//按钮效果 按下变小再变大  属性值要与上面传进来的对应 
//				iv_anim.setScaleX(Math.max(0.8f, value));
//				iv_anim.setScaleY(Math.max(0.8f, value));
//			}
//		});
		
		/* 3.多个动画同时执行( PropertyValuesHolder )*********************************** */
//		PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f, 1.0f);
//		PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("rotation", 0.0f, 360f, 1.0f);
//		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 2.0f, 1.0f);
//		ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv_anim, holder1, holder2, holder3);
//		animator.setDuration(2000);
//		animator.start();
		
		/* 4 .多个动画同时执行************************************ */
//		ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 360.0f, 0.0f);
//		animator.setDuration(1000);
//		animator.start();
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//			
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				// TODO Auto-generated method stub
//				float value = (Float)animation.getAnimatedValue();
//				iv_anim.setRotation(value);
//			}
//		});
		
		/* 5.多个动画同时执行 ，实现线效果******************************** */
		System.out.println(">>>>5.多个动画同时执行 ，实现线效果");
		final int duringSecond = 3; /* 4秒*/
		final int speed = 350;  /* 横向移动速度  300px/s */
		final int speedPlus = 600;  /* 加速度 */
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(duringSecond * 1000);
		//不知道这是起什么作用
		valueAnimator.setInterpolator(new AccelerateInterpolator());
		//这个必须设置
		//valueAnimator.setFloatValues(values) == valueAnimator.ofFloatValues
		valueAnimator.setObjectValues(new PointF(0.0f, 0.0f));
		//设置百分比计算器
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

			@Override
			public PointF evaluate(float fraction, PointF startValue,
					PointF endValue) {
				// TODO Auto-generated method stub
				
				//计算抛物线XY轴的轨迹  fraction为百分数
				PointF pointF = new PointF();
				
				//X轴：假定移动速度为100px/s  s = v*t    百分比x总时间=>花费时间
				pointF.x = speed * (fraction * duringSecond);
				//Y轴： s = (1/2)*g*(t*t)
				pointF.y = 0.5f * speedPlus * (fraction * duringSecond) * (fraction * duringSecond);
				
				return pointF;
			}
		});
		
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				PointF pointF = (PointF) animation.getAnimatedValue();
				iv_anim.setX(pointF.x);
				iv_anim.setY(pointF.y);
			}
		});
		
		//启动
		valueAnimator.start();
		
		//设置监听事件 方法一 (可选需要的实现)
		valueAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				//删除元素
//				ViewGroup parent = (ViewGroup) iv_anim.getParent();
//				parent.removeView(iv_anim);
				
				//或者设置为不可见
				iv_anim.setVisibility(View.GONE);
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//设置监听事件 方法二
//		valueAnimator.addListener(new AnimatorListener() {
//			
//			@Override
//			public void onAnimationStart(Animator animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animator animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationCancel(Animator animation) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
	}

}
