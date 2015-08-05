package com.tz.lsn5_adapter.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.tz.lsn5_adapter.R;
import com.tz.lsn5_adapter.adapter.MyGridAdapter;
import com.tz.lsn5_adapter.object.FoodInfo;

public class GridViewActivity extends BaseActivity {
	private GridView gv;
	private List<FoodInfo> list;
	private MyGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_gridview);
		setContentLayout(R.layout.activity_gridview);
		
		hideLeftBtn();
		hideRightBtn();
		setTitle("菜谱专辑");
		
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		gv = (GridView)findViewById(R.id.gv);
		
		list = getListItem();
		
		System.out.println(list.toString());
		
		adapter =  new MyGridAdapter(this, list);
		
		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				FoodInfo foodInfo = (FoodInfo) adapter.getItem(position);
				Toast.makeText(GridViewActivity.this, foodInfo.getUserName() + " 你是我的菜！", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	private List<FoodInfo> getListItem() {
		// TODO Auto-generated method stub
		
		List<FoodInfo> list = new ArrayList<FoodInfo>();
		list.add(new FoodInfo("干煸四季豆", R.drawable.food_1, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_1, "Ricky"));
		list.add(new FoodInfo("豆豉茄子炒豆角", R.drawable.food_2, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_2, "Grace"));
		list.add(new FoodInfo("干煸豆角", R.drawable.food_3, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_3, "Dallon"));
		list.add(new FoodInfo("蒸酿豆角", R.drawable.food_4, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_4, "Danny"));
		list.add(new FoodInfo("脆皮肠豆角", R.drawable.food_5, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_5, "Moon"));
		list.add(new FoodInfo("夏季豆角华丽吃法", R.drawable.food_6, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_6, "Alex"));
		list.add(new FoodInfo("肉沫豆角丝", R.drawable.food_7, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_7, "Elaine"));
		list.add(new FoodInfo("茄子炒豆角", R.drawable.food_8, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_8, "Cystle"));
		list.add(new FoodInfo("极品四季豆", R.drawable.food_9, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_9, "yoyo"));
		list.add(new FoodInfo("四季豆牛肉", R.drawable.food_10, 10, "四季豆俗称豆角，身材圆圆，豆荚水嫩，头顶尖尖的弯角惹人喜爱，是不少人家餐桌上的常见蔬菜之一", R.drawable.user_10, "Linda"));
		
		return list;
	}
	
	
}
