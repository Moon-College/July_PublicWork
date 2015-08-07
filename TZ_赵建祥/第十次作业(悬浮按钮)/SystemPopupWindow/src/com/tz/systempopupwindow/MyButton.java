package com.tz.systempopupwindow;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.Button;

/**
 * ����������ť
 * @author �Խ���
 *
 */
public class MyButton extends Button {
	private ViewManager mWindowManager;
	private WindowManager.LayoutParams lps;
	
	private int chaX=0;
	private int chaY=0;
	private int downX=0;//����ʱX��������
	private int downY=0;//����ʱY��������
	private Context context;

	public MyButton(Context context, ViewManager mWindowManager,
			WindowManager.LayoutParams lps) {
		super(context);
		this.context=context;
		this.mWindowManager = mWindowManager;
		this.lps = lps;
		//������ɫ����
		this.setBackgroundColor(Color.GREEN);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//����ʱ����¼����������Ļ������
			downX=(int) event.getRawX();
			downY=(int) event.getRawY();
			//��ť���Ͻ������봥����֮��Ĳ�ֵ
			chaX=(int) (lps.x-event.getRawX());
			chaY=(int) (lps.y-((event.getRawY() 
					- getStatusBarHeight())));
		}
		//�ƶ��㣬��ť�����ƶ�
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			lps.x = (int) event.getRawX()+chaX;
			lps.y = (int) (event.getRawY() - getStatusBarHeight())+chaY;
			mWindowManager.updateViewLayout(this, lps);
		}
		//��ָ̧��ʱ���������Ͱ���ʱ����һ��������Ϊ�ǵ���¼�
		else if(event.getAction() == MotionEvent.ACTION_UP){
			int upX=(int) event.getRawX();
			int upY=(int) event.getRawY();
			if(upX==downX&&upY==downY){
				//��ť�����������ҳ
				Intent i = new Intent(Intent.ACTION_MAIN);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addCategory(Intent.CATEGORY_HOME);
				context.startActivity(i);
			}
		}
		return false;
	}
	

	/**
	 * ��ȡ״̬���ĸ߶�
	 * @return
	 */
	private int getStatusBarHeight() {
		int statusBarHeight=0;
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object o = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = (Integer) field.get(o);
			statusBarHeight = getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("jzhao", "statusBarHeight-->"+statusBarHeight);
		return statusBarHeight;
	}

	public WindowManager.LayoutParams getLps() {
		return lps;
	}

	public void setLps(WindowManager.LayoutParams lps) {
		this.lps = lps;
	}

	
}
