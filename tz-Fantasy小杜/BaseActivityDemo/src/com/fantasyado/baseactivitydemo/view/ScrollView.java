package com.fantasyado.baseactivitydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * created at:2015年8月2日 上午7:53:01 project name:BaseActivityDemo
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:ScrollView.java description:
 */

public class ScrollView extends HorizontalScrollView {

	private int screenWidth;
	private int screenHeight;
	View menu, main;
    public ScrollView(Context context) {
    	super(context);
    	screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }
	public ScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		screenHeight = context.getResources().getDisplayMetrics().heightPixels;

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub

		LinearLayout ll_header = (LinearLayout) this.getChildAt(0);
		int headHeight = ll_header.getLayoutParams().height;
		screenHeight = screenHeight - headHeight;
		LinearLayout ll = (LinearLayout) this.getChildAt(0);

		menu = ll.getChildAt(0);
		main = ll.getChildAt(1);
		menu.getLayoutParams().width = screenWidth - 200;
		main.getLayoutParams().width = screenWidth;
		menu.getLayoutParams().height = screenHeight;
		main.getLayoutParams().height = screenHeight;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		this.smoothScrollTo(screenWidth - 200, 0);

	}

	public boolean onTouchEvent(MotionEvent ev) {
		float downX = 0;
		float upX = 0;
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = ev.getX();
			break;
		case MotionEvent.ACTION_UP:
			upX = ev.getY();
			if (upX - downX < 0) {
				this.smoothScrollTo(screenWidth - 200, 0);
			} else {
				this.smoothScrollTo(0, 0);
			}
			break;
		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		float scale = l / (screenWidth - 200);
		float menuScale = 1 - 0.3f * scale;
		float mainScale = (float) (0.8 + 0.2 * scale);

		menu.setScaleX(menuScale);
		menu.setScaleY(menuScale);
		main.setScaleX(mainScale);
		main.setScaleY(mainScale);

		menu.setAlpha(1 - scale);
		menu.setTranslationX(l * 0.8f);

	}

}
