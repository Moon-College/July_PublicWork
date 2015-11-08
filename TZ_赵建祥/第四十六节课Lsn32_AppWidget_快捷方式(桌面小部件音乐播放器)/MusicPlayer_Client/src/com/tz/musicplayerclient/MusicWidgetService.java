package com.tz.musicplayerclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.tz.music.inter.IMusic;
import com.tz.music.inter.bean.Music;

/**
 * ÿ��һ��ʱ�䣬������һ�׸裬ʵ�ֵ�����ź���ͣ����һ��
 * ͨ������aidl���÷���
 * @author Administrator
 *
 */
public class MusicWidgetService extends Service {
	public static final String TAG=MusicWidgetService.class.getSimpleName();
	
	//����һ������
	Intent service;
	IMusic binder;
	ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = IMusic.Stub.asInterface(service);
		}
	};
	
	void init(){
		if(binder==null){
			service = new Intent("com.tz.musicplayer.MusicService");
			startService(service);
			bindService(service, conn, BIND_AUTO_CREATE);
		}
	}
	
	

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}



	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
