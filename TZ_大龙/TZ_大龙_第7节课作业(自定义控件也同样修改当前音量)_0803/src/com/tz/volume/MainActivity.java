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
		 * ����ͬ���Զ���ؼ�����1
		 */
		//��ȡФǰ��ɫ������
//		int curVolume = volumeView.getGrayNumer();
//		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
//			//���� ������С ��ɫ����������
//			volumeView.setGrayNumer(++curVolume);
//		} else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//			//����  �������� ��ɫ����������
//			volumeView.setGrayNumer(--curVolume);
//		}

		return super.onKeyDown(keyCode, event);
	}
	

	
}
