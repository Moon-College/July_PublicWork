package com.example.menulayout01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GridView gv1;

	private SimpleAdapter adapter;
	private String [] text1={
			"牛肉要这么做","给烟民的灵心清单","a","d","d","df","df","df"
	};
	private int[] icon={
			R.drawable.face1,
			R.drawable.face2,
			R.drawable.face3,
			R.drawable.face4,
			R.drawable.face5,
			R.drawable.face6,
			R.drawable.face7,
			R.drawable.face8
	};
	private String[] s1={
		"牛肉要这么做","给烟民的灵心清单","a","d","d","df","df","df"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gv1 = (GridView)findViewById(R.id.gv1);
		
		initData();
		gv1.setAdapter(adapter);
		
		gv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Toast.makeText(MainActivity.this, "小笼包一米二脑袋占一半", 1).show();
				
			}
		});
	}
	private void initData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		
		for(int i = 0;i<8;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("刘备", icon[i]);
			map.put("刘德华", "一米二"+i);
			map.put("小笼包一米八", "一米二"+i);
			
		
			
			list.add(map);
		}
		
		
		adapter = new SimpleAdapter(this, list, R.layout.listitem, 
				new String[]{"刘备","小笼包一米八","刘德华",}, 
				new int[]{R.id.im1,R.id.tv1,R.id.tv2});
		
	}
}
