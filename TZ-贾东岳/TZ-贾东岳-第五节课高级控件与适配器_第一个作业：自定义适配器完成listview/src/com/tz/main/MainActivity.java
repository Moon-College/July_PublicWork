package com.tz.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tz.first.R;
import com.tz.fourth.second.StudentsAdapter;
import com.tz.fourth.second.StudentsInfo;

/**
 * 第五节课第1个作业,自定义适配器完成listview
 * 
 * @author JDY
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.students_ll);

		List<StudentsInfo> list = new ArrayList<StudentsInfo>();
		
		list.add(new StudentsInfo(R.drawable.face1,"豆豆", "男", "60","吃饭"));
		list.add(new StudentsInfo(R.drawable.face2,"同学b", "男", "70","睡觉"));
		list.add(new StudentsInfo(R.drawable.face3,"同学c", "女","80", "打豆豆"));
		list.add(new StudentsInfo(R.drawable.face4,"同学d", "女", "90","吃饭"));
		list.add(new StudentsInfo(R.drawable.face5,"同学e", "男", "60", "打豆豆"));
		list.add(new StudentsInfo(R.drawable.face6,"同学f", "女", "80","吃饭"));
		list.add(new StudentsInfo(R.drawable.face7,"同学g", "男", "90", "打豆豆"));
		list.add(new StudentsInfo(R.drawable.face8,"同学h", "女", "86","睡觉"));
		list.add(new StudentsInfo(R.drawable.face1,"同学i", "男", "88","睡觉"));
		list.add(new StudentsInfo(R.drawable.face2,"同学j", "男", "70", "打豆豆"));
		list.add(new StudentsInfo(R.drawable.face3,"同学k", "女", "100", "打豆豆"));
		list.add(new StudentsInfo(R.drawable.face4,"同学i", "男", "80","睡觉"));
		list.add(new StudentsInfo(R.drawable.face5,"同学j", "男", "75", "打豆豆"));
		list.add(new StudentsInfo(R.drawable.face6,"同学k", "女", "99", "打豆豆"));

		ListView studentsListView = (ListView) findViewById(R.id.students_lv);
		
		StudentsAdapter adapter = new StudentsAdapter(this, list);
		studentsListView.setAdapter(adapter);
		//设置长按删除事件
		studentsListView.setOnItemLongClickListener(adapter.longListener);
	}
	
	
}
