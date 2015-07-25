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
			"ţ��Ҫ��ô��","������������嵥","a","d","d","df","df","df"
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
		"ţ��Ҫ��ô��","������������嵥","a","d","d","df","df","df"
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
				
				Toast.makeText(MainActivity.this, "С����һ�׶��Դ�ռһ��", 1).show();
				
			}
		});
	}
	private void initData() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		
		for(int i = 0;i<8;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("����", icon[i]);
			map.put("���»�", "һ�׶�"+i);
			map.put("С����һ�װ�", "һ�׶�"+i);
			
		
			
			list.add(map);
		}
		
		
		adapter = new SimpleAdapter(this, list, R.layout.listitem, 
				new String[]{"����","С����һ�װ�","���»�",}, 
				new int[]{R.id.im1,R.id.tv1,R.id.tv2});
		
	}
}
