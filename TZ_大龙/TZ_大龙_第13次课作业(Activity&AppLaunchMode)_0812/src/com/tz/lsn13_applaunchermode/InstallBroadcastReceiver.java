package com.tz.lsn13_applaunchermode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class InstallBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>>>>>>>>>InstallBroadcastReceiver-->" + intent.getAction());
		if(LaucherActivity.ACTION_UNINSTALL.equals(intent.getAction())) {
			LaucherActivity.getInstance().notifyDataChanged();
		}
	}

}
