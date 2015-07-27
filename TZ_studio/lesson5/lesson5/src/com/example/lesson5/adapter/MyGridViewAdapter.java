package com.example.lesson5.adapter;

import java.util.List;
import java.util.Map;

import com.example.lesson5.R;
import com.example.lesson5.adapter.ProfileAdapter.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridViewAdapter extends BaseAdapter {	
	
	private Context context;
	private List<Map<String, Object>> list;

	public MyGridViewAdapter(Context context,List<Map<String, Object>> list) {
		super();
		this.list = list;
		this.context = context;
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
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=View.inflate(context, R.layout.hao_dou_activity_item, null);
			holder.iv_bg=(ImageView) convertView.findViewById(R.id.iv_bg);
			holder.tv_count_txt=(TextView) convertView.findViewById(R.id.tv_count_txt);
			holder.iv_person_avatar=(ImageView) convertView.findViewById(R.id.iv_person_avatar);
			holder.title=(TextView) convertView.findViewById(R.id.title);
			holder.nick=(TextView)convertView.findViewById(R.id.nick);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		Map<String,Object> map=(Map<String, Object>) getItem(position);
		holder.iv_bg.setImageResource(Integer.parseInt(map.get("image").toString()));
		holder.tv_count_txt.setText(map.get("countTxt").toString());
//		holder.iv_bg.setImageResource(Integer.parseInt(map.get("avatar").toString()));
		holder.title.setText(map.get("title").toString());
		holder.nick.setText(map.get("nick").toString());
		return convertView;
	}
	
	final class ViewHolder{
		ImageView iv_bg;
		TextView tv_count_txt;
		ImageView iv_person_avatar;
		TextView  title;
		TextView nick;
	}
	

}
