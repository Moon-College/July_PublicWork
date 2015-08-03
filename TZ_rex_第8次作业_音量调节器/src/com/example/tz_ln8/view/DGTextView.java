package com.example.tz_ln8.view;

import com.example.tz_ln8.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DGTextView extends View
{

    Paint paint = new Paint();
    private int text_size;
    private int money;

    public DGTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    public DGTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public DGTextView(Context context)
    {
        super(context);
    }

    private void initAttrs(Context context, AttributeSet attrs)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.DGTextView123);
        money = typedArray.getInt(R.styleable.DGTextView123_money, 10000000);
        text_size = typedArray.getInt(R.styleable.DGTextView123_text_size, 10);
        paint.setTextSize(text_size);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        paint.setColor(Color.RED);

        canvas.drawText("我是自定义控件, money为：" + money, 100, 50, paint);
    }

}
