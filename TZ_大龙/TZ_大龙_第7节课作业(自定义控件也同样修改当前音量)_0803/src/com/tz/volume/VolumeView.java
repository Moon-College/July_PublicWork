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
		
		//初始化图片
		greenBm = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		
		muteBm = BitmapFactory.decodeResource(getResources(), R.drawable.mute);
		
		paint = new Paint();
		block = greenBm.getHeight();
		
		grayCount = maxVolume - volume;
		
		//提醒重绘
		invalidate();
	}

	public VolumeView(Context context) {
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//计算灰色格子
		float x = event.getX();
		float y = event.getY();
		
		// 灰色格子数：总格子数 = 点击的高度：总高度 =>   灰色格子数 = (点击的高度：总高度) * 总格子数
		grayCount = (int)y*maxVolume/((greenBm.getHeight()+block)*maxVolume);
		
		if(grayCount <= maxVolume && x <= greenBm.getWidth()) {
			//判断是否音量为1，再次点击则关闭音量
			if(grayRecord == maxVolume-1 && grayCount == maxVolume-1) {
				grayCount = maxVolume;
			}
			
			invalidate();
		}
		
		manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume-grayCount, AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
		
		//记录当前触摸点击灰色格子的高度
		grayRecord = grayCount;
		
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		//方法2：
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
			//往下 音量减小 灰色格子数增大
			setGrayNumer(++ grayCount);
		} else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			//往上  音量减大 灰色格子数减大
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
	 * 获取灰色格子数
	 * @return
	 */
	public int getGrayNumer() {
		
		return grayCount;
	}
	
	/**
	 * 设置灰色格子数
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
			//节省空间 ,IO次数
			
			Rect src = new Rect(0, 0, greenBm.getWidth(), greenBm.getHeight());
			Rect dst = new Rect(0, i * (greenBm.getHeight() + block),
					greenBm.getWidth(), i * (greenBm.getHeight() + block)
							+ greenBm.getHeight());
			/**
			 * Rect src:从greenBm里面抠一个矩形图片
			 * Rect dst:放到画板上面的某一个矩形区域里面 
			 */
			//节省空间 ,IO次数
			canvas.drawBitmap(greenBm, src, dst, paint);
		}
		
		for (int i = 0; i < grayCount; i++) {
			//节省空间 ,IO次数
			
			Rect src = new Rect(0, 0, grayBm.getWidth(), grayBm.getHeight());
			Rect dst = new Rect(0, i * (grayBm.getHeight() + block),
					grayBm.getWidth(), i * (grayBm.getHeight() + block)
							+ grayBm.getHeight());
			/**
			 * Rect src:从greenBm里面抠一个矩形图片
			 * Rect dst:放到画板上面的某一个矩形区域里面 
			 */
			//节省空间 ,IO次数
			canvas.drawBitmap(grayBm, src, dst, paint);
		}
		
		//判断格子是否满格，即是否为静音
		if(grayCount == maxVolume) {
			//绘制静音键
			canvas.drawBitmap(muteBm, greenBm.getWidth() + block*2, (maxVolume - 3) * (greenBm.getHeight() + block), paint);
		}
		
		super.onDraw(canvas);
	}
	
	public void setVolume() {
		
	}

}
