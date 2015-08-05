package com.ws.file.listview;

import java.util.ArrayList;
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

public class Adapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	private List<MyFile> data = new ArrayList<MyFile>();

	public Adapter(Context context, List<MyFile> data) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
		imageLoader = ImageLoader.getIntance();

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holder;
		MyFile f = data.get(position);
		if (convertView == null) {
			holder = new HolderView();
			convertView = inflater.inflate(R.layout.item_listview, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.item_iv);
			holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
			convertView.setTag(holder);
		} else {
			holder = (HolderView) convertView.getTag();
		}
		holder.tv.setText(f.getName());
		if (f.getBitmap() == null) {
			LoadFileAsynTask task = new LoadFileAsynTask(holder.iv);

			task.execute(f.getPath(), position + "");

		} else {
			
			holder.iv.setImageBitmap(f.getBitmap());

		}

		return convertView;
	}

	class LoadFileAsynTask extends AsyncTask<String, Void, Void> {
		private ImageView iv;
		private Bitmap bm;

		public LoadFileAsynTask(ImageView iv) {
			this.iv = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			String path = params[0];
			String position = params[1];

			try {
				bm = imageLoader.getBitmapFromMemoryCache(path);
				if (bm == null) {
					bm = ImageLoader.decodeBitmapFromResource(path, 100);
					imageLoader.addBitmapToMemoryCache(path, bm);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			notifyDataSetChanged();
			iv.setImageBitmap(bm);
		}
	}

}

class HolderView {
	TextView tv;
	ImageView iv;
}
