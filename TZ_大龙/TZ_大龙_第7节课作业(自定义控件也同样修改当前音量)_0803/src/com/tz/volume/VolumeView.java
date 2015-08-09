package com.tz.volume;

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

public class VolumeView extends View{

	private AudioManager manager;
	private int volume;
	private int maxVolume;
	
	private Bitmap greenBm;
	private Bitmap grayBm;
	private Paint paint;
	
	private int block = 0;
	private int grayCount; 
	private int grayRecord;
	private Bitmap muteBm; 
	
	public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setFocusableInTouchMode(true);
		manager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		volume = manager.getStreamVolume(AudioManager.STREAM_RING);
		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
		
		//��ʼ��ͼƬ
		greenBm = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		
		muteBm = BitmapFactory.decodeResource(getResources(), R.drawable.mute);
		
		paint = new Paint();
		block = greenBm.getHeight();
		
		grayCount = maxVolume - volume;
		
		//�����ػ�
		invalidate();
	}

	public VolumeView(Context context) {
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//�����ɫ����
		float x = event.getX();
		float y = event.getY();
		
		// ��ɫ���������ܸ����� = ����ĸ߶ȣ��ܸ߶� =>   ��ɫ������ = (����ĸ߶ȣ��ܸ߶�) * �ܸ�����
		grayCount = (int)y*maxVolume/((greenBm.getHeight()+block)*maxVolume);
		
		if(grayCount <= maxVolume && x <= greenBm.getWidth()) {
			//�ж��Ƿ�����Ϊ1���ٴε����ر�����
			if(grayRecord == maxVolume-1 && grayCount == maxVolume-1) {
				grayCount = maxVolume;
			}
			
			invalidate();
		}
		
		manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume-grayCount, AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
		
		//��¼��ǰ���������ɫ���ӵĸ߶�
		grayRecord = grayCount;
		
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		//����2��
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
			//���� ������С ��ɫ����������
			setGrayNumer(++ grayCount);
		} else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			//����  �������� ��ɫ����������
			setGrayNumer(-- grayCount);
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	public void dispatchWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.dispatchWindowFocusChanged(hasFocus);
	}

	/**
	 * ��ȡ��ɫ������
	 * @return
	 */
	public int getGrayNumer() {
		
		return grayCount;
	}
	
	/**
	 * ���û�ɫ������
	 * @param number
	 */
	public void setGrayNumer(int number) {
		
		
		grayCount = number;
		if(grayCount > maxVolume) {
			grayCount = maxVolume;
			return;
		} else if(grayCount < 0){
			grayCount = 0;
			return;
		}
		
		invalidate();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < maxVolume; i++) {
			//��ʡ�ռ� ,IO����
			
			Rect src = new Rect(0, 0, greenBm.getWidth(), greenBm.getHeight());
			Rect dst = new Rect(0, i * (greenBm.getHeight() + block),
					greenBm.getWidth(), i * (greenBm.getHeight() + block)
							+ greenBm.getHeight());
			/**
			 * Rect src:��greenBm�����һ������ͼƬ
			 * Rect dst:�ŵ����������ĳһ�������������� 
			 */
			//��ʡ�ռ� ,IO����
			canvas.drawBitmap(greenBm, src, dst, paint);
		}
		
		for (int i = 0; i < grayCount; i++) {
			//��ʡ�ռ� ,IO����
			
			Rect src = new Rect(0, 0, grayBm.getWidth(), grayBm.getHeight());
			Rect dst = new Rect(0, i * (grayBm.getHeight() + block),
					grayBm.getWidth(), i * (grayBm.getHeight() + block)
							+ grayBm.getHeight());
			/**
			 * Rect src:��greenBm�����һ������ͼƬ
			 * Rect dst:�ŵ����������ĳһ�������������� 
			 */
			//��ʡ�ռ� ,IO����
			canvas.drawBitmap(grayBm, src, dst, paint);
		}
		
		//�жϸ����Ƿ����񣬼��Ƿ�Ϊ����
		if(grayCount == maxVolume) {
			//���ƾ�����
			canvas.drawBitmap(muteBm, greenBm.getWidth() + block*2, (maxVolume - 3) * (greenBm.getHeight() + block), paint);
		}
		
		super.onDraw(canvas);
	}
	
	public void setVolume() {
		
	}

}
