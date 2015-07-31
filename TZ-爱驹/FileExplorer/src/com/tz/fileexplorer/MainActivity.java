package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String root;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			root = Environment.getExternalStorageDirectory().getAbsolutePath();
			initData(root);
			listView.setOnItemClickListener(this);
		} else {
			Toast.makeText(this, "sdcard is not exist", 0).show();
		}
	}

	private void initData(String path) {
		list.clear();
		File file = new File(path);
		final File[] files = file.listFiles();
		for (File f : files) {
			MyFile mFile = new MyFile();
			mFile.setName(f.getName());
			mFile.setPath(f.getAbsolutePath());
			if(f.isDirectory()){
				mFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
				mFile.setIcon(true);
			} else if(f.isFile()){
				String name = f.getName().toLowerCase();
				if("png".equals(name)||"jpg".equals(name)||"jpeg".equals(name)){
					mFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				} else {
					mFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
				}
			}
			list.add(mFile);
		}

		adapter = new FileAdapter(this, list);
		listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}
}
