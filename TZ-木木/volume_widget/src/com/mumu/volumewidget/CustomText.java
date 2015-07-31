package com.mumu.volumewidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomText extends View {

	private Paint paint;
	private Context context;
	private String text;

	public CustomText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		paint = new Paint();
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomText);
		float size = a.getDimension(R.styleable.CustomText_TextSize, 30);
		int color = a.getColor(R.styleable.CustomText_TextColor, 0xffffff);
		text = a.getString(R.styleable.CustomText_Text);
		paint.setTextSize(size);
		paint.setColor(color);
		
		a.recycle();	
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Rect r = new Rect(0, 0, 330, 300);
		canvas.save();
		canvas.drawText(text, 100, 100, paint);
		//canvas.drawRect(r, paint);
		canvas.restore();
	}
	
	
	
}
