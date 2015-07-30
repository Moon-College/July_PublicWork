package com.house.volumecontrol;

import com.house.volumecontrol.view.VolumeControlView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	
	private VolumeControlView volumeView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		volumeView = (VolumeControlView) findViewById(R.id.volumeView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			volumeView.changeVolume(false);
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			volumeView.changeVolume(true);
		}
		return super.onKeyDown(keyCode, event);
	}
}
