package com.kevin.view;

import com.kevin.myview.activity.R;

import android.R.integer;
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
import android.widget.Toast;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150731
 * @function 自定义VolumeView 显示 音量调节器
 */
public class VolumeView extends View {
	// 音量管理类
	private AudioManager audioManager;
	// 画笔
	private Paint paint;
	// 间隔
	private int BLOCK;
	// 音量最大值
	private int max;
	// 音量当前值
	private int current;
	// 表征音量
	private Bitmap greenBitmap;
	// 表征音量
	private Bitmap grayBitmap;
	// 灰色模块数量
	private int grayNumber;
	// 总体高度
	private int allHeight;

	public VolumeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		max = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
		current = audioManager.getStreamVolume(AudioManager.STREAM_RING);
		grayNumber = max - current;
		paint = new Paint();
		greenBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);
		grayBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.gray);

		BLOCK = greenBitmap.getHeight();
		allHeight = max * (greenBitmap.getHeight() + BLOCK);
		// 提醒重绘
		invalidate();
	}

	/**
	 * 获取音量最大值
	 */
	public int getMax() {
		return max;
	}

	/**
	 * 获取灰色按钮的数量
	 */
	public int getGrayNumber() {
		return grayNumber;
	}

	/**
	 * 设置灰色按钮的数量
	 * 
	 * @param grayNumber
	 */
	public void setGrayNumber(int grayNumber) {
		this.grayNumber = grayNumber;
	}

	/**
	 * 触摸音量排列 改变音量
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// 计算灰色格子数
		float y = event.getY();
		float x = event.getX();
		if (y <= allHeight && x <= greenBitmap.getWidth()) {
			// 比例
			grayNumber = (int) (y * max / allHeight);
			invalidate();
			// 调系统音量
			audioManager.setStreamVolume(AudioManager.STREAM_RING, max
					- grayNumber, AudioManager.FLAG_PLAY_SOUND
					| AudioManager.FLAG_SHOW_UI);
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 绘制音量的 修改音量 首先绘制
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 全部绘制绿色的格子
		for (int i = 0; i < max; i++) {
			// Rect src: 是对图片进行裁截，若是空null则显示整个图片
			Rect srcRect = new Rect(0, 0, greenBitmap.getWidth(),
					greenBitmap.getHeight());
			// Rect dstRect: 是图片在Canvas画布中显示的区域，
			Rect dstRect = new Rect(0, i * (greenBitmap.getHeight() + BLOCK),
					greenBitmap.getWidth(), i
							* (greenBitmap.getHeight() + BLOCK)
							+ greenBitmap.getHeight());
			canvas.drawBitmap(greenBitmap, srcRect, dstRect, paint);
		}
		// 全部绘制灰色的格子
		for (int i = 0; i < grayNumber; i++) {
			// Rect src: 是对图片进行裁截，若是空null则显示整个图片
			Rect srcRect = new Rect(0, 0, grayBitmap.getWidth(),
					grayBitmap.getHeight());
			// Rect dstRect: 是图片在Canvas画布中显示的区域，
			Rect dstRect = new Rect(0, i * (grayBitmap.getHeight() + BLOCK),
					grayBitmap.getWidth(), i * (grayBitmap.getHeight() + BLOCK)
							+ grayBitmap.getHeight());
			canvas.drawBitmap(grayBitmap, srcRect, dstRect, paint);
		}

	}

}
