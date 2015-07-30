package com.tz.lesson8.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.tz.lesson8.R;
import com.tz.lesson8.util.LogUtil;

@SuppressLint("NewApi")
public class VolumeView extends View {

	private Bitmap grayBitmap;
	private Bitmap greenBitmap;
	
	private AudioManager mAudioManager;
	
	private int mMaxVolume;
	private int mCurrentVolume;
	private int mGrayCount;
	
	private int bitmapHeight;
	private int maxHeight;
	
	
	public VolumeView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public VolumeView(Context context) {
		super(context);
		init(context);
	}
	
	/**
	 * 初始化
	 * @param context
	 */
	private void init(Context context) {
		// 获取音量图片
		grayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		// 获取系统音量管理 对象 
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		// 获取系统媒体最大音量
		mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		
		// 获取图片的宽、高度
		bitmapHeight = grayBitmap.getHeight();
		//　计算最大高度
		maxHeight = (2 * mMaxVolume - 1) * bitmapHeight;
		
	}

	public AudioManager getAudioManager() {
		return mAudioManager;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		
		int height = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		int widthMeasure = grayBitmap.getWidth() + getPaddingLeft() + getPaddingRight();
		int heightMeasure = (2 * mMaxVolume - 1 ) * grayBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
		
		if(widthMode == MeasureSpec.EXACTLY) {
			widthMeasure = width;
		}
		if(heightMode == MeasureSpec.EXACTLY) {
			heightMeasure = height;
		}
		
		setMeasuredDimension(widthMeasure, heightMeasure);
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// 获取系统当前媒体音量
		mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		// 计算灰色格子数
		mGrayCount = mMaxVolume - mCurrentVolume;
		
		for (int i = 0; i < mGrayCount; i++) {
			int top = 2 * i * bitmapHeight + getPaddingTop();
			canvas.drawBitmap(grayBitmap, getPaddingLeft(), top, null);
		}
		
		// 画出当前系统音量等级
		for (int i = mGrayCount; i < mMaxVolume; i++) {
			int top = 2 * i * bitmapHeight + getPaddingTop();
			canvas.drawBitmap(greenBitmap, getPaddingLeft(), top, null);
		}
		
		super.onDraw(canvas);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		LogUtil.d("VolumeView", "监听到触摸事件");
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();
			// 判断是否点击在音量控件内
			if((x > getPaddingLeft() && x <= getWidth() - getPaddingRight()) && (y > getPaddingTop() && y < getHeight() - getPaddingBottom())) {
				// 计算点到音量索引
				mGrayCount = (int) ((y-getPaddingTop()) * mMaxVolume / maxHeight);
				LogUtil.d("VolumeView", "当前音量为：" + (mMaxVolume - mGrayCount));
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (mMaxVolume - mGrayCount), AudioManager.FLAG_PLAY_SOUND);
				invalidate();
			}
		}
		
		return super.onTouchEvent(event);
	}
	
}
