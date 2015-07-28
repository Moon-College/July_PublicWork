package com.tz.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.tz.android.R;
import com.tz.android.bean.Student;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author 徐文龙
 * 第5次作业的第一个listview显示
 * 要求1，每个条目是同学的头像+网名+性别+颜值+爱好
	要求2，条目数不少于10  ，男生背景用蓝色，妹子背景用红色
	要求3，点击对应的条目，移除该条目
 * */
public class Tz_05_01 extends Activity {
	
	
	private List<Student> lists;
	
	private MyAdapter adapter;
	private ListView listview;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tz_05_01_activity);
		context=this;
		lists=new ArrayList<Student>();
		listview=(ListView) findViewById(R.id.tz_05_list);
		
		adapter=new MyAdapter(lists,context);
		
		listview.setAdapter(adapter);
		initList();
		adapter.notifyDataSetChanged();
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(arg0.getAdapter() instanceof MyAdapter){
					Toast.makeText(context, "是这个", Toast.LENGTH_LONG).show();
					lists.remove(arg2);
					adapter.notifyDataSetChanged();
				}
				
			}
			
		});
		
		
	}
	
	void initList(){
		lists.clear();
		lists.add(new Student("大龙1","男","1", "打篮球",R.drawable.face1));
		lists.add(new Student("大龙2","男","12", "打篮球",R.drawable.face2));
		lists.add(new Student("大龙3","女","13", "打篮球",R.drawable.face3));
		lists.add(new Student("大龙4","男","14", "打篮球",R.drawable.face4));
		lists.add(new Student("大龙5","女","15", "打篮球",R.drawable.face5));
		lists.add(new Student("大龙6","女","16", "打篮球",R.drawable.face6));
		lists.add(new Student("大龙7","男","177", "打篮球",R.drawable.face7));
		lists.add(new Student("大龙8","女","18", "打篮球",R.drawable.face8));
		lists.add(new Student("大龙9","男","19", "打篮球",R.drawable.face1));
		lists.add(new Student("大龙10","女","21", "打篮球",R.drawable.face2));
	}
	
	
	
	class MyAdapter extends BaseAdapter{
		
		private List<Student> lists;
		private Context context;
		
		
		

		public MyAdapter(List<Student> lists, Context context) {
			super();
			this.lists = lists;
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lists.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return lists.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if(arg1==null){
				arg1=LayoutInflater.from(context).inflate(R.layout.tz_05_01_list_item,null);
			}
			if(lists.get(arg0).getSex().equals("男")){
				arg1.setBackgroundColor(Color.BLUE);
			}else{
				arg1.setBackgroundColor(Color.RED);
			}
			ImageView img=(ImageView) arg1.findViewById(R.id.tz_05_list_item_img);
			TextView text1=(TextView) arg1.findViewById(R.id.tz_05_list_item_text1);
			TextView text2=(TextView) arg1.findViewById(R.id.tz_05_list_item_text2);
			text1.setText(lists.get(arg0).getNickname());
			img.setImageDrawable(getResources().getDrawable(lists.get(arg0).getHead_img_id()));
			text2.setText(lists.get(arg0).getLove());
			

			return arg1;
		}
		
		
		
	}

}
