package com.tz.tablelayout;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AutoByRelativeLayout extends RelativeLayout {

	public AutoByRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	 @Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		 LayoutParams lp = super.generateLayoutParams(attrs);
    	 lp.leftMargin =200;
		 return lp;
		   
	}
	 
	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		
		super.addView(AddParetent(child,params), index, params);
	}

	private View AddParetent(View child, android.view.ViewGroup.LayoutParams params) {
		LinearLayout ll = new  LinearLayout(getContext());
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setBackgroundColor(Color.YELLOW);
		ll.setPadding(10,10, 10, 10);
		//LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//;
		//ll.setLayoutParams(lp);
		ll.addView(child,params);
		return ll;
		
	}
}
