package com.fantasyado.myvolumecontroler;

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

/**
 * created at:2015��7��30�� ����8:01:53 project name:MyVolumeControler
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:CustomVolumeControler.java description:
 */

public class CustomVolumeControler extends View {
	private Bitmap greenBmp;
	private Bitmap grayBmp;
	private AudioManager manager;
	private int maxVolume;
	private int currentVolume;
	private int grayCount;
	private float totalHeight;
	private Paint paint;
	 

	public CustomVolumeControler(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		//�Զ���ؼ���Ĭ�ϳߴ��߶�����丸�ؼ����ڴ�������Ϊ���������ݡ�
		//��Ϊ֪��ϵͳ�����ܹ����߸�������˿����ø߶�Ϊһ�����Ӽӿ�϶��7.5��
		setMeasuredDimension(15 + greenBmp.getWidth(),
				(int) ((15 + greenBmp.getHeight()) * 7.5));

	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		
		//�������ô����ԣ����Զ���ؼ�������ȷ�������������¼�����һ�ֽ����������Activity
		//�����ü������ÿؼ�invalidate����
		this.setFocusableInTouchMode(true);
	}

	public CustomVolumeControler(Context context, AttributeSet attrs) {
		super(context, attrs);
		grayBmp = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		greenBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);
		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		paint = new Paint();
		invalidate();

	}

  /**
   * �����������������Ӽ��������Զ�����������ڿؼ�
   */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN||keyCode==KeyEvent.KEYCODE_VOLUME_UP)
		invalidate();
		return super.onKeyDown(keyCode, event);
	}
    
	
	/**
	 * ���������Χ���ڿؼ���Χ�ڣ���ִ����Ӧ�������Ӽ��������ػ�ؼ�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float y = event.getY();
		float x = event.getX();
		if (x < 15 + greenBmp.getWidth()) {
			if (y < totalHeight + greenBmp.getHeight() + 15) {
				grayCount = (int) (y * maxVolume / totalHeight);
				setVolume(grayCount);
				invalidate();

			}
		}

		return super.onTouchEvent(event);

	}

	private void setVolume(int volume) {
		// TODO Auto-generated method stub
		manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume - volume,
				AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);

	}

	protected void onDraw(Canvas canvas) {

		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
		currentVolume = manager.getStreamVolume(AudioManager.STREAM_RING);
		grayCount = maxVolume - currentVolume;
		totalHeight = maxVolume * (grayBmp.getHeight() + 15);

		for (int i = 0; i < grayCount; i++) {
			Rect src = new Rect(0, 0, grayBmp.getWidth(), grayBmp.getHeight());
			Rect dest = new Rect(15, i * (grayBmp.getHeight() + 15) + 15,
					15 + grayBmp.getWidth(), i * (grayBmp.getHeight() + 15)
							+ 15 + grayBmp.getHeight());
			canvas.drawBitmap(grayBmp, src, dest, paint);
		}

		for (int i = grayCount; i < maxVolume; i++) {
			Rect src = new Rect(0, 0, greenBmp.getWidth(), greenBmp.getHeight());
			Rect dest = new Rect(15, i * (greenBmp.getHeight() + 15) + 15,
					15 + greenBmp.getWidth(), i * (grayBmp.getHeight() + 15)
							+ 15 + grayBmp.getHeight());
			canvas.drawBitmap(greenBmp, src, dest, paint);

		}

	}

}
