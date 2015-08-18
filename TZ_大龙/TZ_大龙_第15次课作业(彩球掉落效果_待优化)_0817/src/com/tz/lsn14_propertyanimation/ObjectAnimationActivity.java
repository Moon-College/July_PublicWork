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
         * ��Ҫ3.0���ϰ汾������Щ����
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
		 * alpha 0.0F, 1.0F	����Ч��
		 * rotation	0.0F, 360.0F	��תЧ��
		 * rotationX 0.0F, 360.0F	����X��ת
		 * rotationY 0.0F, 360.0F	����Y��ת
		 * translationX 0F, 200F	��X�᷽���ƶ�200
		 * translationY 0F, 200F	��Y�᷽���ƶ�200
		 * scaleX	1.0F, 2.0F	��X��2�������1����2����Ч�� 
		 * scaleY	1.0F, 2.0F	��Y��2�������1����2����Ч�� 
		 **/
		
		/*
		  ����setInterpolator�������ߺ���
			setInterpolator(new BounceInterpolator())
			setInterpolator(new LinearInterpolator());
			setInterpolator(new AccelerateInterpolator());
			setInterpolator(new DecelerateInterpolator());
			setInterpolator(new ArgbEvaluator());
			
		 */
		
		/* 1.��������ִ�� ***********************************/
//		ObjectAnimator animator = ObjectAnimator.ofFloat(iv_anim, "dallon", 0F,1F); //dallon����д�� �������ã����Բ��ᷢ���ı�,�������ʱ��
//		animator.setDuration(200);
//		//animator.setScaleX(1F);
//		animator.start();
		
		/* 2.�������ͬʱִ��******************************** */
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//			
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				// TODO Auto-generated method stub
//				//���
//				float value = (Float) animation.getAnimatedValue();
//
//				//��ťЧ�� ���±�С�ٱ��  ����ֵҪ�����洫�����Ķ�Ӧ 
//				iv_anim.setScaleX(Math.max(0.8f, value));
//				iv_anim.setScaleY(Math.max(0.8f, value));
//			}
//		});
		
		/* 3.�������ͬʱִ��( PropertyValuesHolder )*********************************** */
//		PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f, 1.0f);
//		PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("rotation", 0.0f, 360f, 1.0f);
//		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 2.0f, 1.0f);
//		ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv_anim, holder1, holder2, holder3);
//		animator.setDuration(2000);
//		animator.start();
		
		/* 4 .�������ͬʱִ��************************************ */
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
		
		/* 5.�������ͬʱִ�� ��ʵ����Ч��******************************** */
		System.out.println(">>>>5.�������ͬʱִ�� ��ʵ����Ч��");
		final int duringSecond = 3; /* 4��*/
		final int speed = 350;  /* �����ƶ��ٶ�  300px/s */
		final int speedPlus = 600;  /* ���ٶ� */
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(duringSecond * 1000);
		//��֪��������ʲô����
		valueAnimator.setInterpolator(new AccelerateInterpolator());
		//�����������
		//valueAnimator.setFloatValues(values) == valueAnimator.ofFloatValues
		valueAnimator.setObjectValues(new PointF(0.0f, 0.0f));
		//���ðٷֱȼ�����
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

			@Override
			public PointF evaluate(float fraction, PointF startValue,
					PointF endValue) {
				// TODO Auto-generated method stub
				
				//����������XY��Ĺ켣  fractionΪ�ٷ���
				PointF pointF = new PointF();
				
				//X�᣺�ٶ��ƶ��ٶ�Ϊ100px/s  s = v*t    �ٷֱ�x��ʱ��=>����ʱ��
				pointF.x = speed * (fraction * duringSecond);
				//Y�᣺ s = (1/2)*g*(t*t)
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
		
		//����
		valueAnimator.start();
		
		//���ü����¼� ����һ (��ѡ��Ҫ��ʵ��)
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
				//ɾ��Ԫ��
//				ViewGroup parent = (ViewGroup) iv_anim.getParent();
//				parent.removeView(iv_anim);
				
				//��������Ϊ���ɼ�
				iv_anim.setVisibility(View.GONE);
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//���ü����¼� ������
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
