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

import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.util.BitmapCache;
import com.tz.fileexplorer.util.BitmapUtil;

public class FileAdapter extends BaseAdapter {
	public static int MAX_BITMAP_SIZE = 0;
	BitmapCache bitmapCache;
	private List<MyFile> list = new ArrayList<MyFile>();
	private Context context;

	public FileAdapter(List<MyFile> list, Context context) {
		this.context = context;
		this.list = list;
		bitmapCache = BitmapCache.getInstance();
		int maxCacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);
		bitmapCache.init(maxCacheSize);
		MAX_BITMAP_SIZE = maxCacheSize / 10;
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
				Bitmap bmp = bitmapCache.getBitmap(path);
				if (bmp == null || bmp.isRecycled()) {
					holder.iv.setImageResource(R.drawable.loading);
					new LoadFileAsyncTask().execute(path);
				} else {
					holder.iv.setImageBitmap(bmp);
				}
			} else {
				holder.iv.setImageResource(R.drawable.file);
			}
		}

		return convertView;
	}

	/*
	 * AsyncTask< Params,参数 Progress,进度值 Result>加载的结果
	 */
	class LoadFileAsyncTask extends AsyncTask<String, Void, Bitmap> {
		private String path;

		@Override
		protected Bitmap doInBackground(String... params) {
			// 后台(线程)里面处理得事情
			// 加载图片
			Bitmap bmp = null;
			try {
				bmp = BitmapUtil.shrinkBitmap(path = params[0], MAX_BITMAP_SIZE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bmp;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null && !result.isRecycled()) {
				bitmapCache.addBitmap(path, result);
			}
			notifyDataSetChanged();
		}

	}

	class ViewHolder {
		ImageView iv;
		TextView tv;
	}
}
