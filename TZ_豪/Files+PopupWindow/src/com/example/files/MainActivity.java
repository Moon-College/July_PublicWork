package com.example.files;

import java.io.File;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
  
	private ListView lv_file;
	private FileAdapter adapter;
	private List<MyFile> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_file = (ListView) findViewById(R.id.lv_file);
		lv_file.setOnItemClickListener(this);
		if(FileUtils.hasSDCard()){
			
		}else{
			Toast.makeText(this, "sd卡不存在", 1).show();
		}
		adapter = new FileAdapter(this);
		adapter.list = FileUtils.getSDCardDirectory(FileUtils.getSDCardPath());
		lv_file.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		MyFile file=(MyFile) adapter.getItem(position);
		if(new File(file.getPath()).isDirectory()){
		adapter.list=FileUtils.getSDCardDirectory(adapter.list.get(position).getPath());
		adapter.notifyDataSetChanged();
		}
	}



}
