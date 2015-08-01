package com.tz;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class CustomerTextView extends View{

	Paint paint;
	
	public CustomerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(35);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		canvas.drawText("�����Զ���ؼ�", 100,50, paint);
		super.onDraw(canvas);
	}
	

}
