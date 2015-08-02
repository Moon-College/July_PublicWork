package com.tz.file.adapter;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.file.R;
import com.tz.file.bean.FileBean;
import com.tz.file.utils.ImageLoader;

public class FileAdapter extends BaseAdapter {
	
	private List<FileBean> mDatas;
	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	private int w;
	private int h;
	
	public FileAdapter(List<FileBean> mDatas, Context mContext) {
		super();
		this.mDatas = mDatas;
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
		imageLoader = ImageLoader.getInstance();
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		w = dm.widthPixels;// 获取屏幕分辨率宽度
		h = dm.heightPixels;
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		FileBean bean = mDatas.get(position);
		File file = new File(bean.getPath());
		ViewHolder holder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.item_file, parent, false);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_logo);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvSize = (TextView) convertView.findViewById(R.id.tv_size);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(bean.getName().equals("·µ»Ø")){
			holder.tvSize.setVisibility(View.GONE);
		}else {
			holder.tvSize.setVisibility(View.VISIBLE);
			holder.tvSize.setText("");
		}
		
		if(bean.isImage()) {
			holder.ivIcon.setImageResource(android.R.color.holo_blue_dark);
			new LoadImageAsyncTask(holder.ivIcon).execute(bean.getPath());
		}else {
			holder.ivIcon.setImageResource(bean.getImageId());
		}
		holder.tvName.setText(bean.getName());
		
		return convertView;
	}
	
	
	class ViewHolder {
		public ImageView ivIcon;
		public TextView tvName;
		public TextView tvSize;
	}
	
	class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap>{
		ImageView iv ;
		
		public LoadImageAsyncTask(ImageView iv) {
			super();
			this.iv = iv;
		}

		@Override
		protected Bitmap doInBackground(String... params)
		{
			
			Bitmap bitmap = imageLoader.getBitMapFromMemoryCache(params[0]);
			if(bitmap == null) {
				bitmap = imageLoader.getScaleBitmap(params[0], w/5, h/8);
			}
			return bitmap;
		}
	}
}
