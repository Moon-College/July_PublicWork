package com.tz.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.tz.R;
import com.tz.bean.Dish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GvAdapter extends BaseAdapter {
	Context context;
	List<Dish> lv_dish;
	private ImageView iv_food,iv_userFace;
	private TextView tv_foodName,tv_userName;
	
	public GvAdapter(Context context, List<Dish> lv_dish) {
		super();
		this.context = context;
		this.lv_dish = lv_dish;
	}

	@Override
	public int getCount() {
		return lv_dish.size();
	}

	@Override
	public Object getItem(int position) {
		return lv_dish.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.gv_item, null);
		iv_food = (ImageView) view.findViewById(R.id.iv_food);
		iv_userFace = (ImageView) view.findViewById(R.id.iv_userFace);
		tv_foodName = (TextView) view.findViewById(R.id.tv_foodName);
		tv_userName = (TextView) view.findViewById(R.id.tv_userName);
		
		iv_food.setBackgroundResource(lv_dish.get(position).getDishImg());
		iv_userFace.setBackgroundResource(lv_dish.get(position).getUserImg());
		tv_foodName.setText(lv_dish.get(position).getFoodName());
		tv_userName.setText(lv_dish.get(position).getUserName());
		return view;
	}

}
