package com.example.implicitstartact;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Targer2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_targer2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.targer2, menu);
		return true;
	}

}
