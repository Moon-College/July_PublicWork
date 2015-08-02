package com.example.fileExplorer.adapter;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fileExplorer.R;
import com.example.fileExplorer.bean.MyFile;
import com.example.fileExplorer.utils.ImageLoaderUtils;
import com.example.fileExplorer.utils.ScreenUtils;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function SD卡文件浏览適配器
 * 
 * 
 * @author kevin.li
 * @version 1.0.1
 * @create 20150728
 * @function 增加了图片的压缩 和 Lru缓存处理
 */
public class MyFileAdapter extends BaseAdapter {

	private List<MyFile> mData;
	private LayoutInflater mInflater;
	private ImageAsyncTask mTask;
	private Activity act;
	public static OnItemButtonClickListener mCallback;

	public MyFileAdapter(Activity act, List<MyFile> mData) {

		// TODO Auto-generated constructor stub
		this.act = act;
		this.mData = mData;
		mInflater = LayoutInflater.from(act);
		// (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//和上面那个一会事

	}

	/**
	 * 设置监听
	 * 
	 * @param mCallback
	 */
	@SuppressWarnings("static-access")
	public void setOnItemButtonClickListener(OnItemButtonClickListener mCallback) {
		this.mCallback = mCallback;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int index = position;
		MyFile myFile = mData.get(position);
		ViewHolder holder = null;
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.file_lv_item, null);
			holder.fileIcon = (ImageView) convertView
					.findViewById(R.id.item_fileIcon);
			holder.fileName = (TextView) convertView
					.findViewById(R.id.item_fileName);
			holder.fileDetail = (Button) convertView
					.findViewById(R.id.item_fileDetail);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		// 其他公共信息
		holder.fileName.setText(myFile.getFileName());

		// 图片bitmap信息
		if (null == myFile.getFileIcon()) {
			String path = myFile.getFilePath();
			mTask = new ImageAsyncTask();
			mTask.execute(path, position + "");
		} else {
			holder.fileIcon.setImageBitmap(myFile.getFileIcon());
		}

		/**
		 * 添加监听
		 */
		holder.fileDetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mCallback != null) {
					mCallback.onClick(v, index, R.id.item_fileDetail);
				}
			}
		});

		return convertView;

	}

	class ViewHolder {
		public ImageView fileIcon;
		public TextView fileName;
		public Button fileDetail;
	}

	/**
	 * @author kevin.li
	 * @create 20150727
	 * @function 异步加载图片
	 */
	class ImageAsyncTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path = params[0];
			int position = Integer.parseInt(params[1]);

			try {
				// Bitmap bitmap = BitmapFactory.decodeFile(path);
				// 得到屏幕宽度的1/4
				int screenWidh = ScreenUtils.getSrennSize(act).get(
						ScreenUtils.WIDTH);
				Bitmap bitmap = ImageLoaderUtils.decodeBitmapFromResoure(path,
						screenWidh / 6);
				mData.get(position).setFileIcon(bitmap);
			} catch (Exception e) {
				// TODO: handle exception
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			notifyDataSetChanged();
		}

	}

	/**
	 * 自定义接口
	 * 
	 */
	public interface OnItemButtonClickListener {
		public void onClick(View v, int position, long id);
	}
}
