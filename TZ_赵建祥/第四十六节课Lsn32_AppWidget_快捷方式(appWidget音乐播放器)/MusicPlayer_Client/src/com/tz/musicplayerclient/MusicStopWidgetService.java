package com.tz.musicplayerclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.tz.music.inter.IMusic;
import com.tz.music.inter.bean.Music;

/**
 * 每隔一段时间，播放下一首歌，实现点击播放和暂停、下一首
 * 通过调用aidl调用服务
 * @author Administrator
 *
 */
public class MusicStopWidgetService extends MusicWidgetService {
	public static final String TAG=MusicStopWidgetService.class.getSimpleName();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			init();
			if(binder!=null){
				binder.stop();
				MusicWidgetProvider.getInstance().update(getApplicationContext(),"stop");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.onStartCommand(intent, flags, startId);
	}



	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}



	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
