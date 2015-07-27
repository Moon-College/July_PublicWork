package com.tz.lesson5.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lesson5.R;
import com.tz.lesson5.bean.Menu;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<Menu> mMenus;
	private LayoutInflater mInflater;
	
	public GridViewAdapter(Context context, List<Menu> menus) {
		this.mContext = context;
		this.mMenus = menus;
		this.mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMenus.size();
	}

	@Override
	public Menu getItem(int position) {
		// TODO Auto-generated method stub
		return mMenus.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.grid_view_item, parent, false);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
			holder.ivFace = (ImageView) convertView.findViewById(R.id.iv_face);
			holder.tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Menu menu = getItem(position);
		
		holder.ivIcon.setImageResource(menu.getIcon());
		holder.tvDescription.setText(menu.getDescription());
		holder.ivFace.setImageResource(menu.getAuthIcon());
		holder.tvAuthor.setText(menu.getAuthor());
		
		return convertView;
	}

	class ViewHolder {
		public ImageView ivIcon;
		public TextView tvDescription;
		public ImageView ivFace;
		public TextView tvAuthor;
	}
	
}
