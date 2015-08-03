package com.tz.lsn5_adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;

public class GridViewActivity extends Activity {
	private GridView gv;
	private List<FoodInfo> list;
	private MyGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		gv = (GridView)findViewById(R.id.gv);
		
		list = getListItem();
		
		adapter =  new MyGridAdapter(this,list);
		
		gv.setAdapter(adapter);
	}

	private List<FoodInfo> getListItem() {
		// TODO Auto-generated method stub
		
		List<FoodInfo> list = new ArrayList<FoodInfo>();
		list.add(new FoodInfo("干煸四季豆", R.drawable.food_1, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_1, "aaa"));
		list.add(new FoodInfo("豆豉茄子炒豆角", R.drawable.food_2, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_2, "bbb"));
		list.add(new FoodInfo("干煸豆角", R.drawable.food_3, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_3, "ccc"));
		list.add(new FoodInfo("蒸酿豆角", R.drawable.food_4, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_4, "ddd"));
		list.add(new FoodInfo("脆皮肠豆角", R.drawable.food_5, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_5, "eee"));
		list.add(new FoodInfo("夏季豆角华丽吃法", R.drawable.food_6, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_6, "fff"));
		list.add(new FoodInfo("肉沫豆角丝", R.drawable.food_7, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_7, "ggg"));
		list.add(new FoodInfo("茄子炒豆角", R.drawable.food_8, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_8, "hhh"));
		list.add(new FoodInfo("极品四季豆", R.drawable.food_9, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_9, "iii"));
		list.add(new FoodInfo("四季豆牛肉", R.drawable.food_10, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_10, "jjj"));
		
		return list;
	}
	
	
}
