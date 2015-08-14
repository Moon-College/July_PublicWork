package com.example.lsn14_asyntask_handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void btnClick(View view) {
    	
    	Intent intent = new Intent();
    	
    	switch (view.getId()) {
			case R.id.bt_loadImg:
				intent.setClass(this, RemoteLoadImgActivity.class);
				intent.putExtra("id", R.id.bt_loadImg);
				break;
			case R.id.bt_loadApk:
				intent.setClass(this, RemoteLoadApkActivity.class);
				intent.putExtra("id", R.id.bt_loadApk);
				break;
			default:
				break;
		}
    	
    	startActivity(intent);
    	
    }


}
