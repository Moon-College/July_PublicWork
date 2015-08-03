package com.example.tz_729_homework_view;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends Activity {
    private VolumeView volumeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volumeView = (VolumeView) this.findViewById(R.id.volumeview);
    }
  

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
          
           volumeView.setGrayNum(volumeView.grayNum+1);         
			break;
		case KeyEvent.KEYCODE_VOLUME_UP:
			volumeView.setGrayNum(volumeView.grayNum-1);   
			break;

		}

		return super.onKeyDown(keyCode, event);

	}
    
}
