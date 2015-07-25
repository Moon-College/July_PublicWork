package com.example.lesson5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lesson5.adapter.MyGridViewAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class HaoDouActivity extends Activity {
	
	private GridView gridview;
	private MyGridViewAdapter adapter;
	private List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hao_dou_activity);
		initView();
		initListener();
		initData();
		bindAdapter();
	}
	
	/**
	 * 
	 * 初始化数据
	 * @author: studio	 
	 * bindListView void
	 */
	private void initData() {
		Map<String, Object> map1=new HashMap<String, Object>();
		map1.put("image", R.drawable.face1);
		map1.put("countTxt", "第一道菜");
		map1.put("title", "这酸爽,哈哈哈哈");
		map1.put("nick", "studio1");
		map1.put("avatar", R.drawable.ic_launcher);
		list.add(map1);
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("image", R.drawable.face2);
		map2.put("countTxt", "第二道菜");
		map2.put("title", "这酸爽,哈哈哈哈");
		map2.put("nick", "studio2");
		map2.put("avatar", R.drawable.ic_launcher);
		list.add(map2);
		
		Map<String, Object> map3=new HashMap<String, Object>();
		map3.put("image", R.drawable.face3);
		map3.put("countTxt", "第三道菜");
		map3.put("title", "这酸爽,哈哈哈哈");
		map3.put("nick", "studio3");
		map3.put("avatar", R.drawable.ic_launcher);
		list.add(map3);
		
		Map<String, Object> map4=new HashMap<String, Object>();
		map4.put("image", R.drawable.face4);
		map4.put("countTxt", "第四道菜");
		map4.put("title", "这酸爽,哈哈哈哈");
		map4.put("nick", "studio4");
		map4.put("avatar", R.drawable.ic_launcher);
		list.add(map4);
		
		Map<String, Object> map5=new HashMap<String, Object>();
		map5.put("image", R.drawable.face5);
		map5.put("countTxt", "第五道菜");
		map5.put("title", "这酸爽,哈哈哈哈");
		map5.put("nick", "studio5");
		map5.put("avatar", R.drawable.ic_launcher);
		list.add(map5);
		
	}


	private void initView() {
		gridview=(GridView)this.findViewById(R.id.gridView1);
	}
	
	private void initListener() {
		gridview.setOnItemLongClickListener(new myItemLongClickListener());

	}
	/**
	 * 
	 * 绑定Adapter
	 * @author: studio	 
	 * bindListView void
	 */
	public void bindAdapter(){
		adapter=new MyGridViewAdapter(this,list);
		gridview.setAdapter(adapter);
	}
	
	class myItemLongClickListener implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				final int position, long id) {
			new AlertDialog.Builder(HaoDouActivity.this).setMessage("是否要删除此条信息").setPositiveButton("删除", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				list.remove(position);
				adapter.notifyDataSetChanged();
					
				}
			}).setNegativeButton("不删除", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					
				}
			}).create().show();
			return false;
		}
		
	}

}
