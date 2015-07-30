package com.house.volumecontrol.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.house.volumecontrol.R;

/**
 * 自定义控件-->音量控制
 */
public class VolumeControlView extends View {
	/** 画笔 **/
	private Paint paint;
	/** 系统最大音量 **/
	private int maxVolume;
	/** 当前系统音量 **/
	private int currentVolume;
	/** 灰色音量块的数量 **/
	private int grayNum;
	/** 绿色音量块图片 **/
	private Bitmap greenBm;
	/** 灰色音量块图片 **/
	private Bitmap grayBm;

	private Context context;
	private AudioManager manager;
	/** 间隔距离 **/
	private int BLOCK;
	/** 图片的宽度 **/
	private int bitmapWidth;
	/** 图片的高度 **/
	private int bitmapHeight;
	private int allHeight;

	public VolumeControlView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public VolumeControlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		paint = new Paint();
		greenBm = BitmapFactory
				.decodeResource(getResources(), R.drawable.green);
		grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);

		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		// 获取系统当前音量
		currentVolume = manager.getStreamVolume(AudioManager.STREAM_RING);
		// 获取系统最大音量
		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);

		bitmapWidth = greenBm.getWidth();
		bitmapHeight = greenBm.getHeight();
		BLOCK = bitmapHeight;

		grayNum = maxVolume - currentVolume;
		allHeight = maxVolume * (greenBm.getHeight() + BLOCK);
		// 避免焦点冲突
		setFocusableInTouchMode(true);
		// 提醒重绘
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 全部绘制绿色格子
		for (int i = 0; i < maxVolume; i++) {
			Rect src = new Rect(0, 0, bitmapWidth, bitmapHeight);
			Rect dst = new Rect(0, i * (bitmapHeight + BLOCK), bitmapWidth, i
					* (bitmapHeight + BLOCK) + bitmapHeight);
			canvas.drawBitmap(greenBm, src, dst, paint);
		}
		// 绘制灰色格子
		for (int i = 0; i < grayNum; i++) {
			Rect src = new Rect(0, 0, bitmapWidth, bitmapHeight);
			Rect dst = new Rect(0, i * (bitmapHeight + BLOCK), bitmapWidth, i
					* (bitmapHeight + BLOCK) + bitmapHeight);
			canvas.drawBitmap(grayBm, src, dst, paint);
		}
	}

	/**
	 * 修改音量，刷新控件 后期使用Service监听，这样如果不点击音量键修改音量时也能同步刷新
	 * 
	 * @param isAdd
	 *            true:音量加 false:音量减
	 */
	public void changeVolume(boolean isAdd) {
		if (isAdd) {
			// 当前音量是否是最大，如果不是 当前音量+1
			if (currentVolume < maxVolume) {
				currentVolume += 1;
			}
		} else {
			// 当前音量是否是最小，如果不是 当前音量-1
			if (currentVolume > 0) {
				currentVolume -= 1;
			}
		}
		grayNum = maxVolume - currentVolume;
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (x < bitmapWidth && y < allHeight) {
			// 比例：图片的总高度/y = 总格子数/灰色格子数 --->灰色格子数 = 总格子数*y/(图片的总高度)
			grayNum = (int) (y * maxVolume / allHeight);
			// 如果点击到了图片上而不是点击到了间隔处，则修改音量，刷新界面
			if (y < (grayNum + 1) * (bitmapHeight + BLOCK) - BLOCK) {
				invalidate();
				// 调系统音量
				manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume
						- grayNum, AudioManager.FLAG_PLAY_SOUND
						| AudioManager.FLAG_SHOW_UI);
			}

		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			changeVolume(false);
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			changeVolume(true);
		}
		return super.onKeyDown(keyCode, event);
	}
}
