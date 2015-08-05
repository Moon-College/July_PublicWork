package com.threeceshi;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.threeceishi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
public class Listadapter extends Activity{
	private ListView listview;
	private List<Map<String, Object>> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
	    listview=(ListView) findViewById(R.id.listview1);
int[] imageid=new int[]{R.drawable.face1,R.drawable.face2,R.drawable.face3,R.drawable.face4,R.drawable.face5,R.drawable.face6,R.drawable.face7,R.drawable.face8,R.drawable.face1,R.drawable.face2};
		
		list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<imageid.length;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("image", imageid[i]);
			map.put("name", "张三"+i);
			if(i%2==0){
				map.put("sex", "男");
				map.put("hoppy", "唱歌");
			}else{
				map.put("sex", "女");
				map.put("hoppy", "打球");
			}
			map.put("numble", (80+i));
			list.add(map);
		}
		//Collections.addAll(c, a);将数组转化为集合
		final SimpleAdapter adapter=new SimpleAdapter(this, list, R.layout.activity_main, new String[]{"image","name","sex","hoppy","numble"}, new int[]{R.id.imageview,R.id.name,R.id.sex,R.id.hoppy,R.id.number});
	    listview.setAdapter(adapter);
	    
        listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			    
				list.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
			 }



}
