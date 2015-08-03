package com.tz.lsn5_adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyGridAdapter extends BaseAdapter {

	private Context context;
	private List<FoodInfo> list;
	private LayoutInflater mLayoutInfalter;

	public MyGridAdapter(Context context, List<FoodInfo> list) {
		super();
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		mLayoutInfalter = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = mLayoutInfalter.inflate(R.layout.activity_gridview_item, null);
		
		FoodInfo foodInfo = (FoodInfo) getItem(position);
		
		
		return null;
	}

}
