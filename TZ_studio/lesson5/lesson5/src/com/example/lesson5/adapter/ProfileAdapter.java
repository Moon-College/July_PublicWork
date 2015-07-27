package com.example.lesson5.adapter;

import java.util.List;
import java.util.Map;

import com.example.lesson5.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> list;

	public ProfileAdapter(Context context, List<Map<String, Object>> list) {
		super();
		this.context = context;
		this.list = list;
	}
	
	public void notifyListView(int position){
		list.remove(position);
		notifyDataSetChanged();
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
			convertView=View.inflate(context, R.layout.profile_item, null);
			holder.iv_avatar=(ImageView) convertView.findViewById(R.id.iv_avatar);
			holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_hobby=(TextView) convertView.findViewById(R.id.tv_hobby);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		Map<String,Object> map=(Map<String, Object>) getItem(position);
		holder.iv_avatar.setImageResource(Integer.parseInt(map.get("image").toString()));
		holder.tv_name.setText(map.get("name").toString());
		holder.tv_hobby.setText(map.get("hobby").toString());
		return convertView;
	}
	
	final class ViewHolder{
		ImageView iv_avatar;
		TextView tv_name;
		TextView  tv_hobby;
	}

}
