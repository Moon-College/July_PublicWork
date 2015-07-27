package com.tz.l5.gridview.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.l5.gridview.R;
import com.tz.l5.gridview.entity.Item;

public class GridViewAdapter extends BaseAdapter {
	private List<Item> items;
	private Context context;
	public GridViewAdapter(List<Item> items,
			Context context) {
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder=null;
		if(view==null){
			holder=new ViewHolder();
			LayoutInflater inflater=LayoutInflater.from(context);
			view=inflater.inflate(R.layout.gv_item, null);
			holder.ivIcon=(ImageView) view.findViewById(R.id.img_icon);
			holder.tvName=(TextView) view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder) view.getTag();
		}
		Item item=items.get(position);
		holder.ivIcon.setImageResource(item.getIconId());
		holder.tvName.setText(item.getName());
		return view;
	}

	class ViewHolder{
		public ImageView ivIcon;
		public TextView tvName;
	}
}
