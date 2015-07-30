package com.example.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
private String[] name={
	
		
};private int[] igs={
		R.drawable.face1,
		R.drawable.face2,
		R.drawable.face3,
		R.drawable.face4,
		R.drawable.face5,
		R.drawable.face6,
		R.drawable.face7,
		R.drawable.face8,
		
};
private ListView list;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final List<Map<String,Object>>Data=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 8; i++) {
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("name", "刘德华"+i);
			map.put("phone","18888"+i);
			map.put("p", igs[i]);
			Data.add(map);
		}
		
		//list=(ListView)findViewById(R.id.list);
		GridView gv=(GridView)findViewById(R.id.gv);
		final SimpleAdapter adapter=new SimpleAdapter(
				this, //上下文				
				Data, //数据
				R.layout.lg,//布局资源
				new String[]{"name","phone","p"},//String[] 数据				
				new int []{R.id.textView1,R.id.textView2,R.id.ig});//int[] 数据		
		//list.setAdapter(adapter);
		gv.setAdapter(adapter);
		//条目点击事件
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//getItem获取与指定的位置相关联的数据项的数据集。
				Map map= (Map) adapter.getItem(position);
				String name=(String) map.get("name");
				Toast.makeText(MainActivity.this, name, 1).show();
			}
		});
		
		//长按点击删除事件，
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			private Context context;

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Data.remove(position);//数据删除位置
				adapter.notifyDataSetChanged();//通知观察者,底层数据已经改变,任何视图反映了数据集应该刷新自己。
				
				Toast.makeText(getApplicationContext(), "sdasdaasd", 0).show();
			//	Toast.makeText(context, text, duration)//(context, text, duration)
				return false;                         //   背景下,  文本,  持续时间
			}
			
			
		});
	}

	

}
