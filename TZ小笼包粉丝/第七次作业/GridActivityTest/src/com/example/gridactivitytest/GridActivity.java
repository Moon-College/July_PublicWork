package com.example.gridactivitytest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridActivity extends Activity {

	private GridView gview;
	
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gview = (GridView)findViewById(R.id.gv);
		gview.setColumnWidth(2);
		setGridAdapter(adapter);
		
		
	}
	protected void setGridAdapter(ArrayAdapter madapter) {
		this.adapter = madapter;
		gview.setAdapter(adapter);
		
	}
	
	
	

}
