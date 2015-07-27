package com.example.lesson5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lesson5.adapter.ProfileAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ProfileListActivity extends Activity {
	
	private ListView listView1;
	
	private List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	private ProfileAdapter adapter=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_activity);
		initView();
		initListener();
		initData();
		bindListView();
		
	}
	
	
	/**
	 * 
	 * 此方法描述的是：绑定Adapter
	 * @author: studio	 
	 * bindListView void
	 */
	private void bindListView() {
		 adapter=new ProfileAdapter(this, list);
		listView1.setAdapter(adapter);
		
	}


	/**
	 * 
	 * 初始化数据
	 * @author: studio	 
	 * bindListView void
	 */
	private void initData() {
		Map<String, Object> subMap=null;
		for (int i = 0; i <20; i++) {
			subMap=new HashMap<String, Object>();
			subMap.put("image", R.drawable.ic_launcher+"");
			subMap.put("name", "studio"+i);
			subMap.put("sex", "男");
			subMap.put("hobby", "coding");
			list.add(subMap);
		}
		
		
	}



	private void initView() {
		listView1=(ListView)this.findViewById(R.id.listView1);
		
	}
	
	
	private void initListener() {
		listView1.setOnItemClickListener(new MyItemClickListener());

	}
	
	class MyItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(ProfileListActivity.this, "开始删除了哈", 4).show();
			int index=(int) parent.getItemIdAtPosition(position);
			adapter.notifyListView(index);
		
			
		}
		
	}
	
	

}
