package com.tz.adapter;

import java.util.List;

import com.tz.bean.MyFile;
import com.tz.tzclass9_fileexplorer.R;
import com.tz.utils.ReduceImageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.Settings.System;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter {
	private Context context;
	private List<MyFile> list;
	private ReduceImageUtil imageUtil;

	public FileAdapter(Context context, List<MyFile> list) {
		super();
		this.context = context;
		this.list = list;
		imageUtil = ReduceImageUtil.getInstance();
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
		ViewHorlder horlder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.list_item, null);
			horlder = new ViewHorlder();
			horlder.iv = (ImageView) convertView.findViewById(R.id.iv_show);
			horlder.tv = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(horlder);
		} else {
			horlder = (ViewHorlder) convertView.getTag();
		}

		MyFile myFile = list.get(position);
		horlder.tv.setText(myFile.getName());
		if (myFile.getBm() == null) {
			ImageTask task = new ImageTask(horlder.iv);
			task.execute(myFile.getPath());
		} else {
			horlder.iv.setImageBitmap(myFile.getBm());
		}
		return convertView;
	}

	class ViewHorlder {
		ImageView iv;
		TextView tv;
	}

	class ImageTask extends AsyncTask<String, Void, Void> {

		Bitmap bm;
		ImageView iv;

		public ImageTask(ImageView iv) {
			super();
			this.iv = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			bm = ReduceImageUtil.getBitmapFromMemory(params[0]);
			if (bm == null) {
				WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();
				int width = display.getWidth();
				int targetWidth = (int) ((float)width / 4);
				 bm = ReduceImageUtil
						.getResuceBitmap(params[0], targetWidth);
				 ReduceImageUtil.putBitmapToMemeory(params[0], bm);
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