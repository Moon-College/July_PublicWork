package com.example.gridactivitytest;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class TestActivity extends GridActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> madapter = new ArrayAdapter<String>(this,
				R.layout.listitem,
				R.id.text,
				new String[]{"Àî°×","¶Å¸¦","ÍõÎ¬","²ÜÑ©ÇÛ"});
		
		setGridAdapter(madapter);
		
	}
}
