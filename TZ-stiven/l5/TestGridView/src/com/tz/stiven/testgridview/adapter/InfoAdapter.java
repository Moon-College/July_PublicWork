package com.tz.stiven.testgridview.adapter;

import java.util.List;

import com.tz.stiven.testgridview.R;
import com.tz.stiven.testgridview.dialog.Person;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoAdapter extends BaseAdapter {
	
	private InfoListItemView listItemView;
	private List<Person> mListData = null;
	private Context mContext;
	private LayoutInflater mLayoutInfalter;
	
	public final class InfoListItemView{
		
		public ImageView IV_icon;
		public TextView TV_name;
		public TextView TV_describe;
		public TextView TV_numString;
		public ImageView IV_foodImage;
		
	}
	
	public InfoAdapter(Context context, List<Person> listData){
		this.mContext = context;
		this.mListData = listData;
		mLayoutInfalter = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		
		return mListData.size();
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Person person = mListData.get(position);
		
		if(convertView == null){
			listItemView = new InfoListItemView();
			
			convertView = mLayoutInfalter.inflate(R.layout.gridview_item, null);
			
			listItemView.IV_foodImage = (ImageView)convertView.findViewById(R.id.iv_foodimage);
			listItemView.IV_icon = (ImageView)convertView.findViewById(R.id.iv_icon);
			listItemView.TV_numString = (TextView)convertView.findViewById(R.id.tv_numstring);
			listItemView.TV_describe = (TextView)convertView.findViewById(R.id.tv_describe);
			listItemView.TV_name = (TextView)convertView.findViewById(R.id.tv_name);
			
			convertView.setTag(listItemView);
		}else{
			listItemView = (InfoListItemView)convertView.getTag();
		}
		
		listItemView.IV_foodImage.setImageResource(person.foodImage);
		listItemView.IV_icon.setImageResource(person.icon);
		listItemView.TV_numString.setText(person.numString + "µÀ²Ë");
		listItemView.TV_describe.setText(person.describe);
		listItemView.TV_name.setText(person.name);
		
		return convertView;
	}

}
