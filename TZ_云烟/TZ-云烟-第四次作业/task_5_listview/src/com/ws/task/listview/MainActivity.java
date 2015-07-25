package com.ws.task.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	private List<Bean> data;
	private ListView lv;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lv = (ListView) findViewById(R.id.lv);
		initData();
		//适配器
		adapter = new MyAdapter(this, data);
		lv.setAdapter(adapter);
		//lisetView事件
		lv.setOnItemClickListener(this);
	}
	//数据
	private void initData() {
		int[] haed = { R.drawable.ahri, R.drawable.akali, R.drawable.alistar,
				R.drawable.amumu, R.drawable.anivia, R.drawable.annie,
				R.drawable.ashe, R.drawable.blitzcrank, R.drawable.brand,
				R.drawable.caitlyn };
		String[] name = { "Grace老师", "Crystal老师", "Rrick老师", "瑶瑶老师", "Danny老师", "鸿宇老师", "Elaine老师",
				"子漠老师", "Alex老师", "YSEO老师" };
		boolean[] sex = { true, true, false, true, false, false, true, false,
				true, false };
		String[] hobby = { "卖萌", "卖萌", "卖萌", "卖萌", "卖萌", "卖萌", "卖萌", "卖萌",
				"卖萌", "卖萌" };
//		int[] number = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
		data = new ArrayList<Bean>();
		int b = name.length;
		//循环赋值
		for(int i=0;i<b;i++){
			Bean hero = new Bean();
			hero.setHead(haed[i]);
			hero.setName(name[i]);
			//判断 true为女  false为男
			hero.setSex((sex[i])==true? "女":"男");
			hero.setHobby(hobby[i]);
//			hero.setNumber(number[i]);
			//加入到数据当中
			data.add(hero);
		}
	}
	
	
	//item的点击事件
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		//创建一个确定删除的对话框
		new AlertDialog.Builder(MainActivity.this).setTitle("注意").setMessage("确定要删除吗")
		.setIcon(R.drawable.ic_launcher).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//删除该条目的数据
				data.remove(arg2);
				//刷新listview
				adapter.notifyDataSetChanged();
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).show();//显示出来
		
	}

}
