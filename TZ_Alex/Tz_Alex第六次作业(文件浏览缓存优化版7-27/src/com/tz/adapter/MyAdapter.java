package com.tz.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.R;
import com.tz.bean.MyFile;
import com.tz.util.ImageLoader;

public class MyAdapter extends BaseAdapter {

	List<MyFile> list;
	Context context;
	ImageLoader imageLoader;

	public MyAdapter(List<MyFile> list, Context o) {
		super();
		this.list = list;
		this.context = o;
		this.imageLoader = ImageLoader.getInstance();
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
		ViewHolder viewHolder;
		if (convertView == null) {
			// 重用convertView避免内存浪费
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.lv_item, null);

			viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
			viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_info);
			convertView.setTag(viewHolder);
			Log.i("===========", position + "");
		} else {
			// 如果不是第一次加载，则通过viewHolder直接给控件赋值
			viewHolder = (ViewHolder) convertView.getTag();
		}

		MyFile myFile = list.get(position);
		viewHolder.tv.setText(myFile.getInfo());
		Log.i("+++++++++++++++++", myFile.getPath());
		if (myFile.getIcon() == null) {
			// 异步任务加载图片
			LoadAsync task = new LoadAsync(viewHolder.iv);
			// 回调方法
			task.execute(myFile.getPath(), position + "");
		} else {
			viewHolder.iv.setImageBitmap(myFile.getIcon());
		}

		return convertView;
	}

	/**
	 * 
	 * @author Alex ViewHolder内部类 优化ListView
	 */
	class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	/**
	 * 
	 * @author Alex 异步任务类 加载图片
	 */
	class LoadAsync extends AsyncTask<String, Void, Void> {
		private ImageView iv;
		private Bitmap bm;

		public LoadAsync(ImageView iv) {
			this.iv = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			String path = params[0];
			String position = params[1];
			try {
				// Bitmap bitmap = BitmapFactory.decodeFile(path);
				// list.get(Integer.parseInt(position)).setIcon(bitmap);
				bm = imageLoader.getBitmFromLruCache(path);
				if (bm == null) {
					bm = ImageLoader.getReduceBitmap(path, 100);
					imageLoader.addBitmapToLruCache(path, bm);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// notifyDataSetChanged();
			iv.setImageBitmap(bm);
		}
	}
}
