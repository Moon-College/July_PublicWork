package com.tz.adapter;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import com.tz.R;
import com.tz.bean.MyFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	List<MyFile> list;
	Context context;

	private ImageView mImageView;
	private TextView mTextView;

	public MyAdapter(List<MyFile> list, Context o) {
		super();
		this.list = list;
		this.context = o;
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
		View v = LayoutInflater.from(context).inflate(R.layout.lv_item, null);
		mImageView = (ImageView) v.findViewById(R.id.iv_icon);
		mTextView = (TextView) v.findViewById(R.id.tv_info);

		MyFile myFile = list.get(position);
		mTextView.setText(myFile.getInfo());

		if (myFile.getIcon() == null) {
			// �첽�������ͼƬ
			LoadAsync task = new LoadAsync();
			// �ص�����
			task.execute(myFile.getPath(), position + "");
		} else {
			mImageView.setImageBitmap(myFile.getIcon());
		}

		return v;
	}

	class LoadAsync extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			String path = params[0];
			String position = params[1];
			try {
				Bitmap bitmap = BitmapFactory.decodeFile(path);

				// ����Ϊʲô������ע�͵�д������list����ȥ��ȡ����������bitmap
				// mImageView.setImageBitmap(bitmap);
				list.get(Integer.parseInt(position)).setIcon(bitmap);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			notifyDataSetChanged();
		}
	}
}
