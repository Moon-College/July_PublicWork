package com.tz.volume.view;

import com.tz.volume.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

/**
 * 
 * 此类的描述：自定义控件 属性 * 
 * @author: studio
 * @最后修改人：studio
 */
public class XXView extends View {

	private Paint paint;

	public XXView(Context context) {
		super(context);
	}

	public XXView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
		int color = typedArray.getColor(R.styleable.MyView_textColor, Color.RED);
		float textsize = typedArray.getDimension(R.styleable.MyView_textSize, 10);
		paint.setTextSize(textsize);
		paint.setColor(color);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.RED);
		paint.setTextSize(40);//单位默认都是px
		canvas.drawText("哈哈哈哈哈哈", 200, 50, paint);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

}
