package com.ws.baseactivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentLayout(R.layout.main);
		setTitle("QQ");
		hideRigthBtn();
	}

}
