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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tz.fileexplorer.ImageLoader;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.BaseFile;
import com.tz.fileexplorer.utils.BitmapCache;

public class FileAdapter extends BaseAdapter {

	private List<BaseFile> list;
	private LayoutInflater inflater;
	private ViewHolder holder;
	private Context context;

	public FileAdapter(Context context, List<BaseFile> list) {
		this.list = list;
		this.context = context;
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

		BaseFile mFile = list.get(position);
		// core code!!!
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// core code!!!
		holder.tvName.setText(mFile.getName());
		holder.tvPath.setText(mFile.getPathExceptName());
		if (!mFile.isEmpty()) {
			holder.tvEmptyDir.setVisibility(View.GONE);
			// holder.emptyDir.removeView(holder.tvEmptyDir);
		} else {
			holder.tvEmptyDir.setVisibility(View.VISIBLE);
		}
		if (mFile.getIcon() == null) {
			holder.iv.setImageBitmap(BitmapCache.getBitmap(
					context.getResources(), R.drawable.ic_launcher));
			LoadFileTask task = new LoadFileTask(holder.iv);
			task.execute(mFile.getPath(), String.valueOf(position));
		} else {
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
		TextView tvName, tvPath, tvEmptyDir;

		public ViewHolder(View v) {
			tvName = (TextView) v.findViewById(R.id.tv_name);
			tvEmptyDir = (TextView) v.findViewById(R.id.tv_isEmpty);
			tvPath = (TextView) v.findViewById(R.id.tv_path);
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
		protected Void doInBackground(final String... params) {
			String path = params[0];
			try {

				if (imageLoader.getBitmapFromCache(path) == null) {
					icon = ImageLoader.loadImage(path, 20);
//					String uri = "file://"+params[0];
//					icon = com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImageSync(uri, new ImageSize(20, 20));
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
		
		private void loadImage(ImageView iv,String path){
			DisplayImageOptions options = new DisplayImageOptions.Builder()  
			                .cacheInMemory(true)  
			                .cacheOnDisk(true)  
			                .bitmapConfig(Bitmap.Config.RGB_565)  
			                .build();  
			          
//			        String path = "/mnt/sdcard/image.png";  
			        String imageUrl = Scheme.FILE.wrap(path);  
			          
			//      String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_74jpg";  
			          
			        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(imageUrl, iv, options);  

		}
	}
}
