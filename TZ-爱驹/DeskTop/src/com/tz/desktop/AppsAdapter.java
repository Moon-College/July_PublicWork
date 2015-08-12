package com.tz.desktop;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppsAdapter extends BaseAdapter {

	private List<ResolveInfo> infos;
	private Context context;
	private PackageManager pm;

	public AppsAdapter(PackageManager pm, List<ResolveInfo> infos,Context context){
		this.infos = infos;
		this.context = context;
		this.pm = pm;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ResolveInfo info = infos.get(position);
		ViewHolder holder;
		if(convertView==null){
			View v = View.inflate(context, R.layout.listitem, null);
			convertView = v;
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.iv = (ImageView) v.findViewById(R.id.iv);
			holder.tv = (TextView) v.findViewById(R.id.tv);
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.iv.setBackground(info.loadIcon(pm));
		holder.tv.setText(info.loadLabel(pm));
		
		
		return convertView;
	}

	class ViewHolder{
		ImageView iv;
		TextView tv;
	}
}
