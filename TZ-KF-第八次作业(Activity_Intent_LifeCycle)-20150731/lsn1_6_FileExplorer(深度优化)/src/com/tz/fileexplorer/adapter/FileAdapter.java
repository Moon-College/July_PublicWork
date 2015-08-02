package com.tz.fileexplorer.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.fileexplorer.MainActivity;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.util.BitmapCache;
import com.tz.fileexplorer.util.BitmapUtil;
import com.tz.fileexplorer.util.LoadFileAsyncTask;

public class FileAdapter extends BaseAdapter {

	private List<MyFile> list = new ArrayList<MyFile>();
	private Context context;

	public FileAdapter(List<MyFile> list, Context context) {
		this.context = context;
		this.list = list;
	}

	public void setList(List<MyFile> list) {
		this.list = list;
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

		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.listview_item, null);
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.icon);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MyFile myFile = (MyFile) getItem(position);
		// 文件名
		holder.tv.setText(myFile.getName());
		// 图标
		File f = myFile.getFile();
		if (f.isDirectory()) {
			holder.iv.setImageResource(R.drawable.dirs);
		} else {
			if (myFile.isIcon()) {
				String path = myFile.getPath();
				Bitmap bmp = BitmapCache.getInstance().getBitmap(path);
				if (bmp == null || bmp.isRecycled()) {
					holder.iv.setImageResource(R.drawable.loading);
//					new LoadFileAsyncTask() {
//						protected void onPostExecute(Bitmap result) {
//							super.onPostExecute(result);
//							notifyDataSetChanged();
//						}
//					}.execute(path);
				} else {
					holder.iv.setImageBitmap(bmp);
				}
			} else {
				holder.iv.setImageResource(R.drawable.file);
			}
		}

		return convertView;
	}

	class ViewHolder {
		ImageView iv;
		TextView tv;
	}
}
