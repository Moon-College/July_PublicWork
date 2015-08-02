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
	int MaxVolume;// ϵͳ�������� һ��Ϊ7
	int NowValume;// ��ǰ��������
	int grayNum;// ��ɫ������
	Bitmap gray;
	Bitmap green;
	int marginHeight = 5;// ���Ӽ��
	Paint mpaint;
	int HeightAll;// �ؼ�����߶�
	private AudioManager manager;

	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);

		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		// �õ�ϵͳ������������STREAM_RING ���� STREAM_SYSTEMϵͳ����
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
			// ת������ ��ָ���µ� �ط� ����߶�/��ָ���µĵط� = �ܸ�����/��ɫ������=�ܸ�����/(�ܸ�����-��ɫ������)
			// ====>��ɫ������ =
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
