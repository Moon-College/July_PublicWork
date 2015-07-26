package com.study.listviewstudy;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.study.MyAdapter.MyAdapter;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化获得一个对象列表
		final ArrayList<mesageData> datas=initDatas();

		
		final ListView  listview = (ListView) findViewById(R.id.listview);
		
		//传统的ArrayAdapter，代码可用
/*		ArrayAdapter<String> adapter=new ArrayAdapter<String>(
				this, 
				R.layout.model, 
				R.id.model,
				getResources().getStringArray(R.array.hero));//读取strings文件中的数组hero
		listview.setAdapter(adapter);	*/	
		
		final MyAdapter myAdapter=new MyAdapter(this,datas);
		listview.setAdapter(myAdapter);
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					
					//长按删除并动态更新listview
					datas.remove(position);					
					myAdapter.notifyDataSetChanged();
					return false;
				}
		}	);
				
	}
	
	
	//初始化设置数据集
	ArrayList<mesageData>  initDatas(){
		ArrayList<mesageData> datas=new ArrayList<mesageData>();		
		for(int i=0;i<50;i++){
			mesageData data=new mesageData();
			data.setName("小乔"+i);
			data.setTime("发送时间：2015-06-25 17:00");
			data.setContent("暮然回首却在灯火阑珊处");
			datas.add(data);
		}
		return datas;
	}
}
