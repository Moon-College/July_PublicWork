package com.tz.filebrowser.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.filebrowser.R;
import com.tz.filebrowser.bean.SDFile;
import com.tz.filebrowser.utils.ImageLoader;

public class FileAdapter extends BaseAdapter {
	private List<SDFile> mSDFileLists;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	
	private int width;
	
	public FileAdapter(Context context, List<SDFile> sdFileList) {
		this.mSDFileLists = sdFileList;
		this.mInflater = LayoutInflater.from(context);
		mImageLoader = ImageLoader.getNewInstance();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		width = outMetrics.widthPixels / 4;
		
	}
	
	@Override
	public int getCount() {
		return mSDFileLists.size();
	}

	@Override
	public Object getItem(int position) {
		return mSDFileLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.list_view_item, parent, false);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvCount = (TextView) convertView.findViewById(R.id.tv_count);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		SDFile sdFile = (SDFile) getItem(position);
		if(sdFile.getName().equals("·µ»Ø")){
			holder.tvCount.setVisibility(View.GONE);
		}else {
			holder.tvCount.setVisibility(View.VISIBLE);
			holder.tvCount.setText(sdFile.getFile().isFile() ? sdFile.getFileSize() : (sdFile.getCount()+"Ïî"));
		}
		
		if(sdFile.isPicture()) {
			holder.ivIcon.setImageResource(android.R.color.holo_blue_dark);
			new LoadImageAsyncTask(holder.ivIcon).execute(sdFile.getFile().getAbsolutePath(), String.valueOf(position));
		}else {
			holder.ivIcon.setImageBitmap(sdFile.getBitmap());
		}
		holder.tvName.setText(sdFile.getName());
		
		return convertView;
	}

	
	class ViewHolder {
		public ImageView ivIcon;
		public TextView tvName;
		public TextView tvCount;
	}
	
	
	class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
		private ImageView iv;
		public LoadImageAsyncTask(ImageView iv) {
			this.iv = iv;
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = mImageLoader.getBitmapFromMemoryCache(params[0]);
			if(bitmap == null) {
				bitmap = mImageLoader.getBitmapWithPath(params[0], width);
			}
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if(bitmap != null) {
				iv.setImageBitmap(bitmap);
			}
		}
	}
	
}
