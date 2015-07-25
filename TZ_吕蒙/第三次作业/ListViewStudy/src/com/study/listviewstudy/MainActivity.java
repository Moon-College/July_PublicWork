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
		
		//��ʼ�����һ�������б�
		final ArrayList<mesageData> datas=initDatas();

		
		final ListView  listview = (ListView) findViewById(R.id.listview);
		
		//��ͳ��ArrayAdapter���������
/*		ArrayAdapter<String> adapter=new ArrayAdapter<String>(
				this, 
				R.layout.model, 
				R.id.model,
				getResources().getStringArray(R.array.hero));//��ȡstrings�ļ��е�����hero
		listview.setAdapter(adapter);	*/	
		
		final MyAdapter myAdapter=new MyAdapter(this,datas);
		listview.setAdapter(myAdapter);
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					
					//����ɾ������̬����listview
					datas.remove(position);					
					myAdapter.notifyDataSetChanged();
					return false;
				}
		}	);
				
	}
	
	
	//��ʼ���������ݼ�
	ArrayList<mesageData>  initDatas(){
		ArrayList<mesageData> datas=new ArrayList<mesageData>();		
		for(int i=0;i<50;i++){
			mesageData data=new mesageData();
			data.setName("С��"+i);
			data.setTime("����ʱ�䣺2015-06-25 17:00");
			data.setContent("ĺȻ����ȴ�ڵƻ���ɺ��");
			datas.add(data);
		}
		return datas;
	}
}
