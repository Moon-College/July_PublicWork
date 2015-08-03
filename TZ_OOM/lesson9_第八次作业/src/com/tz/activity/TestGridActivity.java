package com.tz.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.tz.activity.adapter.GridAdapter;

public class TestGridActivity extends GridActivity {
	
	//private ArrayAdapter<String> adapter;
	
	private GridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*adapter = new ArrayAdapter<String>(
											this, 
											android.R.layout.simple_list_item_1, 
											new String[]{
													"Java",
													"Android",
													"Objective-C",
													"C++",
													"C",
													"C#",
											});
		
		
		setNumColumns(5);
		setGridAdapter(adapter);*/
		
		adapter = new GridAdapter(this, 
								  R.layout.grid_view_item_txt_img, 
								  initData(), 
								  new String[]{"icon","name"}, 
								  new int[]{R.id.img,R.id.tv});
		setGridAdapter(adapter);
		
	}
	
	private List<Map<String, Object>> initData(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		for(int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_launcher);
			map.put("name", "Item_" + (i+1));
			list.add(map);
		}
		
		return list;
	}
	
	
	@Override
	protected void onGridItemClick(GridView l, View v, int position, long id) {
		super.onGridItemClick(l, v, position, id);
		/*String selectTxt = adapter.getItem(position);
		Toast.makeText(this, selectTxt, Toast.LENGTH_SHORT).show();*/
		
		Map<String, Object> map = adapter.getItem(position);
		
		Toast.makeText(this, map.get("name").toString(), Toast.LENGTH_SHORT).show();
		
	}
	
	
}
