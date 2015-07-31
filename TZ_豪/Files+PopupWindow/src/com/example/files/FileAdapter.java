package com.example.files;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class FileAdapter extends BaseAdapter implements OnClickListener {
	public List<MyFile> list;
	private Context context;
	private LayoutInflater inflater;
	private PopupWindow window;

	public FileAdapter(Context context) {
		this.context = context;
		this.inflater = inflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
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
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.files_item_layout,
					null);
			holder = new ViewHolder();
			holder.iv_fileIcon = (ImageView) convertView
					.findViewById(R.id.iv_fileIcon);
			holder.tv_fileName = (TextView) convertView
					.findViewById(R.id.tv_fileName);
			holder.tv_fileCreateTime = (TextView) convertView
					.findViewById(R.id.tv_fileCreateTime);
			holder.tv_fileSize = (TextView) convertView
					.findViewById(R.id.tv_fileSize);
			holder.iv_fileIcon.setOnClickListener(this);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MyFile file = list.get(position);
		holder.iv_fileIcon.setTag(position);
		holder.tv_fileName.setText("文件名称：" + file.getFileName());
		holder.tv_fileCreateTime.setText("创建时间：" + file.getCreateTime());
		holder.tv_fileSize.setText("文件大小:" + file.getFileSize());
		if (file.isImage()) {
			new LoadFileAsyncTask(holder.iv_fileIcon).execute(file.getPath());
		} else {
			holder.iv_fileIcon.setImageResource(R.drawable.dirs);
		}
		return convertView;
	}

	class ViewHolder {
		private ImageView iv_fileIcon;
		private TextView tv_fileName, tv_fileSize, tv_fileCreateTime;
	}

	class LoadFileAsyncTask extends AsyncTask<String, Void, Bitmap> {
		private ImageView imageView;

		public LoadFileAsyncTask(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path = params[0];
			Bitmap bitmap = ImageLoad.getImaeLoad().getBitmapFromMemoryCache(
					path);
			if (bitmap == null) {
				bitmap = ImageLoad.getImaeLoad().getImgae(path, 100);
				ImageLoad.getImaeLoad().addBitmapToMemoryCache(path, bitmap);
			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageView.setImageBitmap(result);
		}
	}

	@Override
	public void onClick(View v) {
		window = new PopupWindow(LayoutParams.WRAP_CONTENT, v.getHeight());
		View view = View.inflate(context, R.layout.popup_window_layout, null);
		ClickListener listener = new ClickListener((Integer) v.getTag());
		view.findViewById(R.id.bt_delete).setOnClickListener(listener);
		view.findViewById(R.id.bt_details).setOnClickListener(listener);
		window.setContentView(view);
		window.setBackgroundDrawable(context.getResources().getDrawable(
				R.drawable.local_popup_bg));
		window.setFocusable(true);
		window.showAsDropDown(v, (int) (v.getX() + v.getWidth() + 20),
				(int) v.getY() - v.getHeight());
	}

	class ClickListener implements OnClickListener {
		private int itemId;

		public ClickListener(int itemId) {
			this.itemId = itemId;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bt_delete:
				list.remove(itemId);
				notifyDataSetChanged();
				break;

			case R.id.bt_details:
				Toast.makeText(context,
						"文件名称：" + list.get(itemId).getFileName(), 1).show();
				break;
			}
			if (window.isShowing()) {
				window.dismiss();
			}
		}

	}

}
