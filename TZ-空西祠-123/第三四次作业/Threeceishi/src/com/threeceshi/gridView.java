package com.threeceshi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.threeceishi.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class gridView extends Activity {
	private GridView gridview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		
		init();//初始化控件，并创建适配器，进行关联
		
	}

	private void init() {
		// TODO Auto-generated method stub
		gridview=(GridView) findViewById(R.id.gridview);
		int[] imageid=new int[]{R.drawable.face1,R.drawable.face2,R.drawable.face3,R.drawable.face4,R.drawable.face5,R.drawable.face6,R.drawable.face7,R.drawable.face8};
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<imageid.length;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("image", imageid[i]);
			map.put("name", (i+8)+"道菜");
			map.put("title", "牛肉炖土豆，好吃不得了，你要吗"+i);
			map.put("tou", imageid[i]);
			map.put("name1", "名字"+i);
			list.add(map);
		}
		SimpleAdapter adapter=new SimpleAdapter(this, list, R.layout.gridviewitem, new String[]{"image","name","title","tou","name1"}, new int[]{R.id.image,R.id.txtview1,R.id.textview2,R.id.image1,R.id.textview3});
		gridview.setAdapter(adapter);
	}

}
