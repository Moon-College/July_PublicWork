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
 * @function �Զ���VolumeView ��ʾ ����������
 */
public class VolumeView extends View {
	// ����������
	private AudioManager audioManager;
	// ����
	private Paint paint;
	// ���
	private int BLOCK;
	// �������ֵ
	private int max;
	// ������ǰֵ
	private int current;
	// ��������
	private Bitmap greenBitmap;
	// ��������
	private Bitmap grayBitmap;
	// ��ɫģ������
	private int grayNumber;
	// ����߶�
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
		// �����ػ�
		invalidate();
	}

	/**
	 * ��ȡ�������ֵ
	 */
	public int getMax() {
		return max;
	}

	/**
	 * ��ȡ��ɫ��ť������
	 */
	public int getGrayNumber() {
		return grayNumber;
	}

	/**
	 * ���û�ɫ��ť������
	 * 
	 * @param grayNumber
	 */
	public void setGrayNumber(int grayNumber) {
		this.grayNumber = grayNumber;
	}

	/**
	 * ������������ �ı�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// �����ɫ������
		float y = event.getY();
		float x = event.getX();
		if (y <= allHeight && x <= greenBitmap.getWidth()) {
			// ����
			grayNumber = (int) (y * max / allHeight);
			invalidate();
			// ��ϵͳ����
			audioManager.setStreamVolume(AudioManager.STREAM_RING, max
					- grayNumber, AudioManager.FLAG_PLAY_SOUND
					| AudioManager.FLAG_SHOW_UI);
		}
		return super.onTouchEvent(event);
	}

	/**
	 * ���������� �޸����� ���Ȼ���
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ȫ��������ɫ�ĸ���
		for (int i = 0; i < max; i++) {
			// Rect src: �Ƕ�ͼƬ���вýأ����ǿ�null����ʾ����ͼƬ
			Rect srcRect = new Rect(0, 0, greenBitmap.getWidth(),
					greenBitmap.getHeight());
			// Rect dstRect: ��ͼƬ��Canvas��������ʾ������
			Rect dstRect = new Rect(0, i * (greenBitmap.getHeight() + BLOCK),
					greenBitmap.getWidth(), i
							* (greenBitmap.getHeight() + BLOCK)
							+ greenBitmap.getHeight());
			canvas.drawBitmap(greenBitmap, srcRect, dstRect, paint);
		}
		// ȫ�����ƻ�ɫ�ĸ���
		for (int i = 0; i < grayNumber; i++) {
			// Rect src: �Ƕ�ͼƬ���вýأ����ǿ�null����ʾ����ͼƬ
			Rect srcRect = new Rect(0, 0, grayBitmap.getWidth(),
					grayBitmap.getHeight());
			// Rect dstRect: ��ͼƬ��Canvas��������ʾ������
			Rect dstRect = new Rect(0, i * (grayBitmap.getHeight() + BLOCK),
					grayBitmap.getWidth(), i * (grayBitmap.getHeight() + BLOCK)
							+ grayBitmap.getHeight());
			canvas.drawBitmap(grayBitmap, srcRect, dstRect, paint);
		}

	}

}
