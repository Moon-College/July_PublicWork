package com.tz.musicplayerclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.tz.music.inter.IMusic;

/**
 * ÿ��һ��ʱ�䣬������һ�׸裬ʵ�ֵ�����ź���ͣ����һ��
 * ͨ������aidl���÷���
 * @author Administrator
 *
 */
public class MusicPreviousWidgetService extends MusicWidgetService {
	public static final String TAG=MusicPreviousWidgetService.class.getSimpleName();
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			init();
			if(binder!=null){
				binder.next(0);
				MusicWidgetProvider.getInstance().update(getApplicationContext(),"previous");
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
