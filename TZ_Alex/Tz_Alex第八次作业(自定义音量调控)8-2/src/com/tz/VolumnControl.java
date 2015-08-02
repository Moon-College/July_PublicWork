package com.tz;

import com.example.tzclass9_customvolumn.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class VolumnControl extends View {
	// 画笔
	private Paint paint;
	// 声音管理器
	private AudioManager manager;
	// 得到当前和最大声音
	private int greenCount;
	private int maxVolumn;
	// 获取声音的格子
	private Bitmap greenBm;
	private Bitmap grayBm;
	// 空白间距
	private int SPACEING;
	// 得到显示的灰色音量大小
	private int grayCount;
	// 得到控件全高
	private int allHeight;

	private Context context;

	public VolumnControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		paint = new Paint();
		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		greenCount = manager.getStreamVolume(AudioManager.STREAM_RING);
		maxVolumn = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
		greenBm = BitmapFactory
				.decodeResource(getResources(), R.drawable.green);
		grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		SPACEING = greenBm.getHeight();
		grayCount = maxVolumn - greenCount;
		allHeight = maxVolumn * (greenBm.getHeight() + SPACEING);
		// 当音量改变时重新绘制
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制绿色格子
		for (int i = 0; i < maxVolumn; i++) {
			Rect src = new Rect(0, 0, greenBm.getWidth(), greenBm.getHeight());
			Rect dst = new Rect(0, i * (greenBm.getHeight() + SPACEING),
					greenBm.getWidth(), i * (greenBm.getHeight() + SPACEING)
							+ SPACEING);
			canvas.drawBitmap(greenBm, src, dst, paint);
		}
		// 绘制灰色格子
		for (int i = 0; i < grayCount; i++) {
			Rect src = new Rect(0, 0, grayBm.getWidth(), grayBm.getHeight());
			Rect dst = new Rect(0, i * (grayBm.getHeight() + SPACEING),
					grayBm.getWidth(), i * (grayBm.getHeight() + SPACEING)
							+ SPACEING);
			canvas.drawBitmap(grayBm, src, dst, paint);
		}

		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 计算灰色格子个数
		float x = event.getX();
		float y = event.getY();
		if (y <= allHeight && x <= greenBm.getWidth()) {
			grayCount = (int) (maxVolumn * y / allHeight);
			invalidate();
			manager.setStreamVolume(AudioManager.STREAM_RING, maxVolumn
					- grayCount, AudioManager.FLAG_PLAY_SOUND
					| AudioManager.FLAG_SHOW_UI);
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		int action = event.getAction();

		if (action == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0
				&& event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
			if (greenCount > 0) {
				greenCount--;
				grayCount++;
			}
			invalidate();
		}

		if (action == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0
				&& event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
			if (grayCount > 0) {
				grayCount--;
				greenCount++;
			}
			invalidate();
		}

		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// 若不设置此属性，则自定义控件不能正确监听物理按键的事件
		this.setFocusableInTouchMode(true);
	}
}
