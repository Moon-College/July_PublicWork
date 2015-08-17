package com.tz.lsn14_propertyanimation;

import javax.xml.validation.Validator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CircleCanvasView extends View {
	
	float radius;	//半径
	float x;		//圆心x坐标
	float y;		//圆心y坐标
	int color;		//画笔颜色
	
	public CircleCanvasView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public CircleCanvasView(Context context, float radius, float x, float y,
			int color) {
		super(context);
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.color = color;
		
	}


	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		System.out.println(">>>>>onDraw");
		
		 Paint paint = new Paint();
         //  设置画笔颜色
         paint.setColor(color);
         //  绘制实心圆
         canvas.drawCircle(x, y, radius, paint);
	}
}
