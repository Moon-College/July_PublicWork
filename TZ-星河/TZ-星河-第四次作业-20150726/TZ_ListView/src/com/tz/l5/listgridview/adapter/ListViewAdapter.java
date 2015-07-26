package com.tz.l5.listgridview.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tz_listgridview.R;
import com.tz.l5.listgridview.entity.Person;

public class ListViewAdapter extends BaseAdapter {
	private List<Person> persons;
	private Context context;
	
	
	public ListViewAdapter(List<Person> persons, Context context) {
		super();
		this.persons = persons;
		this.context = context;
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
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
			view=inflater.inflate(R.layout.list_item, null);
			holder.ivIcon=(ImageView) view.findViewById(R.id.img_icon);
			holder.tvName=(TextView) view.findViewById(R.id.tv_name);
			holder.tvSex=(TextView) view.findViewById(R.id.tv_sex);
			holder.tvBeauty=(TextView) view.findViewById(R.id.tv_beauty);
			holder.tvHobby=(TextView) view.findViewById(R.id.tv_hobby);
			view.setTag(holder);
		}else{
			holder=(ViewHolder) view.getTag();
		}
		Person p = persons.get(position);
		holder.ivIcon.setImageResource(p.getIcon());
		holder.tvName.setText(p.getName());
		holder.tvSex.setText(p.getSex());
		holder.tvBeauty.setText(p.getBeauty()+"");
		holder.tvHobby.setText(p.getHobby());
		if(p.getSex().equals("ÄÐ")){
			view.setBackgroundColor(Color.BLUE);
		}else{
			view.setBackgroundColor(Color.RED);
		}
		return view;
	}
	
	class ViewHolder{
		public ImageView ivIcon;
		public TextView tvName;
		public TextView tvSex;
		public TextView tvBeauty;
		public TextView tvHobby;
	}

}
