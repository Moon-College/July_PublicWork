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

import com.tz.fileexplorer.MainActivity;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.util.ImageLoader;
import com.tz.fileexplorer.util.MyLog;

public class FileAdapter extends BaseAdapter {
	
	private List<MyFile> list;//数据
	private Context context; //上下文件，用于加载布局
	public static ImageLoader imageLoader;
	
	public FileAdapter(List<MyFile> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=ImageLoader.getInstance();
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
		FileHolder holder;
		//右手视图
		if(convertView==null){
			convertView=inflater.inflate(R.layout.file_item, null);
			holder=new FileHolder();
			holder.iconIv=(ImageView) convertView.findViewById(R.id.iv_icon);
			holder.nameTv=(TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}
		else{
			holder=(FileHolder) convertView.getTag();
		}
		if(myFile.isImage()){
			
			//先从lru缓存中加载图片
			Bitmap bitmap=imageLoader.getImageFromLruCache(myFile.getPath());
			if(bitmap!=null){
				holder.iconIv.setImageBitmap(bitmap);
			}
			//起动异步加载
			else{
				//启动异步加载时，将图片设为默认图片
				holder.iconIv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
				
				ImageLoadTask imageTask=new ImageLoadTask(holder.iconIv);
				imageTask.execute(myFile.getPath());
			}
		}else{
			holder.iconIv.setImageBitmap(myFile.getIcon());
		}
		holder.nameTv.setText(myFile.getName());
		return convertView;
	}
	
	//控件缓存
	class  FileHolder {
		ImageView iconIv;
		TextView nameTv;
	}
	
	private class ImageLoadTask extends AsyncTask<String, Void, Bitmap>{
		private ImageView iconIv;
		
		public ImageLoadTask(ImageView iconIv) {
			// TODO Auto-generated constructor stub
			this.iconIv=iconIv;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String path=params[0];
			//Bitmap image=BitmapFactory.decodeFile(path);
			//根据路径，图片指定宽度，用压缩后的图片进行显示，降低所占内存
			//将压缩后的图片，放入LRU缓存，
			Bitmap image=imageLoader.getImageFromLruCache(path);
			if(image==null){
				//按屏幕宽度的八分之一，进行压缩
				int toWidth=Math.round(((MainActivity)context).getWindowManager().getDefaultDisplay().getWidth()/8);
				MyLog.i("jzhao", "toWidth-->"+toWidth);
				image=ImageLoader.compressedImageFromResource(path, toWidth);
				imageLoader.addImageToLruCache(path, image);
			}
			return image;
		}

		//数据加载完成后，刷新list
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//notifyDataSetChanged();
			iconIv.setImageBitmap(result);
		}
	}

}
