package com.mumu.volumewidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class CustomVolume extends View {
	
	Paint paint;
	Context context;
	Bitmap grayBitmap;
	Bitmap greenBitmap;
	private AudioManager am;
	private int maxVolume;
	private int curVolume;
	

	public CustomVolume(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
		invalidate();
	}
	
	private void init() {
		paint =  new Paint();
		grayBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray);
		greenBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
		curVolume = am.getStreamVolume(AudioManager.STREAM_RING);
	}
	
	/**
	 * 重写onMeasure方法，以便获取该控件的大小
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		int sizeheight = MeasureSpec.getSize(heightMeasureSpec);
		
		int measureWidth = grayBitmap.getWidth() + this.getPaddingLeft() + this.getPaddingRight();
		int measureHeight = (2 * maxVolume - 1) *grayBitmap.getHeight() 
				+ this.getPaddingTop() + this.getPaddingBottom();
		
		//指定宽高时使用指定的大小，其他使用测量出来的大小
		measureWidth = (modeWidth == MeasureSpec.EXACTLY ? sizeWidth : measureWidth);
		measureHeight = (modeHeight == MeasureSpec.EXACTLY ? sizeheight : measureHeight);
		
		if(modeHeight == MeasureSpec.EXACTLY){
			measureHeight = sizeheight;
		}
	
		super.onMeasure(MeasureSpec.makeMeasureSpec(measureWidth, modeWidth), 
				MeasureSpec.makeMeasureSpec(measureHeight, modeHeight));
	}

	/**
	 * 重写onDraw方法，重新画该控件
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		Bitmap drawBitmap = grayBitmap;
		int leftPadding =  this.getPaddingLeft();
		int topPadding = this.getPaddingTop();
		for(int i = 0; i < maxVolume; i++){
			if(i >= (maxVolume - curVolume)){
				drawBitmap = greenBitmap;
			} 
			canvas.drawBitmap(drawBitmap, leftPadding, topPadding + i* 2 * greenBitmap.getHeight(), paint);
		}
		
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i("INFO", "keyCode:" + keyCode + " ,curVolume:" + curVolume);
		if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			if (curVolume < maxVolume) {
				curVolume++;
				invalidate();
			}
			return true;
		} else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			if(curVolume > 0){
				curVolume--;	
				invalidate();
			}
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	

}
