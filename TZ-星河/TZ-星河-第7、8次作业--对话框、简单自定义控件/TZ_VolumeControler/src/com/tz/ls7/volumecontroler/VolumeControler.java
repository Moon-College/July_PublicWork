package com.tz.ls7.volumecontroler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class VolumeControler extends View {
	private int volume;
	private int maxVolume;
	private Bitmap greenBm;
	private Bitmap grayBm;
	private int grayCount;
	private Paint paint = null;
	private int bmHeight;
	private int bmWidth;
	private int block;
	private int allHeight;
	private AudioManager manager;
	
	public VolumeControler(Context context, AttributeSet attrs) {
		super(context, attrs);
		manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		paint = new Paint();
		volume = manager.getStreamVolume(AudioManager.STREAM_RING);
		maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
		greenBm = BitmapFactory
				.decodeResource(getResources(), R.drawable.green);
		grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		grayCount = maxVolume - volume;
		bmHeight = grayBm.getHeight();
		bmWidth = grayBm.getWidth();
		block = 2 * bmHeight;
		allHeight=maxVolume*block;
		//Ã·–—÷ÿª≠
		invalidate();
		context.registerReceiver(new VolumeChangeRecivre(), new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < maxVolume; i++) {
			Rect src = new Rect(0, 0, bmWidth, bmHeight);
			RectF dst = new RectF(0, i * (block), bmWidth, i * block + bmHeight);
			canvas.drawBitmap(greenBm, src, dst, paint);
		}
		
		for (int i = 0; i < grayCount; i++) {
			Rect src = new Rect(0, 0, bmWidth, bmHeight);
			RectF dst = new RectF(0, i * (block), bmWidth, i * block + bmHeight);
			canvas.drawBitmap(grayBm, src, dst, paint);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x=event.getX();
		float y=event.getY();
		if(x<=bmWidth&&y<=allHeight){
			grayCount=(int) Math.floor(y*maxVolume/allHeight);
			invalidate();
			int currVolume=maxVolume-grayCount;
			manager.setStreamVolume(AudioManager.STREAM_RING, currVolume, AudioManager.FLAG_PLAY_SOUND|AudioManager.FLAG_SHOW_UI);
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			Toast.makeText(getContext(), "down", 0).show();
			if(grayCount<maxVolume){
				grayCount++;
			}
			break;
		case KeyEvent.KEYCODE_VOLUME_UP:
			if(grayCount>0){
				grayCount--;
			}
			break;
		}
		invalidate();
		return true;
	}
	
	private class VolumeChangeRecivre extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
				volume=manager.getStreamVolume(AudioManager.STREAM_RING);
				grayCount=maxVolume-volume;
				invalidate();
			}
		}
		
	}

}
