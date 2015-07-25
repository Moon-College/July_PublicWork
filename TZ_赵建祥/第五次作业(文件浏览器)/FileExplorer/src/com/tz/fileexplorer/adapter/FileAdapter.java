package com.tz.fileexplorer.adapter;

import java.util.List;

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

import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.MyFile;

public class FileAdapter extends BaseAdapter {
	
	private List<MyFile> list;//数据
	private Context context; //上下文件，用于加载布局
	
	public FileAdapter(List<MyFile> list, Context context) {
		super();
		this.list = list;
		this.context = context;
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
		//实义布局加载器
		LayoutInflater inflater=LayoutInflater.from(context);
		//左手数据
		MyFile myFile=list.get(position);
		//右手视图
		View view=inflater.inflate(R.layout.file_item, null);
		
		ImageView iconIv=(ImageView) view.findViewById(R.id.iv_icon);
		TextView nameTv=(TextView) view.findViewById(R.id.tv_name);
		if(myFile.getIcon()==null){
			//起动异步加载
			ImageLoadTask imageTask=new ImageLoadTask();
			imageTask.execute(myFile.getPath(),position+"");
		}else{
			iconIv.setImageBitmap(myFile.getIcon());
		}
		nameTv.setText(myFile.getName());
		return view;
	}
	
	private class ImageLoadTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			String path=params[0];
			String position=params[1];
			MyFile myFile=list.get(Integer.parseInt(position));
			Bitmap image=BitmapFactory.decodeFile(path);
			myFile.setIcon(image);
			return null;
		}

		//数据加载完成后，刷新list
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			notifyDataSetChanged();
		}
	}

}
