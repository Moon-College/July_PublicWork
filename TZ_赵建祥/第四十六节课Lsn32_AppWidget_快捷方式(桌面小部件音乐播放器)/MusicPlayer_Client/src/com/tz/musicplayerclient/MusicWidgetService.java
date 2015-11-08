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
 * 每隔一段时间，播放下一首歌，实现点击播放和暂停、下一首
 * 通过调用aidl调用服务
 * @author Administrator
 *
 */
public class MusicWidgetService extends Service {
	public static final String TAG=MusicWidgetService.class.getSimpleName();
	
	//启动一个服务
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
