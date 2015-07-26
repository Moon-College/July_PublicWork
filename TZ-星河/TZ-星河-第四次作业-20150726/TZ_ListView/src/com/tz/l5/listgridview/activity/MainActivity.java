package com.tz.l5.listgridview.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.tz_listgridview.R;
import com.tz.l5.listgridview.adapter.ListViewAdapter;
import com.tz.l5.listgridview.entity.Person;

public class MainActivity extends Activity {
	private ListView lvPersons=null;
	private List<Person> persons=new ArrayList<Person>();
	private ListViewAdapter adapter=null;
	private int[] icons={
			R.drawable.u1,
			R.drawable.u2,
			R.drawable.u3,
			R.drawable.u4,
			R.drawable.u5,
			R.drawable.u6,
			R.drawable.u7,
			R.drawable.u8,
			R.drawable.u9,
			R.drawable.u10,
			R.drawable.u11,
			R.drawable.u12,
			R.drawable.u13
	};
	private String[] hobbies={
		"唱歌",	
		"跳舞",	
		"篮球",	
		"足球",	
		"看书",	
		"看电影",	
		"游戏",	
		"游泳",	
		"爬山"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		lvPersons.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				persons.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
	}

	private void initData() {
		Random random=new Random();
		for(int i=0;i<13;i++){
			Person p=new Person();
			p.setIcon(icons[i]);
			p.setName("name"+i);
			String sex=null;
			if((i+1)%2==0){
				sex="男";
			}else{
				sex="女";
			}
			p.setSex(sex);
			int tmp=random.nextInt(10);
			if(tmp==0){
				tmp=10;
			}
			p.setBeauty(tmp);
			StringBuilder sb=new StringBuilder();
			int cnt=5;
			cnt=random.nextInt(9);
			if(cnt==0){
				cnt=5;
			}
			for(int j=0;j<cnt;j++){
				tmp=random.nextInt(cnt);
				while(sb.toString().contains(hobbies[tmp])){
					tmp=random.nextInt(cnt);
				}
				String hobby=hobbies[tmp];
				if(sb.length()==0){
					sb.append(hobby);
				}else{
					sb.append(","+hobby);
				}
			}
			p.setHobby(sb.toString());
			persons.add(p);
		}
		adapter=new ListViewAdapter(persons,this);
		lvPersons.setAdapter(adapter);
	}

	private void initView() {
		lvPersons=(ListView) findViewById(R.id.lv_list);
	}
}
