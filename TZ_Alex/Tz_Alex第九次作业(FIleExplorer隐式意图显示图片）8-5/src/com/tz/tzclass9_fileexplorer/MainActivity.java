package com.tz.tzclass9_fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tz.adapter.FileAdapter;
import com.tz.bean.MyFile;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView mlListView;
	private String rootPath;
	private List<MyFile> mList;
	private FileAdapter adapter;
	private int tempPosition;
	private PopupWindow pWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mlListView = (ListView) findViewById(R.id.lv_show);
		mList = new ArrayList<MyFile>();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			rootPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		}
		initData(rootPath);

		mlListView.setOnItemClickListener(this);

		mlListView.setOnItemLongClickListener(listener);

	}

	OnItemLongClickListener listener = new OnItemLongClickListener() {


		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			tempPosition = position;
			View conView = View.inflate(MainActivity.this, R.layout.pop_list,
					null);
			pWindow = new PopupWindow(conView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, true);
			pWindow.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.local_popup_bg));
			int[] location = new int[2];
			view.getLocationInWindow(location);
			pWindow.showAtLocation(view, Gravity.TOP | Gravity.RIGHT,
					location[0], location[1]);
			TextView tv_del = (TextView) conView.findViewById(R.id.tv_del);
			TextView tv_detail = (TextView) conView
					.findViewById(R.id.tv_detail);
			tv_del.setOnClickListener(popListener);
			tv_detail.setOnClickListener(popListener);
			
			return false;
		}
	};

	OnClickListener popListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_del:
				MyFile myFile = (MyFile) adapter.getItem(tempPosition);
				mList.remove(myFile);
				adapter.notifyDataSetChanged();
				pWindow.dismiss();
				break;
			case R.id.tv_detail:
				isDir(tempPosition);
				pWindow.dismiss();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		isDir(position);
	}

	private void initData(String path) {
		mList.clear();

		File file = new File(path);
		File[] listFile = file.listFiles();

		MyFile backoff = new MyFile();
		backoff.setName("∑µªÿ…œ“ª≤„");
		if(file.getParentFile().getAbsolutePath().equals(rootPath)){
			backoff.setPath(rootPath);
		}
		backoff.setPath(file.getParentFile().getAbsolutePath());
		backoff.setIsPic(false);
		backoff.setBm(BitmapFactory.decodeResource(getResources(),
				R.drawable.dirs));
		mList.add(backoff);

		for (File f : listFile) {
			MyFile myFile = new MyFile();
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			if (f.isDirectory()) {
				myFile.setIsPic(false);
				myFile.setBm(BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs));
			} else {
				String str = f.getName().toLowerCase();
				if (str.endsWith("gif") || str.endsWith("png")
						|| str.endsWith("jpeg") || str.endsWith("jpg")) {
					myFile.setIsPic(true);
					myFile.setBm(null);
				} else {
					myFile.setIsPic(false);
					myFile.setBm(BitmapFactory.decodeResource(getResources(),
							R.drawable.file));
				}
			}
			mList.add(myFile);
		}
		adapter = new FileAdapter(this, mList);
		mlListView.setAdapter(adapter);
	}

	private void isDir(int position) {
		MyFile myFile = (MyFile) adapter.getItem(position);
		File file = new File(myFile.getPath());
		if (file.isDirectory()) {
			initData(file.getAbsolutePath());
		}else{
			Intent intent = new Intent();
			intent.setAction("android.intent.action.FileDetail");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.setData(Uri.parse("file:"+file.getAbsoluteFile()));
			startActivity(intent);
		}
	}
}
