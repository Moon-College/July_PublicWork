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
		
		initData();  //��ʼ������
		
		adapter = new GvAdapter(this, lv_dish);
		
		gv_show.setAdapter(adapter);
	}
	/**
	 * Ϊlist���ϸ�ֵ
	 */
	private void initData() {
		lv_dish = new ArrayList<Dish>();
		Dish dish1 = new Dish(R.drawable.food1,"����ʲôС��",R.drawable.face1,"�ŷ�");
		Dish dish2 = new Dish(R.drawable.food2,"����ʲôС��",R.drawable.face2,"�ܲ�");
		Dish dish3 = new Dish(R.drawable.food3,"����ʲôС��",R.drawable.face3,"��Ȩ");
		Dish dish4 = new Dish(R.drawable.food4,"����ʲôС��",R.drawable.face4,"����");
		Dish dish5 = new Dish(R.drawable.food5,"����ʲôС��",R.drawable.face5,"�����");
		Dish dish6 = new Dish(R.drawable.food6,"����ʲôС��",R.drawable.face6,"����");
		Dish dish7 = new Dish(R.drawable.food7,"����ʲôС��",R.drawable.face7,"����");
		Dish dish8 = new Dish(R.drawable.food8,"����ʲôС��",R.drawable.face8,"����");
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
