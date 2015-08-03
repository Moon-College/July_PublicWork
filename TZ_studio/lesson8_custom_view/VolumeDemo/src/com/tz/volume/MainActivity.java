package com.tz.volume;

import com.tz.volume.view.CustomVolume;
import com.tz.volume.view.XXView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	CustomVolume view=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 view=new CustomVolume(this);		
		setContentView(view);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("activity onKeyDown-->", "activity onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode ==KeyEvent.KEYCODE_VOLUME_UP){
			return view.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

}
