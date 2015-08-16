package com.tz.testlistview.adapter;

import java.util.List;

import com.tz.testlistview.R;
import com.tz.testlistview.bean.Person;

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
		public TextView TV_hobby;
		public TextView TV_sex;
		public TextView TV_facenum;
		
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
			
			convertView = mLayoutInfalter.inflate(R.layout.list_item_info, null);
			
			listItemView.IV_icon = (ImageView)convertView.findViewById(R.id.iv_icon);
			listItemView.TV_name = (TextView)convertView.findViewById(R.id.tv_name);
			listItemView.TV_hobby = (TextView)convertView.findViewById(R.id.tv_hobby);
			listItemView.TV_sex = (TextView)convertView.findViewById(R.id.tv_sex);
			listItemView.TV_facenum = (TextView)convertView.findViewById(R.id.tv_facenum);
			
			if(person.sex.equals("男")){
				convertView.setBackgroundResource(R.drawable.blue);
				listItemView.TV_sex.setText("男");
			}else if(person.sex.equals("女")){
				convertView.setBackgroundResource(R.drawable.red);
				listItemView.TV_sex.setText("女");
			}else{
				convertView.setBackgroundColor(0x00ff00);
				listItemView.TV_sex.setText("阴阳人");
			}
			
			convertView.setTag(listItemView);
			
		}else{
			
			listItemView = (InfoListItemView)convertView.getTag();
			
			if(person.sex.equals("男")){
				convertView.setBackgroundResource(R.drawable.blue);
				listItemView.TV_sex.setText("男");
			}else if(person.sex.equals("女")){
				convertView.setBackgroundResource(R.drawable.red);
				listItemView.TV_sex.setText("女");
			}else{
				convertView.setBackgroundColor(0x00ff00);
				listItemView.TV_sex.setText("阴阳人");
			}
			
			
		}
		
		//设置输入信息
		listItemView.IV_icon.setImageResource(person.icon);
		listItemView.TV_name.setText(person.name);
		listItemView.TV_hobby.setText(person.hobby);
		listItemView.TV_facenum.setText(person.num + "");
		
		return convertView;
	}

}
