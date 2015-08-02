package com.mumu.volumewidget;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends Activity {

	private CustomVolume cv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cv = (CustomVolume) findViewById(R.id.custom_volume);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode ==KeyEvent.KEYCODE_VOLUME_UP){
			return cv.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

}
