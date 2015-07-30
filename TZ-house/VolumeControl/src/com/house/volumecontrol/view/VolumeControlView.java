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
 * �Զ���ؼ�-->��������
 */
public class VolumeControlView extends View {
	/** ���� **/
	private Paint paint;
	/** ϵͳ������� **/
	private int maxVolume;
	/** ��ǰϵͳ���� **/
	private int currentVolume;
	/** ��ɫ����������� **/
	private int grayNum;
	/** ��ɫ������ͼƬ **/
	private Bitmap greenBm;
	/** ��ɫ������ͼƬ **/
	private Bitmap grayBm;

	private Context context;
	private AudioManager manager;
	/** ������� **/
	private int BLOCK;
	/** ͼƬ�Ŀ�� **/
	private int bitmapWidth;
	/** ͼƬ�ĸ߶� **/
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
	 * ��ʼ��
	 */
	private void init() {
		paint = new Paint();
		greenBm = BitmapFactory
				.decodeResource(getResources(), R.drawable.green);
		grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);

		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		// ��ȡϵͳ��ǰ����
		currentVolume = manager.getStreamVolume(AudioManager.STREAM_RING);
		// ��ȡϵͳ�������
		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);

		bitmapWidth = greenBm.getWidth();
		bitmapHeight = greenBm.getHeight();
		BLOCK = bitmapHeight;

		grayNum = maxVolume - currentVolume;
		allHeight = maxVolume * (greenBm.getHeight() + BLOCK);
		// ���⽹���ͻ
		setFocusableInTouchMode(true);
		// �����ػ�
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ȫ��������ɫ����
		for (int i = 0; i < maxVolume; i++) {
			Rect src = new Rect(0, 0, bitmapWidth, bitmapHeight);
			Rect dst = new Rect(0, i * (bitmapHeight + BLOCK), bitmapWidth, i
					* (bitmapHeight + BLOCK) + bitmapHeight);
			canvas.drawBitmap(greenBm, src, dst, paint);
		}
		// ���ƻ�ɫ����
		for (int i = 0; i < grayNum; i++) {
			Rect src = new Rect(0, 0, bitmapWidth, bitmapHeight);
			Rect dst = new Rect(0, i * (bitmapHeight + BLOCK), bitmapWidth, i
					* (bitmapHeight + BLOCK) + bitmapHeight);
			canvas.drawBitmap(grayBm, src, dst, paint);
		}
	}

	/**
	 * �޸�������ˢ�¿ؼ� ����ʹ��Service�������������������������޸�����ʱҲ��ͬ��ˢ��
	 * 
	 * @param isAdd
	 *            true:������ false:������
	 */
	public void changeVolume(boolean isAdd) {
		if (isAdd) {
			// ��ǰ�����Ƿ������������� ��ǰ����+1
			if (currentVolume < maxVolume) {
				currentVolume += 1;
			}
		} else {
			// ��ǰ�����Ƿ�����С��������� ��ǰ����-1
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
			// ������ͼƬ���ܸ߶�/y = �ܸ�����/��ɫ������ --->��ɫ������ = �ܸ�����*y/(ͼƬ���ܸ߶�)
			grayNum = (int) (y * maxVolume / allHeight);
			// ����������ͼƬ�϶����ǵ�����˼���������޸�������ˢ�½���
			if (y < (grayNum + 1) * (bitmapHeight + BLOCK) - BLOCK) {
				invalidate();
				// ��ϵͳ����
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
