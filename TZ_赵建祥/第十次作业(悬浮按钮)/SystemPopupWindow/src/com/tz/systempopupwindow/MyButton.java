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
 * 桌面悬浮按钮
 * @author 赵建祥
 *
 */
public class MyButton extends Button {
	private ViewManager mWindowManager;
	private WindowManager.LayoutParams lps;
	
	private int chaX=0;
	private int chaY=0;
	private int downX=0;//按下时X绝对坐标
	private int downY=0;//按下时Y绝对坐标
	private Context context;

	public MyButton(Context context, ViewManager mWindowManager,
			WindowManager.LayoutParams lps) {
		super(context);
		this.context=context;
		this.mWindowManager = mWindowManager;
		this.lps = lps;
		//设置绿色背景
		this.setBackgroundColor(Color.GREEN);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//按下时，记录触摸点在屏幕的坐标
			downX=(int) event.getRawX();
			downY=(int) event.getRawY();
			//按钮左上角坐标与触摸点之间的差值
			chaX=(int) (lps.x-event.getRawX());
			chaY=(int) (lps.y-((event.getRawY() 
					- getStatusBarHeight())));
		}
		//移动点，按钮跟着移动
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			lps.x = (int) event.getRawX()+chaX;
			lps.y = (int) (event.getRawY() - getStatusBarHeight())+chaY;
			mWindowManager.updateViewLayout(this, lps);
		}
		//手指抬起时，如果坐标和按下时坐标一样，则认为是点击事件
		else if(event.getAction() == MotionEvent.ACTION_UP){
			int upX=(int) event.getRawX();
			int upY=(int) event.getRawY();
			if(upX==downX&&upY==downY){
				//按钮被点击返回首页
				Intent i = new Intent(Intent.ACTION_MAIN);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addCategory(Intent.CATEGORY_HOME);
				context.startActivity(i);
			}
		}
		return false;
	}
	

	/**
	 * 获取状态栏的高度
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
