package com.tz.lsn5_adapter;

import java.util.List;

import android.R.anim;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {
	private List<UserInfo> list;
	private LayoutInflater mLayoutInfalter;

	public MyBaseAdapter(Context context,List<UserInfo> list) {
		super();
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

		View view = mLayoutInfalter.inflate(R.layout.activity_listview_item, null);

		UserInfo userInfo = (UserInfo) getItem(position);
		
		ImageView icon = (ImageView) view.findViewById(R.id.icon);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView nickName = (TextView) view.findViewById(R.id.nickName);
		TextView sex = (TextView) view.findViewById(R.id.sex);
		TextView faceValue = (TextView) view.findViewById(R.id.faceValue);
		TextView hobby = (TextView) view.findViewById(R.id.hobby);
		
		if("ÄÐ".equals(userInfo.getSex())) {
			view.setBackgroundColor(Color.rgb(0, 150, 0));
		} else if("Å®".equals(userInfo.getSex())) {
			view.setBackgroundColor(Color.rgb(150, 0, 0));
		} else {
			view.setBackgroundColor(Color.GREEN);
		}

		icon.setImageResource(userInfo.getImgId());
		name.setText(userInfo.getName());
		nickName.setText(userInfo.getNickname());
		sex.setText(userInfo.getSex());
		faceValue.setText(Integer.toString(userInfo.getFaceValue()));
		hobby.setText(userInfo.getHobby());
		
		return view;
	}

}
