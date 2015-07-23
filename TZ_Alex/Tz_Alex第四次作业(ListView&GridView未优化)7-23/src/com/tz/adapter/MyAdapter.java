package com.tz.adapter;

import java.util.List;
import java.util.Map;

import com.tz.R;
import com.tz.R.id;
import com.tz.R.layout;
import com.tz.bean.MemberInfo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	
	private Context context;
	private List<MemberInfo> lv_info;
	private ImageView lv_iv;
	private TextView tv_name, tv_sex, tv_face, tv_hobby;
	private int[] imgIds;
	private LinearLayout linearLayout;
	
	public MyAdapter(Context context, List<MemberInfo> lv_info) {
		super();
		this.context = context;
		this.lv_info = lv_info;
	}

	@Override
	public int getCount() {
		return lv_info.size();
	}

	@Override
	public Object getItem(int position) {
		return lv_info.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.lv_item, null);
		lv_iv = (ImageView) view.findViewById(R.id.lv_iv);
		tv_name = (TextView) view.findViewById(R.id.lv_tv_name);
		tv_sex = (TextView) view.findViewById(R.id.lv_tv_sex);
		tv_face = (TextView) view.findViewById(R.id.lv_tv_face);
		tv_hobby = (TextView) view.findViewById(R.id.lv_tv_hobby);
		linearLayout = (LinearLayout) view.findViewById(R.id.lv_info);
		
		//给item中的文本框赋值
		lv_iv.setBackgroundResource(lv_info.get(position).getImgId());
		tv_name.setText(lv_info.get(position).getName());
		tv_sex.setText(lv_info.get(position).getSex());
		if(lv_info.get(position).getSex().equals("男")){
			linearLayout.setBackgroundColor(Color.BLUE);
		}else if(lv_info.get(position).getSex().equals("女")){
			linearLayout.setBackgroundColor(Color.RED);
		}
		tv_face.setText(lv_info.get(position).getFace());
		tv_hobby.setText(lv_info.get(position).getHobby());
		
		
		return view;
	}

}
