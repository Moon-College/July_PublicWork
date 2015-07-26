package com.example.homework05;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ImageView imageView;
	private ListView listView;
	private Button button;

	private List<String> list;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				list.add("Ð¡Áý°ü·Û");
				
				
				listView.setAdapter(adapter);
				
			}
		});
		
		
	}

	private void initData() {
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, R.layout.listitem,R.id.tv,list);
		
	}

	private void initView() {
		
		
		listView = (ListView)findViewById(R.id.listView);
		
		button = (Button)findViewById(R.id.button1);
		
		
	}

	

}
