package com.tz.lockscreen.service;

import com.tz.lockscreen.MainActivity;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class LockScreenService extends Service {

	private MyScreenReciver myReciver;




	/**
	 * ����һ���������������㲥
	 */
	@Override
	public void onCreate() {
		myReciver = new MyScreenReciver();
		IntentFilter filter=new IntentFilter(Intent.ACTION_SCREEN_OFF);
		registerReceiver(myReciver, filter);
	};
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myReciver);
	}
	
	
	
	
	class MyScreenReciver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			//�ɵ�ϵͳĬ������
			KeyguardManager keryManager=(KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
			KeyguardLock keyLock = keryManager.newKeyguardLock("");
			keyLock.disableKeyguard();
			
			//�����Լ�������
			Intent intentT=new Intent(context, MainActivity.class);
			
			intentT.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intentT);
		}
		
	}

}
