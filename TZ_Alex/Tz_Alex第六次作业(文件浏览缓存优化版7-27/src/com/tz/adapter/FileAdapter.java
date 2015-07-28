package com.tz.adapter;

import java.util.List;

import com.example.tz_class7_homework.R;
import com.tz.bean.MyFile;
import com.tz.util.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter {

	private List<MyFile> list;
	private ImageLoader mImageLoader;
	private Context context;

	public FileAdapter(List<MyFile> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.mImageLoader = ImageLoader.getInstance();
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
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}else{
			holder  =(ViewHolder) convertView.getTag();
		}

		MyFile myFile = list.get(position);
		holder.tv.setText(myFile.getName());
		if (myFile.getBitmap() == null) {
			LoaderImageTask task = new LoaderImageTask(holder.iv);
			task.execute(myFile.getPath(), position + "");
		} else {
			holder.iv.setImageBitmap(myFile.getBitmap());
		}
		return convertView;
	}

	/**
	 * viewHolder
	 */
	class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	/**
	 * 异步任务类
	 */

	class LoaderImageTask extends AsyncTask<String, Void, Void> {
		Bitmap bm;
		ImageView iv;

		public LoaderImageTask(ImageView iv) {
			this.iv = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			String path = params[0];
			bm = ImageLoader.getBitmapFromMemory(path);
			if (bm == null) {
				WindowManager manager = (WindowManager) context
						.getSystemService(Context.WINDOW_SERVICE);
				Display display = manager.getDefaultDisplay();
				int width = display.getWidth();
				int targetWidth = Math.round((float) width / 6);
				bm = ImageLoader.getReduceBitmap(path, targetWidth);
				ImageLoader.addBitmapToMemeory(path, bm);
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			iv.setImageBitmap(bm);
		}
	}
}
