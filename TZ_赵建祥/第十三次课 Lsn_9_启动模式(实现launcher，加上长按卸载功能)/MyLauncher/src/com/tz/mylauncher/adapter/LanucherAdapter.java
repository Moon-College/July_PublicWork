package com.tz.mylauncher.adapter;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.mylauncher.R;

public class LanucherAdapter extends BaseAdapter {

	private List<ResolveInfo> data;
	private LayoutInflater inflater;
	private PackageManager pm;

	public LanucherAdapter(List<ResolveInfo> data, Context context,
			PackageManager pm) {
		super();
		this.data = data;
		inflater = LayoutInflater.from(context);
		this.pm = pm;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ResolveInfo resolveInfo = data.get(position);
		LauncherHolder holder=null;
		if (convertView == null) {
			holder = new LauncherHolder();
			convertView = inflater.inflate(R.layout.item, null);
			convertView.setTag(holder);
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tv_lable = (TextView) convertView
					.findViewById(R.id.tv_lable);
		} else {
			holder=(LauncherHolder) convertView.getTag();
		}
		holder.iv_icon.setBackgroundDrawable(resolveInfo.activityInfo.loadIcon(pm));
		holder.tv_lable.setText(resolveInfo.activityInfo.loadLabel(pm));
		return convertView;
	}

	class LauncherHolder {
		public ImageView iv_icon;
		public TextView tv_lable;
	}

}
