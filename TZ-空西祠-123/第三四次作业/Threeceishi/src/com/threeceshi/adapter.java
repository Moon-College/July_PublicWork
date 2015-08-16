package com.threeceshi;

import java.util.ArrayList;
import java.util.List;

import com.entity.dao;
import com.threeceishi.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adapter extends BaseAdapter {
	private List<dao> mlist=new ArrayList<dao>();
	private Context context;
	public adapter(List<dao> list,Context context){
		this.mlist=list;
		this.context=context;
	}
	@Override
	public int getCount() {
		// listview中显示多少条数据
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		// 返回position对应的数据
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// 返回当前位置的ID
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		dao bean=mlist.get(position);
		// 将布局渲染成view视图
		LayoutInflater layoutinflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=layoutinflater.inflate(R.layout.activity_main, null);
		TextView sex=(TextView) v.findViewById(R.id.sex);
		sex.setText(bean.getSex());
		
		TextView name=(TextView) v.findViewById(R.id.name);
		name.setText(bean.getName());
		ImageView image=(ImageView) v.findViewById(R.id.imageview);
		image.setImageResource(bean.getIcon());
		TextView hoppy=(TextView) v.findViewById(R.id.hoppy);
		hoppy.setText(bean.getHoppy());
		TextView numble=(TextView) v.findViewById(R.id.number);
		numble.setText(bean.getNumble());
		if(position%2==0){
			v.setBackgroundColor(Color.BLUE);
		}else{
			v.setBackgroundColor(Color.argb(100, 0, 255, 20));//定义颜色
		}
		return v;
		
		
	}

}
