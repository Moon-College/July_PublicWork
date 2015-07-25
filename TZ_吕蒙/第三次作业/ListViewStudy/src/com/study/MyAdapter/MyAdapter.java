package com.study.MyAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.listviewstudy.R;
import com.study.listviewstudy.mesageData;

public class MyAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<mesageData> Datas;
	private LayoutInflater inflater;

	
	public MyAdapter(Context context,ArrayList<mesageData> Datas){
		this.context=context;
		this.Datas=Datas;	
		//设置inflater的两种方式
		//inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View root = inflater.inflate(R.layout.messagelist, null);
		
		TextView name=(TextView) root.findViewById(R.id.name);
		TextView time=(TextView) root.findViewById(R.id.time);
		TextView content=(TextView) root.findViewById(R.id.content);
		
		name.setText(Datas.get(position).getName());
		time.setText(Datas.get(position).getTime());
		content.setText(Datas.get(position).getContent());
		
		return root;
	}

}
