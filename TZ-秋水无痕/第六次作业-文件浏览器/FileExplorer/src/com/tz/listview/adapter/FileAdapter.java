package com.tz.listview.adapter;

import java.util.ArrayList;
import java.util.List;

import com.tz.listview.R;
import com.tz.listview.bean.MyFile;

import android.app.Activity;
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

public class FileAdapter extends BaseAdapter {
	private List<MyFile> list = new ArrayList<MyFile>();
	private Context context;
	public FileAdapter(List<MyFile> list,Context context) {
		this.context = context;
		this.list = list;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		MyFile myFile = list.get(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.listview_item, null);
		TextView tv = (TextView) v.findViewById(R.id.tv);
		tv.setText(myFile.getName());
		ImageView icon = (ImageView) v.findViewById(R.id.icon);
		if (myFile.getIcon()==null) {
			//比较耗时的操作不要放到主线程
			LoadFileAsyncTask task = new LoadFileAsyncTask();
			task.execute(myFile.getPath(),position+"");
		}else {
			icon.setImageBitmap(myFile.getIcon());
		}
		return v;
	}
	/**
	 * AsyncTask<
	 * Params,参数
	 * Progress,进度值
	 * Result>家在结果
	 */
	class LoadFileAsyncTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			//后台加载图片
			String path = params[0];
			String position = params[1];
			Bitmap bitmap = BitmapFactory.decodeFile(path);
			list.get(Integer.parseInt(position)).setIcon(bitmap);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// 线程处理完毕回调的方法
			super.onPostExecute(result);
			//刷新适配器
			notifyDataSetChanged();
		}
		
	}

}
