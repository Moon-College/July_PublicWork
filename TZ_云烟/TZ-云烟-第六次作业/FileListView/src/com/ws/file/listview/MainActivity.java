package com.ws.file.listview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listview;
	private String rootPath;
	private Adapter adapter;
	private List<MyFile> data = new ArrayList<MyFile>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview = (ListView) findViewById(R.id.listview);
		// 判断SD是否存在
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			
		} else {
			Toast.makeText(this, "SD卡不存在", Toast.LENGTH_SHORT).show();
		}
		initData(rootPath);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyFile myFile = (MyFile) adapter.getItem(position);
				File file = new File(myFile.getPath());
				if(file.isDirectory()){
					initData(file.getAbsolutePath());
				}
			}
			
		});
	}

	private void initData(String path) {
		// 清空列表数据
		data.clear();
		File file = new File(path);
		File[] listFile = file.listFiles();
		MyFile file_back = new MyFile("返回", BitmapFactory.decodeResource(
				getResources(), R.drawable.dirs), file.getParentFile()
				.getAbsolutePath());
		data.add(file_back);
		for (File f : listFile) {
			MyFile myFile = new MyFile();
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			// 判断是否是文件夹
			if (f.isDirectory()) {
				myFile.setBitmap(BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs));
			} else {
				if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")
						|| f.getName().toLowerCase().endsWith(".jpeg")) {
					myFile.setBitmap(null);
				} else {
					myFile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
			}
			data.add(myFile);
		}
		adapter = new Adapter(this, data);
		listview.setAdapter(adapter);
	}

}
