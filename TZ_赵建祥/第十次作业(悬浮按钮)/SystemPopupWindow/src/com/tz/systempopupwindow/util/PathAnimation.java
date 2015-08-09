package com.tz.systempopupwindow.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class PathAnimation {

	/**
	 * ��ת�˵���ť
	 * @param fromDegrees ��ʼ�Ƕ�
	 * @param toDegrees �����Ƕ�
	 * @param duration ����ʱ��
	 * @return
	 */
	public static Animation rotateMenu(float fromDegrees,float toDegrees,long duration){
		RotateAnimation animation=new RotateAnimation(
				fromDegrees, 
				toDegrees, 
				Animation.RELATIVE_TO_SELF, //
				0.5f, 
				Animation.RELATIVE_TO_SELF, 
				0.5f);
		animation.setDuration(duration);
		return animation;
	}

	/**
	 * �����˵��б���
	 * @param v �˵��б�����
	 * @param duration ����ʱ��
	 */
	public static void showMenuList(View v,long duration) {
		RelativeLayout menuLayout=(RelativeLayout)v;
		if(menuLayout.getChildCount()>0){
			for(int i=0;i<menuLayout.getChildCount();i++){
				ImageButton menu=(ImageButton) menuLayout.getChildAt(i);
				menu.setVisibility(View.VISIBLE);
				
				RelativeLayout.LayoutParams lps=(android.widget.RelativeLayout.LayoutParams) menu.getLayoutParams();
				//����Ļ���Ƶ���ǰλ��
				TranslateAnimation animation=new TranslateAnimation(
						Animation.ABSOLUTE, 
						lps.rightMargin, 
						Animation.ABSOLUTE, 
						0f, 
						Animation.ABSOLUTE, 
						lps.bottomMargin,
						Animation.ABSOLUTE, 
						0f);
				animation.setDuration(duration);
				animation.setStartTime((i*200));
				//�Ӹ����������ջ�Ч��
				animation.setInterpolator(new OvershootInterpolator(2f+(i*2f)));
				menu.startAnimation(animation);
				
			}
		}
	}
	
	/**
	 * ���ز˵��б���
	 * @param v �˵��б�����
	 * @param duration ����ʱ��
	 */
	public static void hideMenuList(View v,long duration) {
		RelativeLayout menuLayout=(RelativeLayout)v;
		if(menuLayout.getChildCount()>0){
			for(int i=0;i<menuLayout.getChildCount();i++){
				ImageButton menu=(ImageButton) menuLayout.getChildAt(i);
				RelativeLayout.LayoutParams lps=(android.widget.RelativeLayout.LayoutParams) menu.getLayoutParams();
				//����Ļ���Ƶ���ǰλ��
				TranslateAnimation animation=new TranslateAnimation(
						Animation.ABSOLUTE, 
						0f, 
						Animation.ABSOLUTE, 
						lps.rightMargin, 
						Animation.ABSOLUTE, 
						0f,
						Animation.ABSOLUTE, 
						lps.bottomMargin
						);
				animation.setDuration(duration);
				animation.setStartTime(((menuLayout.getChildCount()-i)*200));
				//�Ӹ����������ջ�Ч��
				animation.setInterpolator(new AnticipateInterpolator(2f+((menuLayout.getChildCount()-i)*2f)));
				menu.startAnimation(animation);
				menu.setVisibility(View.GONE);
			}
		}
	}
}
