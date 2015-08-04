package com.tz.lsn5_adapter.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lsn5_adapter.R;
import com.tz.lsn5_adapter.object.FoodInfo;

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
		
		TextView tv_foodName = (TextView)view.findViewById(R.id.tv_foodName);
		ImageView iv_foodImg = (ImageView)view.findViewById(R.id.iv_foodImg);
		TextView tv_des = (TextView)view.findViewById(R.id.tv_des);
		TextView tv_count = (TextView)view.findViewById(R.id.tv_count);
		ImageView iv_icon = (ImageView)view.findViewById(R.id.iv_icon);
		TextView tv_user = (TextView)view.findViewById(R.id.tv_userName);
		
		tv_foodName.setText(foodInfo.getFoodName());
		iv_foodImg.setBackgroundResource(foodInfo.getFoodImgId());
		tv_des.setText(foodInfo.getDes());
		tv_count.setText(String.valueOf(foodInfo.getCount()) + "µÀ²Ë");
		iv_icon.setBackgroundResource(foodInfo.getUserImgId());
		tv_user.setText(foodInfo.getUserName());
		
		return view;
	}

}
