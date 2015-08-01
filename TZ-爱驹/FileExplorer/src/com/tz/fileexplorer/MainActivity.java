package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;

public class MainActivity extends Activity implements OnItemClickListener {

	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	private ListView listView;
	private String currPath;
	private int top;

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
		File dirs = new File(path);
		final File[] files = dirs.listFiles();
		Arrays.sort(files);
		for (File f : files) {
			MyFile mFile = new MyFile();

			mFile.setName(f.getName());
			if (f.getName().trim().length() > 24) {
				String front = f.getName().substring(0, 16);
				String back = f.getName().substring(f.getName().length()-5);
				mFile.setName(front+"..."+back);
			}
			mFile.setPath(f.getAbsolutePath());
			setFileIcon(f, mFile);
			list.add(mFile);
		}
		currPath = path;
		adapter = new FileAdapter(this, list);
		listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = list.get(position).getPath();
		File file = new File(path);
		if (file.isDirectory()) {
			initData(path);
			top = position;
		}

	}

	private void setFileIcon(File f, MyFile mFile) {
		if (f.isDirectory()) {
			mFile.setIcon(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			mFile.setIcon(true);
		} else if (f.isFile()) {
			String name = f.getName().toLowerCase(Locale.US);
			if (name.endsWith("png") || name.endsWith("jpg")
					|| name.endsWith("jpeg")) {
				mFile.setIcon(null);
				mFile.setIcon(false);
			} else {
				mFile.setIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher));
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		File file = new File(currPath);
		if (file.getParent() != null) {
			initData(file.getParent());
		} else {
			finish();
		}
	}
}
