package com.house.baseactivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.house.baseactivity.common.GridActivity;

public class SecondActivity extends GridActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "返字1",
						"返字1", "返字1", "返字1", "返字1", "返字1", "返字3" });
		
		setGridAdapter(adapter);
		setColumnsWidth(50);
		setNumColumns(3);
	}
}
