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
	 * ��ʼ��
	 * @param context
	 */
	private void init(Context context) {
		// ��ȡ����ͼƬ
		grayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		// ��ȡϵͳ�������� ���� 
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		// ��ȡϵͳý���������
		mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		
		// ��ȡͼƬ�Ŀ��߶�
		bitmapHeight = grayBitmap.getHeight();
		//���������߶�
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
		// ��ȡϵͳ��ǰý������
		mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		// �����ɫ������
		mGrayCount = mMaxVolume - mCurrentVolume;
		
		for (int i = 0; i < mGrayCount; i++) {
			int top = 2 * i * bitmapHeight + getPaddingTop();
			canvas.drawBitmap(grayBitmap, getPaddingLeft(), top, null);
		}
		
		// ������ǰϵͳ�����ȼ�
		for (int i = mGrayCount; i < mMaxVolume; i++) {
			int top = 2 * i * bitmapHeight + getPaddingTop();
			canvas.drawBitmap(greenBitmap, getPaddingLeft(), top, null);
		}
		
		super.onDraw(canvas);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		LogUtil.d("VolumeView", "�����������¼�");
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();
			// �ж��Ƿ����������ؼ���
			if((x > getPaddingLeft() && x <= getWidth() - getPaddingRight()) && (y > getPaddingTop() && y < getHeight() - getPaddingBottom())) {
				// ����㵽��������
				mGrayCount = (int) ((y-getPaddingTop()) * mMaxVolume / maxHeight);
				LogUtil.d("VolumeView", "��ǰ����Ϊ��" + (mMaxVolume - mGrayCount));
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (mMaxVolume - mGrayCount), AudioManager.FLAG_PLAY_SOUND);
				invalidate();
			}
		}
		
		return super.onTouchEvent(event);
	}
	
}
