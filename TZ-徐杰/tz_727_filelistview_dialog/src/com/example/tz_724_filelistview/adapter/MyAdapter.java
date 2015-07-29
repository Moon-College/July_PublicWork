package com.example.tz_724_filelistview.adapter;

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

import com.example.tz_724_filelistview.R;
import com.example.tz_724_filelistview.bean.MyFile;
import com.example.tz_724_filelistview.utils.ImageLoader;

public class MyAdapter extends BaseAdapter {

	List<MyFile> list;
	Context context;

	public MyAdapter(List<MyFile> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		// 调用系统服务创建填充器
		MyFile myFile = list.get(position);
		LayoutInflater inflater;
		ViewHolder holder;
		// 或者下面方法创建
		// LayoutInflater inflater2 = LayoutInflater.from(context);
		// 考虑到复用问题，所以我们这边不需要每次都创建View 使用重用的view convertView
		// 使用convertView后每次去寻找id也是耗时的操作，所以我们引用一个viewholder类将找id变换为找对象的属性
		// View v = inflater.inflate(R.layout.listview_item, null);
		// ImageView img_icon = (ImageView) v.findViewById(R.id.img_icon);
		// TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
		// tv_name.setText(myFile.getName());
		// if (myFile.getIsIcon()) {
		// Bitmap bitmap = BitmapFactory.decodeFile(myFile.getPath());
		// img_icon.setImageBitmap(bitmap);
		//
		// } else {
		// img_icon.setImageBitmap(myFile.getIcon());
		// }
		//
		// return v;
		if (convertView == null) {
			holder = new ViewHolder();
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder.img_icon = (ImageView) convertView
					.findViewById(R.id.img_icon);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);// 设置数据
		} else {
			holder = (ViewHolder) convertView.getTag();// 得到数据

		}
		holder.tv_name.setText(myFile.getName());
		if(myFile.getIcon() == null)
		{//采用异步线程来取数据
			holder.img_icon.setTag(myFile.getPath());
			 new ImgAsyncTask(holder.img_icon).execute(myFile.getPath(),position+"");
			 
		}else
		{
			holder.img_icon.setImageBitmap(myFile.getIcon());
		}

		return convertView;
	}

	class ViewHolder {
		TextView tv_name;
		ImageView img_icon;

	}

	/**
	 * 图片加载异步类
	 * 
	 * @author xujie
	 * 
	 */
	class ImgAsyncTask extends AsyncTask<String, Void, Void> {
		Bitmap bitmap;
		ImageView icon;
		private String path;
		//private int position;
		public ImgAsyncTask(ImageView icon) {
			this.icon  =icon;
		}

		@Override
		protected Void doInBackground(String... params) {
			path = params[0];
			
			ImageLoader imageLoader = ImageLoader.getInstance();
			//list.get(position).setIcon(bitmap);
			//从内存中拿图片，如果是空则说明内存没有。那么压缩图片并放入内存中。内存中有就直接用
			bitmap =imageLoader.getBitmapFromMemory(path);
			if(bitmap == null )
			{
			bitmap = imageLoader.getCompressionBitmapForRequest(path, 50);
			imageLoader.addBitmapToMemory(path, bitmap);
			
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if(icon.getTag().equals(path))
			{
		     icon.setImageBitmap(bitmap);
			}
			notifyDataSetChanged();
		}
        
	}

}
