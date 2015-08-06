package com.studio;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;

//演示逐帧动画
public class MainActivity extends Activity {
	private AnimationDrawable animationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView iv = (ImageView) findViewById(R.id.iv_image);
		iv.setBackgroundResource(R.drawable.frame_animation);
		animationDrawable = (AnimationDrawable) iv.getBackground();

	}

	public void clickStart(View view) {
		animationDrawable.start();
	}

	public void clickStop(View view) {
		animationDrawable.stop();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (!animationDrawable.isRunning()) {
			animationDrawable.start();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction()==MotionEvent.ACTION_DOWN) {
			if (!animationDrawable.isRunning()) {
				animationDrawable.start();
			}
			return true;
		}
		return super.onTouchEvent(event);
	}
	
}
