package com.tz.orm.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tz.orm.R;

public class BeanAdapter<T> extends BaseAdapter {

	private List<T> data;
	private Context context;
	private LayoutInflater inflater;
	
	
	public BeanAdapter(List<T> data, Context context) {
		super();
		this.data = data;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T t=data.get(position);
		BeanHolder holder=null;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.bean_item, null);
			holder=new BeanHolder();
			convertView.setTag(holder);
			holder.tv=(TextView) convertView.findViewById(R.id.tv_beanstring);
		} else {
			holder=(BeanHolder) convertView.getTag();
		}
		holder.tv.setText(t.toString());
		return convertView;
	}
	
	class BeanHolder{
		public TextView tv;
	}

}
