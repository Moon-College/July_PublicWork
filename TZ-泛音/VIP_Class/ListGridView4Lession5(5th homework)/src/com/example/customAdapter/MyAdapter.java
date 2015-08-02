package com.example.customAdapter;

import java.util.List;
import java.util.Map;

import com.example.listgridview4lession5.R;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mData;
	Context context;
	public MyAdapter(Context context,List<Map<String, Object>> Data) {
		this.mInflater = LayoutInflater.from(context);
		this.mData=Data;
		this.context=context;
	}

	@Override
	public int getCount() {
		return mData.size();

	}
    
	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(final int position, View convertView,  ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			
			holder=new ViewHolder();  
			convertView = mInflater.inflate(R.layout.vlist2, null);
			holder.img = (ImageView)convertView.findViewById(R.id.img);
			holder.name = (TextView)convertView.findViewById(R.id.name);
			holder.sex = (TextView)convertView.findViewById(R.id.sex);
			holder.facelevel = (TextView)convertView.findViewById(R.id.facelevel);
			holder.hobbit = (TextView)convertView.findViewById(R.id.hobbit);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		
		if(position<6){
			holder.img.setBackgroundResource(R.drawable.man);
		}else{
			holder.img.setBackgroundResource(R.drawable.women);
		}
		
		holder.name.setText((String)mData.get(position).get("name"));
		holder.sex.setText((String)mData.get(position).get("sex"));
		holder.facelevel.setText((String)mData.get(position).get("facelevel"));
		holder.hobbit.setText((String)mData.get(position).get("hobbit"));
		return convertView;
	}
	



	
	
	public class ViewHolder {
		//头像+网名+性别+颜值+爱好
		public ImageView img;
		public TextView name;
		public TextView sex;
		public TextView facelevel;
		public TextView hobbit;
	}
}
