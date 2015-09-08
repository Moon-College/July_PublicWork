package com.tz.ls9.launcher.adapter;

import java.util.List;

import com.example.tz_launcher.R;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppsAdapter extends BaseAdapter {
	private List<ResolveInfo> list;
	private Context context;

	public AppsAdapter(List<ResolveInfo> activities,Context context) {
		this.list = activities;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ResolveInfo resolveInfo = list.get(position);
		View v = View.inflate(context, R.layout.item, null);
		ImageView iv = (ImageView) v.findViewById(R.id.iv_icon);
		TextView tv = (TextView) v.findViewById(R.id.tv_name);
		PackageManager pm = context.getPackageManager();
		Drawable icon = resolveInfo.loadIcon(pm);
		iv.setImageDrawable(icon);
		tv.setText(resolveInfo.loadLabel(pm));
		return v;
	}

}
