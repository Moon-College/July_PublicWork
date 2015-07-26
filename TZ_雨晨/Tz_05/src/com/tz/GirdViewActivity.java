package com.tz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tz.adapter.GridViewAdapter;
import com.tz.bean.DishMenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class GirdViewActivity extends Activity {

	GridViewAdapter adapter;
	private GridView girdView;

	private List<DishMenu> mDatas;
	private int[] images = { R.drawable.face1, R.drawable.face2,
			R.drawable.face3, R.drawable.face4, R.drawable.face5,
			R.drawable.face6, R.drawable.face7, R.drawable.face8 };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_main);
		initData();
		initView();
	}

	private void initData()
	{
		mDatas = new ArrayList<DishMenu>();
		for (int i = 0; i < 10; i++) {
			int index = new Random().nextInt(8);
			DishMenu dishMenu = new DishMenu();
			dishMenu.cooker = "Cooker" + i;
			dishMenu.introduction = "这道菜还是真心不错的";
			dishMenu.icon = images[index];
			dishMenu.img = R.drawable.cai;
			dishMenu.count = new Random().nextInt(30);
			mDatas.add(dishMenu);
		}
	}

	private void initView()
	{
		adapter = new GridViewAdapter(this,mDatas);
		girdView = (GridView) findViewById(R.id.gv_girdview);
		girdView.setAdapter(adapter);
	}
}
