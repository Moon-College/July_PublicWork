package com.example.tz_729_homework_view;

import android.content.Context;
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

public class VolumeView extends View {
	int MaxVolume;// 系统音量级别 一般为7
	int NowValume;// 当前音量级别
	int grayNum;// 灰色格子数
	Bitmap gray;
	Bitmap green;
	int marginHeight = 5;// 格子间距
	Paint mpaint;
	int HeightAll;// 控件总体高度
	private AudioManager manager;

	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);

		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		// 得到系统服务的最大音量STREAM_RING 铃声 STREAM_SYSTEM系统音量
		MaxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
		NowValume = manager.getStreamVolume(AudioManager.STREAM_RING);
		gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		grayNum = MaxVolume - NowValume;
		mpaint = new Paint();
		HeightAll = (green.getHeight() + marginHeight) * MaxVolume
				- marginHeight;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < MaxVolume; i++) {
			canvas.drawBitmap(green, 0, (marginHeight + green.getHeight()) * i,
					mpaint);
		}

		for (int i = 0; i < grayNum; i++) {
			canvas.drawBitmap(gray, 0, (marginHeight + green.getHeight()) * i,
					mpaint);
		}

		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX();
			float y = event.getY();
			// 转化问题 手指按下的 地方 总体高度/手指按下的地方 = 总格子数/绿色格子数=总格子数/(总格子数-灰色格子数)
			// ====>灰色格子数 =
			if (x < green.getWidth() && y < HeightAll) {
				grayNum = MaxVolume
						- (int) (MaxVolume - (MaxVolume * y) / HeightAll);
				manager.setStreamVolume(AudioManager.STREAM_RING, MaxVolume
						- grayNum, AudioManager.FLAG_SHOW_UI
						| AudioManager.FLAG_VIBRATE);
				invalidate();
			}
			if (y < green.getHeight()) {
				grayNum = 0;
				manager.setStreamVolume(AudioManager.STREAM_RING, MaxVolume,
						AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_VIBRATE);
			}
			break;
		}

		return super.onTouchEvent(event);

	}

	public void setGrayNum(int num) {
		if (num <= 0) {
			grayNum = 0;
			invalidate();
		} else if (num >= MaxVolume) {
			grayNum = MaxVolume;
			invalidate();
		} else {
			grayNum = num;
			invalidate();
		}
	}

}
