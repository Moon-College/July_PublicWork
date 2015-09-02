package com.example.qq_slidingmenu01;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyHorizontalScrollView extends HorizontalScrollView {

	private int widthPixel;
	private int heightPixel;
	private ViewGroup menu;
	private ViewGroup content;
	private int width1;

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		widthPixel = displayMetrics.widthPixels;
		heightPixel = displayMetrics.heightPixels;
//		System.out.println(widthPixel+"==============widthPixel");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		LinearLayout ll = (LinearLayout) this.getChildAt(0);

		menu = (ViewGroup) ll.getChildAt(0);
		content = (ViewGroup) ll.getChildAt(1);
		System.out.println("menuWidth+++++++++++++++++++++"+width1);
		width1  = (int) (0.7 * widthPixel);
		menu.getLayoutParams().width = (int) (0.7 * widthPixel);
		System.out.println(width1+"=============menuWidth==================");
		content.getLayoutParams().width = widthPixel;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(width1, 0);
//			System.out.println("------------------------------------------------------");
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
//		ViewHelper.setTranslationX(ll1, l*0.7f);
		float scale = l/(menu.getWidth()*1.0f);
		float leftScale = 1-scale*0.3f;
		menu.setScaleX(leftScale);
		menu.setScaleY(leftScale);
		menu.setTranslationX(l);
		content.setScaleX(0.8f+0.2f*scale);
		content.setScaleY(0.8f+0.2f*scale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}

}
