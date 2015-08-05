package com.tz.explore.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tz.explore.R;
import com.tz.explore.adapter.MainAdapter;
import com.tz.explore.bean.Item;

public class MainActivity extends Activity implements OnItemClickListener {

	private MainAdapter mAdapter;

	private List<Item> mList = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.lv_main);
		mAdapter = new MainAdapter(this, mList);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(this);
		String path = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			path = Environment.getDataDirectory().getAbsolutePath();
		}
		loadSDcard(path);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Item item = mAdapter.getItem(position);
		File file = new File(item.getPath());
		if (file.isDirectory()) {
			loadSDcard(item.getPath());
		}
	}

	public void loadSDcard(String path) {
		mList.clear();
		File father = new File(path);
		String root = father.getParent();
		if (!TextUtils.isEmpty(root)) {
			Item item = new Item(root, "返回", false, BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			mList.add(item);
		}
		File[] files = father.listFiles();
		for (File file : files) {
			Item tempItem = new Item();
			tempItem.setName(file.getName());
			tempItem.setPath(file.getAbsolutePath());
			if (file.isDirectory())
				tempItem.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			else {
				Locale locale = Locale.getDefault();
				if (file.getName().toLowerCase(locale).endsWith(".png")
						|| file.getName().toLowerCase(locale).endsWith(".jpg")
						|| file.getName().toLowerCase(locale).endsWith(".jpeg")) {
					tempItem.setImg(true);
				} else {
					tempItem.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
			}
			mList.add(tempItem);
		}
		mAdapter.notifyDataSetChanged();
	}
}
