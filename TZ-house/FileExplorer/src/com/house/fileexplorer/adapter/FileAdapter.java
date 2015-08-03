package com.house.fileexplorer.adapter;

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

import com.house.fileexplorer.R;
import com.house.fileexplorer.bean.MyFileResponse;
import com.house.fileexplorer.util.BitmapCompress;
import com.house.fileexplorer.util.ImageLoader;
import com.house.fileexplorer.util.PhoneUtils;

public class FileAdapter extends BaseAdapter {

	private List<MyFileResponse> list;
	private Context context;
	private ImageLoader imageLoader;

	public FileAdapter(List<MyFileResponse> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
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
					R.layout.file_item, null);
			convertView.setTag(holder);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		MyFileResponse response = list.get(position);
		holder.tv_name.setText(response.getName());
		if (response.getIcon() == null) {// 是图片
			// 异步任务加载图片
			new LoadBitmapAsyncTask(holder.icon).execute(response.getPath(),
					position + "");
		} else {
			holder.icon.setImageBitmap(response.getIcon());
		}
		return convertView;
	}

	class ViewHolder {
		ImageView icon;
		TextView tv_name;
	}

	/**
	 * AsyncTask< Params,参数 Progress,进度值 Result>加载的结果
	 */
	class LoadBitmapAsyncTask extends AsyncTask<String, Void, Bitmap> {

		private ImageView imageView;

		private LoadBitmapAsyncTask(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// 图片下载
			Bitmap bitmap = null;
			String path = params[0];
			// 图片不在缓存中
			if (imageLoader.getBitmapFromMemoryCache(path) == null) {
				bitmap = BitmapCompress.decodeBitmapFromResponse(path,
						PhoneUtils.screenWidthAndHeight(context)[0] / 4);
				imageLoader.addBitmapAddMemoryCache(path, bitmap);
			} else {
				bitmap = imageLoader.getBitmapFromMemoryCache(path);
			}

			return bitmap;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			imageView.setImageResource(R.drawable.dirs);
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			imageView.setImageBitmap(bitmap);
		}
	}

}
