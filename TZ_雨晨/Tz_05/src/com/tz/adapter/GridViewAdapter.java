package com.tz.adapter;

import java.util.List;

import com.tz.R;
import com.tz.bean.DishMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private List<DishMenu> mDatas;

	public GridViewAdapter(Context mContext, List<DishMenu> mDatas) {
		super();
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		DishMenu bean = mDatas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_gridview, null);
			holder.img = (ImageView) convertView.findViewById(R.id.iv_img);
			holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.count = (TextView) convertView.findViewById(R.id.tv_num);
			holder.introduction = (TextView) convertView
					.findViewById(R.id.tv_introduction);
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.img.setImageResource(bean.img);
		holder.icon.setImageResource(bean.icon);
		holder.count.setText(String.format(
				mContext.getResources().getString(R.string.dish_num),
				bean.count));
		holder.introduction.setText(bean.introduction);
		holder.name.setText(bean.cooker);

		return convertView;
	}

	class ViewHolder {
		private ImageView img;
		private ImageView icon;
		private TextView name;
		private TextView introduction;
		private TextView count;
	}

}
