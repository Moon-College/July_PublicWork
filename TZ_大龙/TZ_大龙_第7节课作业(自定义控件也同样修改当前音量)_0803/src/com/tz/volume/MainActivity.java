package com.tz.volume;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {

	private VolumeView volumeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		volumeView = (VolumeView) findViewById(R.id.volumeView);

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
//		Toast.makeText(this, "keyCode:" + keyCode + " " + event.getAction(), Toast.LENGTH_LONG).show();
		
		/**
		 * 设置同步自定义控件方法1
		 */
		//获取肖前灰色格子数
//		int curVolume = volumeView.getGrayNumer();
//		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
//			//往下 音量减小 灰色格子数增大
//			volumeView.setGrayNumer(++curVolume);
//		} else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//			//往上  音量减大 灰色格子数减大
//			volumeView.setGrayNumer(--curVolume);
//		}

		return super.onKeyDown(keyCode, event);
	}
	

	
}
