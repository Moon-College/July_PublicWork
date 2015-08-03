package com.example.listgridview4lession5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.customAdapter.MyAdapter;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity{
	MyAdapter adapter ;
	List<Map<String, Object>> aaList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aaList=getData();
		 adapter = new MyAdapter(this,getData());
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.v("MyListView4-click", (String)getData().get(position).get("name"));
		System.out.println("aaList长度->"+aaList.size());
		aaList.remove(position);
		System.out.println("aaList长度->"+aaList.size());
		adapter.notifyDataSetInvalidated();
	}
	
	private List<Map<String, Object>> getData() {
		//声明一个装了map的的list,map中的KV 是 String和object
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<10;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			if(i<6){
				map.put("name", "男姓名"+i);
				map.put("sex", "male");
				map.put("facelevel",""+ i);
				map.put("hobbit","爱好"+i);
			}else{
			    map.put("name", "女"+i);
			    map.put("sex", "female");
			     map.put("facelevel", ""+ i);
			    map.put("hobbit","爱好"+i);
			}
			 list.add(map);
		}
		
		return list;
	}

}
