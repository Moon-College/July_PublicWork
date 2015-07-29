package com.tz.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tzclass8_homework.R;
import com.tz.bean.MyFile;
import com.tz.util.ImageLoader;

public class FileAdapter extends BaseAdapter {

	Context context;
	List<MyFile> list;
	ImageLoader imageLoader;

	public FileAdapter(Context context, List<MyFile> list) {
		super();
		this.context = context;
		this.list = list;
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
		ViewHoler holer = null;
		if (convertView == null) {
			holer = new ViewHoler();
			convertView = View.inflate(context, R.layout.list_item, null);
			holer.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
			holer.tv = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		MyFile myFile = list.get(position);
		holer.tv.setText(myFile.getName());
		if (myFile.getBitmap() == null) {
			ImageTask task = new ImageTask(holer.iv);
			task.execute(myFile.getPath());
		} else {
			holer.iv.setImageBitmap(myFile.getBitmap());
		}
		return convertView;
	}

	class ViewHoler {
		ImageView iv;
		TextView tv;
	}

	class ImageTask extends AsyncTask<String, Void, Void> {
		ImageView iv;
		Bitmap bm;

		public ImageTask(ImageView iv) {
			super();
			this.iv = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			String path = params[0];
			bm = ImageLoader.getBitmapFromMemory(path);
			if (bm == null) {
				WindowManager wm = (WindowManager) context
						.getSystemService(Context.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();
				int width = display.getWidth();
				int targetWidth = Math.round((float) width / 6);
				bm = ImageLoader.getReduceBitmap(path, targetWidth);
				ImageLoader.addBitmapToMemeroy(path, bm);
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
