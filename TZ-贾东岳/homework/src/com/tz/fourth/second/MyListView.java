package com.tz.fourth.second;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class MyListView extends ListView{

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected boolean addViewInLayout(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		child.setBackgroundColor(Color.BLACK);
		return super.addViewInLayout(child, index, params);
	}
}
