package com.tz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.tzclass8_homework.R;
import com.tz.adapter.FileAdapter;
import com.tz.bean.MyFile;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView lv_list;
	private String rootPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_list = (ListView) findViewById(R.id.lv_list);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			rootPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		}

		initData(rootPath);

		lv_list.setOnItemClickListener(this);
		lv_list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				View conView = View.inflate(
						MainActivity.this, R.layout.popup_item, null);
				PopupWindow pw = new PopupWindow(conView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						true);
				
				pw.setBackgroundDrawable(getResources().getDrawable(R.drawable.local_popup_bg));
				int[] location = new int[2];
				view.getLocationInWindow(location);
				pw.showAtLocation(view, Gravity.TOP|Gravity.LEFT, location[0]+150, location[1]-100);
				
				
				return true;
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyFile myFile = (MyFile) adapter.getItem(position);
		File file = new File(myFile.getPath());
		if (file.isDirectory()) {
			initData(file.getAbsolutePath());
		}
	}

	private void initData(String path) {

		list.clear();
		File file = new File(path);
		File[] listFiles = file.listFiles();

		MyFile backFile = new MyFile();
		backFile.setName("·µ»Ø");
		backFile.setBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.dirs));
		backFile.setPath(file.getParentFile().getAbsolutePath());
		backFile.setIsPicBoolean(false);
		list.add(backFile);

		for (File f : listFiles) {
			MyFile myFile = new MyFile();
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			if (f.isDirectory()) {
				myFile.setIsPicBoolean(false);
				myFile.setBitmap(BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs));
			} else {
				String str = f.getName().toLowerCase();
				if (str.endsWith(".jpg") || str.endsWith(".gif")
						|| str.endsWith(".png") || str.endsWith(".jpeg")) {
					myFile.setIsPicBoolean(true);
					myFile.setBitmap(null);
				} else {
					myFile.setIsPicBoolean(false);
					myFile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
			}
			list.add(myFile);
		}
		adapter = new FileAdapter(this, list);
		lv_list.setAdapter(adapter);
	}

}
