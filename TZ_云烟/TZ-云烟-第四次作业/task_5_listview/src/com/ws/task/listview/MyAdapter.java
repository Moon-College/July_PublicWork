package com.ws.task.listview;

import java.util.List;

import android.R.color;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	Context context;
	List<Bean> data;
	LayoutInflater inflater;
	//MyAdapte�Ĺ��췽��
	public MyAdapter(Context context, List<Bean> data) {
		this.data = data;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return data.size();//��Ŀ���Ĵ�С
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	//��дgetView����
	public View getView(int position, View convertView, ViewGroup parent) {
		Bean hero = data.get(position);
		HolderView holder;
		if (convertView == null) {
			holder = new HolderView();
//			if(hero.getSex().equals("��")){
				convertView = inflater.inflate(R.layout.listview_item, null);
//			}else{
//				convertView = inflater.inflate(R.layout.listview_item1,null);
//			}
			holder.img_head = (ImageView) convertView
					.findViewById(R.id.item_haed);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.item_name);
			holder.tv_hobby = (TextView) convertView
					.findViewById(R.id.item_hobby);
			holder.tv_number = (TextView) convertView
					.findViewById(R.id.item_number);
			holder.tv_sex = (TextView) convertView.findViewById(R.id.item_sex);
			convertView.setTag(holder);
		} else {
			holder = (HolderView) convertView.getTag();
		}
		holder.tv_name.setText(hero.getName());
		holder.tv_sex.setText(hero.getSex());
		//�ж�ΪŮ������ɫΪ��ɫ
		if(hero.getSex().equals("Ů")){
			holder.tv_sex.setTextColor(Color.RED);
		}
//		holder.tv_number.setText(hero.getNumber());
		holder.tv_hobby.setText(hero.getHobby());
		holder.img_head.setImageResource(hero.getHead());

		return convertView;
	}

}
//�鲼��
class HolderView {
	TextView tv_name;
	TextView tv_sex;
	TextView tv_hobby;
	TextView tv_number;
	ImageView img_head;

}
