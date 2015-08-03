package com.tz.volume.view;

import com.tz.volume.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/**
 * 此类的描述：自定义音量控件 * 
 * @author: studio 
 */
public class CustomVolume extends View {
	private Context context;
	private Bitmap greenBitmap;
	private Bitmap grayBitmap;
	private AudioManager audioManager;
	private int maxVolume = 0;
	int currentVolume = 0;
	private int blockHeight;
	private int allHight;
	private Paint paint;

	public CustomVolume(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public CustomVolume(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	private void init() {
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
		currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
		paint = new Paint();
		greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		grayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		blockHeight = grayBitmap.getHeight();
		// 取到最大的高度
		allHight = maxVolume * (grayBitmap.getHeight() + blockHeight);
		invalidate();

	}

	@Override
	protected void onDraw(Canvas canvas) {	
		Bitmap drawBitmap = grayBitmap;
		int leftPadding =  grayBitmap.getWidth();
		int topPadding = grayBitmap.getHeight();
		for(int i = 0; i < maxVolume; i++){
			if(i >= (maxVolume - currentVolume)){
				drawBitmap = greenBitmap;
			} 
			canvas.drawBitmap(drawBitmap, leftPadding, topPadding + i* 2 * greenBitmap.getHeight(), paint);
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("xxxxx onKeyDown--> ", "dfsdf");
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			if (currentVolume <= maxVolume) {
				currentVolume++;
				invalidate();
			}
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			if (currentVolume >=0) {
				currentVolume--;
				invalidate();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
