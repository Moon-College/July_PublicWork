package com.example.fileExplorer.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fileExplorer.R;
import com.example.fileExplorer.bean.MyFile;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function SD卡文件浏览適配器
 */
public class MyFileAdapter extends BaseAdapter {

	private List<MyFile> mData;
	private LayoutInflater mInflater;
	private ImageAsyncTask mTask;

	public MyFileAdapter(Context mContext, List<MyFile> mData) {

		// TODO Auto-generated constructor stub
		this.mData = mData;
		mInflater = LayoutInflater.from(mContext);
		// (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//和上面那个一会事

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
		MyFile myFile = mData.get(position);
		ViewHolder holder = null;
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.file_lv_item, null);
			holder.fileIcon = (ImageView) convertView
					.findViewById(R.id.item_fileIcon);
			holder.fileName = (TextView) convertView
					.findViewById(R.id.item_FileName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		if (null == myFile.getFileIcon()) {
			String path = myFile.getFilePath();
			mTask = new ImageAsyncTask();
			mTask.execute(path, position + "");
		} else {
			holder.fileIcon.setImageBitmap(myFile.getFileIcon());
		}
		holder.fileName.setText(myFile.getFileName());
		return convertView;

	}

	class ViewHolder {
		public ImageView fileIcon;
		public TextView fileName;
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
				Bitmap bitmap = BitmapFactory.decodeFile(path);
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
}
