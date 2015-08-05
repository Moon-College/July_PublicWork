package com.lisn_8_volumecontrolerview;

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

public class voluecontrolerview extends View {
	private Paint paint;
	private Bitmap green, gray;
	private int maxVolume;// 系统音量最大值
	private int volume;
	private int allHeight;
	private int graycount;
	private Context context;
	private final int Long = 5;
	private AudioManager manager;

	public voluecontrolerview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
		paint = new Paint();
		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);// 获得系统音频服务
		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);// 获取系统音量最大值
		volume = manager.getStreamVolume(AudioManager.STREAM_RING);// 当前音量
		green = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.green);
		gray = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.gray);
		allHeight = maxVolume * (green.getHeight() + Long) - Long;
		graycount = maxVolume - volume;
		System.out.println(graycount);
		// 提醒重绘
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float y = event.getY();// 点击高度
		if (y <= allHeight + green.getHeight()
				&& event.getX() <= green.getWidth()) {
			// 比例：图片的总高度/y = 总格子数/灰色格子数 --->灰色格子数 = 总格子数*y/(图片的总高度)
			graycount = (int) (y * maxVolume / allHeight);
			invalidate();
			manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume
					- graycount, AudioManager.FLAG_PLAY_SOUND
					| AudioManager.FLAG_SHOW_UI);
			// Toast.makeText (context, "当前音量值： ", Toast.LENGTH_SHORT).show ();
		}

		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN
				|| event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {

		}
		volume = manager.getStreamVolume(AudioManager.STREAM_RING);
		graycount = maxVolume - volume;
		// System.out.println(graycount);
		// 提醒重绘
		invalidate();
		return super.dispatchKeyEvent(event);
	}

	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	//
	// // TODO Auto-generated method stub
	//
	// switch (keyCode) {
	// // 音量减小
	// case KeyEvent.KEYCODE_VOLUME_DOWN:
	// volume= manager.getStreamVolume(AudioManager.STREAM_RING);
	// graycount=maxVolume-volume;
	// break;
	// // 音量增大
	// case KeyEvent.KEYCODE_VOLUME_UP:
	//
	// volume= manager.getStreamVolume(AudioManager.STREAM_RING);
	// Toast.makeText(context, volume+"", 1).show();
	// graycount=maxVolume-volume;
	// break;
	// }
	// //提醒重绘
	// invalidate();
	// return super.onKeyDown(keyCode, event);
	// }
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < maxVolume; i++) {
			// canvas.drawBitmap(green, 0, i*20, paint);
			/**
			 * Rect src,从green里面抠出一个矩形图片 RectF dst 放到画板某一个矩形区域里面
			 */
			Rect src = new Rect(0, 0, green.getWidth(), green.getHeight());
			/**
			 * (int left, int top, int right, int bottom)
			 */
			Rect dst = new Rect(0, i * (green.getHeight() + Long),
					green.getWidth(), i * (green.getHeight() + Long)
							+ green.getHeight());
			canvas.drawBitmap(green, src, dst, paint);
		}
		for (int i = 0; i < graycount; i++) {
			// canvas.drawBitmap(gray, 0, i*20, paint);
			Rect src = new Rect(0, 0, gray.getWidth(), gray.getHeight());
			Rect dst = new Rect(0, i * (gray.getHeight() + Long),
					gray.getWidth(), i * (gray.getHeight() + Long)
							+ gray.getHeight());
			canvas.drawBitmap(gray, src, dst, paint);
		}
		super.onDraw(canvas);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// 若不设置此属性，则自定义控件不能正确监听物理按键的事件
		this.setFocusableInTouchMode(true);
	}
}
