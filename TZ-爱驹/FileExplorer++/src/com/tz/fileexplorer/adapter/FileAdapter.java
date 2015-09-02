package com.tz.fileexplorer.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.fileexplorer.ImageLoader;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.MyFile;

public class FileAdapter extends BaseAdapter {

	private List<MyFile> list;
	private LayoutInflater inflater;
	private ViewHolder holder;

	public FileAdapter(Context context, List<MyFile> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
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

		MyFile mFile = list.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(mFile.getName());
		if (mFile.getIcon() == null) {
			holder.iv.setImageResource(R.drawable.ic_launcher);
			LoadFileTask task = new LoadFileTask(holder.iv);
			task.execute(mFile.getPath(), String.valueOf(position));
		}else{
			holder.iv.setImageBitmap(mFile.getIcon());
		}
		return convertView;
	}

	/**
	 * 为防止在每次getView时都要调用一次findViewByID（）
	 * 所以把ImageView和TextView等控件捆绑在一个ViewHolder里面
	 */
	class ViewHolder {
		ImageView iv;
		TextView tv;

		public ViewHolder(View v) {
			tv = (TextView) v.findViewById(R.id.tv_name);
			iv = (ImageView) v.findViewById(R.id.iv_icon);
		}
	}

	/**
	 * 为防止做耗时操作卡顿UI线程，因此要开一个新的异步任务线程
	 */
	class LoadFileTask extends AsyncTask<String, Void, Void> {

		private ImageLoader imageLoader;
		private ImageView iv;
		private Bitmap icon;

		public LoadFileTask(ImageView iv) {
			this.iv = iv;
			imageLoader = ImageLoader.getInstance();
		}

		@Override
		protected Void doInBackground(String... params) {
			String path = params[0];
			try {

				if (imageLoader.getBitmapFromCache(path) == null) {
					icon = ImageLoader.loadImage(path, 20);
					imageLoader.addBitmapToCache(path, icon);
				} else {
					icon = imageLoader.getBitmapFromCache(path);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			iv.setImageBitmap(icon);
		}
	}
}
