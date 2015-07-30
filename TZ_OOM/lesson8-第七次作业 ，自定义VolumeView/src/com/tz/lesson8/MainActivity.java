package com.tz.lesson8;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.tz.lesson8.widget.VolumeView;

public class MainActivity extends Activity {

	private VolumeView mVolumeView;
	private AudioManager mAudioManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mVolumeView = (VolumeView) findViewById(R.id.volume_view);
		mAudioManager = mVolumeView.getAudioManager();
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
											AudioManager.ADJUST_LOWER, 
											AudioManager.FLAG_PLAY_SOUND);
			mVolumeView.invalidate();
			return true;
		}
		else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
					  					  	 AudioManager.ADJUST_RAISE, 
					  					  	 AudioManager.FLAG_PLAY_SOUND);
			mVolumeView.invalidate();
			return true;
		}else {
			return super.onKeyDown(keyCode, event);
		}
	}
}
