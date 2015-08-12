package com.tz.lsn13_applaunchermode;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyLauncherAdapter extends BaseAdapter {

	
	private Context mContext;
	private List<ResolveInfo> list;
	private LayoutInflater mLayoutInflater;
	
	public MyLauncherAdapter(Context mContext, List<ResolveInfo> list) {
		super();
		this.mContext = mContext;
		this.list = list;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	private class ViewHolder {
		ImageView iv_icon;
		TextView tv_name;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.activity_launcher_item, null);
			holder = new ViewHolder();
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		ResolveInfo info = list.get(position);
		PackageManager pm = mContext.getPackageManager();
		Drawable loadIcon = info.loadIcon(pm);
		CharSequence loadLabel = info.loadLabel(pm);
		holder.iv_icon.setImageDrawable(loadIcon);
		holder.tv_name.setText(loadLabel);
		
		return convertView;
	}

}
