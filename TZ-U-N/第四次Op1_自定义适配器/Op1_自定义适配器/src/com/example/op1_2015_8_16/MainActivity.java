package com.example.op1_2015_8_16;

import java.util.ArrayList;
import java.util.List;

import com.pujie_dome.Ctiy;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private List<Ctiy>mctiy=new ArrayList<Ctiy>();
	private String[] cities = {
			"北京",
			"广东",
			"深圳",
			"长沙",
			"武汉",
			"武昌",
			"abc",
			"aac",
			"acc",
			"aaa",
			"广州"
		};
	private String[] citiesw = {
			"北京",
			"广东",
			"深圳",
			"长沙",
			"武汉",
			"武昌",
			"abc",
			"aac",
			"acc",
			"aaa",
			"广州"
	};
	
	private int[] icons = {
			R.drawable.face1,
			R.drawable.face2,
			R.drawable.face3,
			R.drawable.face4,
			R.drawable.face5,
			R.drawable.face6,
			R.drawable.face7,
			R.drawable.face8
	};
	private int[] iconc = {
			R.drawable.food1,
			R.drawable.food2,
			R.drawable.food3,
			R.drawable.food4,
			R.drawable.food5,
			R.drawable.food6,
			R.drawable.food7,
			R.drawable.food8
	};

	private GridView gv;
	private ListView lv;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.griv_1);
		//lv = (ListView) findViewById(R.id.lt);
		gv = (GridView) findViewById(R.id.grid);
		adapter = new MyAdapter(mctiy, this);
		gv.setAdapter(adapter);
		initdata();
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				mctiy.remove(position);
			adapter.notifyDataSetChanged();
			Toast.makeText(getApplicationContext(), "长按删除", 0).show();
				return false;
			}
		});
		
	}
	private void initdata() {
		for (int i = 0; i <8; i++) {
			Ctiy ctiy =new Ctiy(cities[i],iconc[i],citiesw[i],icons[i]);
			mctiy.add(ctiy);
			
		}
	}

	

}
