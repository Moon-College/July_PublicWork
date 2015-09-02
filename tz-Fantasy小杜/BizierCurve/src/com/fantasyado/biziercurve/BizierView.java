package com.fantasyado.biziercurve;

 
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**  
 * created at:2015年8月22日 上午10:37:20  
 * project name:BizierCurve  
 * @author Fantasy ado  
 * @version 1.0  
 * @since JDK 1.7  
 * File name:BizierView.java  
 * description:  
 */

public class BizierView extends LinearLayout {
	
	private int[] imgs = {
			R.drawable.red,R.drawable.green,R.drawable.blue
	};
	private int width;
	private int height;

	public BizierView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
 
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
 		  width = getContext().getResources().getDisplayMetrics().widthPixels;
 		  height = getContext().getResources().getDisplayMetrics().heightPixels;
         
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
	}

}
