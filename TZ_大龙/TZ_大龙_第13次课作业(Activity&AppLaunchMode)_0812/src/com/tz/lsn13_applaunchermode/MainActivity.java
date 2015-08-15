package com.tz.lsn13_applaunchermode;

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
		// TODO Auto-generated method stub
    	switch (view.getId()) {
			case R.id.bt_launcher:
				startActivity(new Intent(this,LaucherActivity.class));
				break;
	
			default:
				break;
		}
	}
}
