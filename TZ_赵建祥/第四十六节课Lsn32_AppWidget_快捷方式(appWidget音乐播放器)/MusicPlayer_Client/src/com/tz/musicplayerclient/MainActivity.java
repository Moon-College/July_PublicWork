package com.tz.musicplayerclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.tz.music.inter.IMusic;
import com.tz.music.inter.bean.Music;

public class MainActivity extends Activity {
	//播放模式
	public static int musicPlayModel=0;
	
	public static final int PLAYMODEL_ORDER=0;//顺序
	public static final int PLAYMODEL_RANDOM=1;//随机
	public static final int PLAYMODEL_LOOP=2;//循环
	public static final int PLAYMODEL_SINGLE=3;//单曲
	
	public static final int PLAYDIRECT_PREVIOUS=0;//上一首
	public static final int PLAYDIRECT_NEXT=1;//下一首

	private Intent service;
	private IMusic binder;

	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = IMusic.Stub.asInterface(service);
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
		try {
			Music music=binder.play();
			Toast.makeText(this, music.toString(), 1).show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop(View v) {
		try {
			binder.stop();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pause(View v) {
		try {
			binder.pause();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void previous(View v){
		try {
			binder.next(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void next(View v){
		try {
			binder.next(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void order(View v){
		try {
			binder.setPlayModel(PLAYMODEL_ORDER);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void random(View v){
		try {
			binder.setPlayModel(PLAYMODEL_RANDOM);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loop(View v){
		try {
			binder.setPlayModel(PLAYMODEL_LOOP);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void single(View v){
		try {
			binder.setPlayModel(PLAYMODEL_SINGLE);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}
}
