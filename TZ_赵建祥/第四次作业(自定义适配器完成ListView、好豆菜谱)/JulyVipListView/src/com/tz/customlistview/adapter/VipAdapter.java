package com.tz.customlistview.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.customlistview.R;
import com.tz.customlistview.bean.JulyVip;

public class VipAdapter extends BaseAdapter {

	private Context context;
	private List<JulyVip> data;
	private LayoutInflater inflater;
	
	public VipAdapter(Context context, List<JulyVip> data) {
		super();
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(this.context);;
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
		JulyVip vip=data.get(position);
		JulyVipHolder holder=null;
		if(convertView==null){
			holder=new JulyVipHolder();
			convertView=inflater.inflate(R.layout.vip_item, null);
			convertView.setTag(holder);
			holder.faceImg=(ImageView) convertView.findViewById(R.id.iv_face);
			holder.netName=(TextView) convertView.findViewById(R.id.tv_netname);
			holder.sex=(TextView) convertView.findViewById(R.id.tv_sex);
			holder.faceScore=(TextView) convertView.findViewById(R.id.tv_facescore);
			holder.hobby=(TextView) convertView.findViewById(R.id.tv_hobby);
		} else {
			holder=(JulyVipHolder) convertView.getTag();
		}
		
		holder.faceImg.setImageResource(vip.getFaceImgId());
		holder.netName.setText(vip.getNetName());
		holder.sex.setText(vip.getSex());
		holder.faceScore.setText("ÑÕÖµ£º"+vip.getFaceScore());
		holder.hobby.setText("°®ºÃ£º"+vip.getHobby());
		if(vip.getSex().equals("ÄÐ")){
			convertView.setBackgroundColor(Color.BLUE);
		}else{
			convertView.setBackgroundColor(Color.RED);
		}
		
		return convertView;
	}

	private class JulyVipHolder{
		public ImageView faceImg;
		public TextView netName;
		public TextView sex;
		public TextView faceScore;
		public TextView hobby;
	}
}
