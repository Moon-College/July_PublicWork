package com.tz.adapter;

import java.util.List;

import com.tz.R;
import com.tz.bean.Person;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<Person> mDatas;

	public ListViewAdapter(Context mContext, List<Person> mDatas) {
		super();
		this.mInflater = LayoutInflater.from(mContext);
		this.mContext = mContext;
		this.mDatas = mDatas;
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
		Person person = mDatas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_listview, null);
			holder.img_icon = (ImageView) convertView.findViewById(R.id.it_iv_icon);
			holder.tv_nickname = (TextView) convertView.findViewById(R.id.it_tv_nickname);
			holder.tv_gender = (TextView) convertView.findViewById(R.id.it_tv_gender);
			holder.tv_yan = (TextView) convertView.findViewById(R.id.it_tv_yan);
			holder.tv_interest = (TextView) convertView.findViewById(R.id.it_tv_interest);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.img_icon.setImageResource(person.iconId);
		holder.tv_nickname.setText(person.nickName);
		if(person.gender==0){
			holder.tv_gender.setText("ÄÐ");
			convertView.setBackgroundColor(Color.BLUE);
		}else{
			holder.tv_gender.setText("Å®");
			convertView.setBackgroundColor(Color.RED);
		}
		holder.tv_yan.setText(person.yan);
		holder.tv_interest.setText(person.interest);
		
		return convertView;
	}

	
	public void remove(int position) {
		mDatas.remove(position);
		notifyDataSetChanged();
		Toast.makeText(mContext,"É¾³ý³É¹¦£¡", 0).show();
    }
	class ViewHolder {
		private ImageView img_icon;
		private TextView tv_nickname;
		private TextView tv_gender;
		private TextView tv_yan;
		private TextView tv_interest;
	}
	
}
