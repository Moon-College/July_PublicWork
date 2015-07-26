package com.tz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tz.adapter.ListViewAdapter;
import com.tz.bean.Person;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewActivity extends Activity {
	private ListView mListView;
	private List<Person> mDatas;
	private ListViewAdapter adapter;
	private int[] images = { R.drawable.face1, R.drawable.face2,
			R.drawable.face3, R.drawable.face4, R.drawable.face5,
			R.drawable.face6, R.drawable.face7, R.drawable.face8 };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);
		initData();
		initView();
	}

	/**
	 * 初始化虚拟数据
	 * 
	 * @param
	 * @return void
	 */
	private void initData()
	{
		mDatas = new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			int index = new Random().nextInt(8);
			Person person = new Person(images[index], "TZ" + i,
					(i & 1) != 0 ? 0 : 1, "颜值-" + i, "爱好：球球" + i);
			mDatas.add(person);
		}
	}

	private void initView()
	{
		adapter = new ListViewAdapter(this, mDatas);
		mListView = (ListView) findViewById(R.id.lv_listview);
		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				adapter.remove(position);
			}
		});
	}

}
