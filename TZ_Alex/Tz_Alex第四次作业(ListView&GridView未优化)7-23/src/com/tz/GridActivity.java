package com.tz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.tz.adapter.GvAdapter;
import com.tz.bean.Dish;

public class GridActivity extends Activity {

	private GridView gv_show;
	private List<Dish> lv_dish;
	private GvAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		gv_show = (GridView) findViewById(R.id.gv_show);
		
		initData();  //初始化数据
		
		adapter = new GvAdapter(this, lv_dish);
		
		gv_show.setAdapter(adapter);
	}
	/**
	 * 为list集合赋值
	 */
	private void initData() {
		lv_dish = new ArrayList<Dish>();
		Dish dish1 = new Dish(R.drawable.food1,"这是什么小吃",R.drawable.face1,"张飞");
		Dish dish2 = new Dish(R.drawable.food2,"这是什么小吃",R.drawable.face2,"曹操");
		Dish dish3 = new Dish(R.drawable.food3,"这是什么小吃",R.drawable.face3,"孙权");
		Dish dish4 = new Dish(R.drawable.food4,"这是什么小吃",R.drawable.face4,"吕布");
		Dish dish5 = new Dish(R.drawable.food5,"这是什么小吃",R.drawable.face5,"诸葛亮");
		Dish dish6 = new Dish(R.drawable.food6,"这是什么小吃",R.drawable.face6,"貂蝉");
		Dish dish7 = new Dish(R.drawable.food7,"这是什么小吃",R.drawable.face7,"关羽");
		Dish dish8 = new Dish(R.drawable.food8,"这是什么小吃",R.drawable.face8,"刘备");
		lv_dish.add(dish1);
		lv_dish.add(dish2);
		lv_dish.add(dish3);
		lv_dish.add(dish4);
		lv_dish.add(dish5);
		lv_dish.add(dish6);
		lv_dish.add(dish7);
		lv_dish.add(dish8);
	}

	
}
