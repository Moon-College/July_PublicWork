package com.ws.baseactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Activity01 extends ListActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{
				"����һ",
				"���ڶ�",
				"������",
				"������",
				"������",
				"������",
				"������",
		});
		setListAdapter(adapter);
	}
}
