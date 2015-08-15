package com.fantasyado.mylauncher.adapter;

import java.util.List;

import com.fantasyado.mylauncher.R;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * created at:2015年8月11日 上午9:20:15 project name:MyLauncher
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:LauncherAdapter.java description:
 */

public class LauncherAdapter extends BaseAdapter {

	private List<ResolveInfo> list;
	private Context context;

	public LauncherAdapter(List<ResolveInfo> list, Context context) {

		this.context = context;
		this.list = list;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Drawable icon=null;
		CharSequence label=null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_adapter, null);
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tv= (TextView) convertView.findViewById(R.id.tv_label);
		   convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		ResolveInfo info = list.get(position);
		PackageManager pm = context.getPackageManager();
	    icon = info.loadIcon(pm);
		label = info.loadLabel(pm);
		holder.iv.setImageDrawable(icon);
		holder.tv.setText(label);
		return convertView;
	}

	class ViewHolder {

		ImageView iv;
		TextView tv;
	}

	public void remove(ResolveInfo dragItem) {
		// TODO Auto-generated method stub
		if (list.contains(dragItem)) {
			list.remove(dragItem);
			
		}
	}

	public void insert(ResolveInfo dragItem, int dragPosition) {
		// TODO Auto-generated method stub
		list.add(dragPosition, dragItem);
	}

}
