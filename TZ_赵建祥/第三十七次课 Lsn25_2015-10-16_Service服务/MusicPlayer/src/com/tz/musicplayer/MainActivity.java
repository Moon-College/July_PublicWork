package com.tz.musicplayer;

import com.tz.musicplayer.service.MusicService;
import com.tz.musicplayer.service.MusicService.MusicBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;

public class MainActivity extends Activity {

	private Intent service;
	private MusicBinder binder;

	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (MusicBinder) service;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		service = new Intent("com.tz.musicplayer.MusicService");
		startService(service);
		bindService(service, conn, BIND_AUTO_CREATE);
	}

	public void play(View v) {
		binder.play();
	}

	public void stop(View v) {
		binder.stop();
	}

	public void pause(View v) {
		binder.pause();
	}
	
	public void previous(View v){
		binder.next(0);
	}
	
	public void next(View v){
		binder.next(1);
	}
	
	public void order(View v){
		binder.setPlayModel(MusicService.PLAYMODEL_ORDER);
	}
	
	public void random(View v){
		binder.setPlayModel(MusicService.PLAYMODEL_RANDOM);
	}
	
	public void loop(View v){
		binder.setPlayModel(MusicService.PLAYMODEL_LOOP);
	}
	
	public void single(View v){
		binder.setPlayModel(MusicService.PLAYMODEL_SINGLE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}
}
