package com.tz.fileexplorer.adapter;

import java.util.ArrayList;
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
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.imageloader.ImageLoader;

public class FileAdapter extends BaseAdapter {
	private List<MyFile> list = new ArrayList<MyFile>();
	private Context context; 
	private int screenWidth;
	ImageLoader mImageLoader;
	public FileAdapter(List<MyFile> list,Context context) {
		this.context = context;
		this.list = list;
		//获取屏幕宽度像素值
		screenWidth=context.getResources().getDisplayMetrics().widthPixels;
		//获取ImageLoader实例
		mImageLoader=ImageLoader.getInstance();
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
		MyFile myFile = list.get(position);
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutInflater inflater = LayoutInflater.from(context);
		//R.java导包一定要注意，导自己的项目包下面的R
	    Wrapper mWrapper=null;
		if (convertView==null) {
			convertView = inflater.inflate(R.layout.listview_item, null);
			mWrapper=new Wrapper();
			mWrapper.tvName=(TextView) convertView.findViewById(R.id.tv_name);
			mWrapper.ivIcon=(ImageView)convertView.findViewById(R.id.icon);
			 convertView.setTag(mWrapper);			 
		}
		else {
			  mWrapper=(Wrapper) convertView.getTag();
		}
		
		mWrapper.tvName.setText(myFile.getName());	
		

		// TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
	
	// 	ImageView icon = (ImageView) v.findViewById(R.id.icon);
		if(myFile.getIcon()==null){
			//加载图片(如果是一个比较大的图片，IO操作会比较耗时，这样在UI主线程里面就会容易报ANR异常)
			//应该开启线程去做耗时的操作。---异步任务
			LoadFileAsyncTask  task = new LoadFileAsyncTask(mWrapper.ivIcon);
			//execute(String... params)//params:代表可变数量的参数；比如：可以放2个值，也可以放3个值等等。
			task.execute(myFile.getPath(),position+"" );
			
		}else{
			mWrapper.ivIcon.setImageBitmap(myFile.getIcon());
			
		}
		return convertView;
	}
	
	class Wrapper{
		TextView tvName;
		ImageView ivIcon;
	}

	/*
	 * AsyncTask<
	 * Params,参数
	 *  Progress,进度值
	 *   Result>加载的结果
	 */
	class LoadFileAsyncTask extends AsyncTask<String, Void, Void>{
       ImageView iv;
       Bitmap bitmap=null;
		public LoadFileAsyncTask(ImageView iv) {
		super();
		// TODO Auto-generated constructor stub
		this.iv=iv;
	}

		@Override
		protected Void doInBackground(String... params) {
			// 后台(线程)里面处理得事情
			//加载图片
			
			String path = params[0];
			String position = params[1];
			 
			try {
				// Bitmap bitmap = BitmapFactory.decodeFile(path);
				
				//尝试冲缓存中获取bitmap
				 bitmap=mImageLoader.getBitmapFromMemoryCache(path);
				if (bitmap==null) {
					//如果缓存中没有找到，则加载压缩过后的图片并且存入缓存中
					  bitmap=ImageLoader.getCompressedImage(path, screenWidth/8);
					  mImageLoader.addBitmapToMemory(path, bitmap);
				}				
				
			//	list.get(Integer.parseInt(position)).setIcon(bitmap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// 线程处理完毕回调的方法
			super.onPostExecute(result);
			//刷新适配器
			//notifyDataSetChanged();
			iv.setImageBitmap(bitmap);
			
		}
		
	}
	
	
}
