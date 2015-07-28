package com.mumu.filebrowser.adapter;

import java.util.List;

import com.mumu.filebrowser.R;
import com.mumu.filebrowser.bean.FileInfo;
import com.mumu.filebrowser.utils.ImageLoader;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.http.SslCertificate;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter {
	private final String SHOW_DIR_BITMAP = "SHOW_DIR_BITMAP";
	private final String SHOW_PDF_BITMAP = "SHOW_PDF_BITMAP";
	private final String SHOW_IMAGE_BITMAP = "SHOW_IMAGE_BITMAP";
	private final String SHOW_SOUND_BITMAP = "SHOW_SOUND_BITMAP";
	private final String SHOW_UNKNOW_BITMAP = "SHOW_UNKNOW_BITMAP";
	
	private final boolean DEBUG = true;
	
	private Context context;
	private LayoutInflater inflater;
	private List<FileInfo> list;
	private ImageLoader mImageLoader;
	private final int ivWidth = 50;
	private final int ivHeight = 50;

	public FileAdapter(Context context, List<FileInfo> list) {
		this.context = context;
		this.list = list;
		this.inflater = (LayoutInflater) LayoutInflater.from(context);
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public FileInfo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Bitmap bitmap;
		FileInfo fileInfo = list.get(position);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.file_list_item_layout, null);
			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.file_img);
			holder.tv = (TextView) convertView.findViewById(R.id.file_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		//image类型key值为“文件路径+文件名”，其他使用类型字符串作为key
		switch (fileInfo.getType()) {
		case FileInfo.FILE_TYPE_IMAGE:
			String key = fileInfo.getFilePath();
			bitmap = mImageLoader.getBitmapFromCache(key);
			if(DEBUG) Log.i("INFO", "getView:position = " + position + " ,key=" + key + " ,bitmap=" + bitmap);
			if(bitmap == null){
				AsyncLoadBitmap asyncTask = new AsyncLoadBitmap();
				asyncTask.execute(fileInfo.getFilePath(), String.valueOf(position));
			} else {
				holder.iv.setImageBitmap(bitmap);
			}
			break;
		case FileInfo.FILE_TYPE_DIR:
			if(DEBUG) Log.i("INFO", "getView(dir):position = " + position);
			holder.iv.setImageBitmap(getDrawableBitmap(SHOW_DIR_BITMAP, R.drawable.file_type_folder));
			break;
		case FileInfo.FILE_TYPE_PDF:
			holder.iv.setImageBitmap(getDrawableBitmap(SHOW_PDF_BITMAP, R.drawable.file_type_pdf));
			break;
		case FileInfo.FILE_TYPE_SOUND:
			holder.iv.setImageBitmap(getDrawableBitmap(SHOW_SOUND_BITMAP, R.drawable.file_type_audio));
			break;
		default:
			holder.iv.setImageBitmap(getDrawableBitmap(SHOW_UNKNOW_BITMAP, R.drawable.file_type_default));
			break;
		}
		holder.tv.setText(fileInfo.getFileName());

		return convertView;
	}
	
	private Bitmap getDrawableBitmap(String key, int resid){
		Bitmap bitmap = mImageLoader.getBitmapFromCache(key);
		if(bitmap == null){
			bitmap = BitmapFactory.decodeResource(context.getResources(), resid);
			mImageLoader.putBitmapToCache(key, bitmap);
		} 
		return bitmap;
	}
	
	/**
	 *  用于保存convertView中相关的数据信息
	 */
	private class ViewHolder{
		public ImageView iv;
		public TextView tv;
	}
	
	/**
	 * 异步加载图片，防止主线程ANR
	 */
	private class AsyncLoadBitmap extends AsyncTask<String, Void, Bitmap>{

		private Bitmap bitmap;

		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeFile(path, options);
			int width = options.outWidth;
			int height = options.outHeight;
			if (width > ivWidth || height > ivHeight) {
				options.inSampleSize = Math.max(width / ivWidth, height	/ ivHeight);
			}
			options.inJustDecodeBounds = false;
			if(DEBUG) Log.i("INFO", "doInBackground:postion = " + position + " ,options.inSampleSize=" + options.inSampleSize);
			bitmap = BitmapFactory.decodeFile(path, options);
			mImageLoader.putBitmapToCache(path, bitmap);
			list.get(position).setBitmap(bitmap);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			FileAdapter.this.notifyDataSetChanged();
		}
		
	}

}
