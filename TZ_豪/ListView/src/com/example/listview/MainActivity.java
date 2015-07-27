package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView listView;
	private List<QQ> list;
	private QqAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listView = new ListView(this);
		listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		setContentView(listView);
		listView.setOnItemClickListener(this);
		list = getListViewData();
		adapter = new QqAdapter(this, list);
		listView.setAdapter(adapter);
	}

	private List<QQ> getListViewData() {
		List<QQ> Qqs = new ArrayList<QQ>();
		for (int i = 0; i < 40; i++) {
			QQ qq = new QQ();
			qq.setName("aa" + i);
			qq.setSex(i % 2 == 0 ? 1 : 2);
			qq.setHobby("bb" + 1);
			qq.setFace(i);
			Qqs.add(qq);
		}
		return Qqs;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this,"刪除："+list.get(position).getName(),1).show();
		list.remove(position);
		adapter.notifyDataSetChanged();
		
	}
	

}
