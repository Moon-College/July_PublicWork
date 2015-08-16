package com.tz.fileexplorer.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
import com.tz.fileexplorer.util.ImageLoader;

public class FileAdapter extends BaseAdapter {
	private List<MyFile> list = new ArrayList<MyFile>();
	private Context context;
	private ImageLoader imageLoader; 
	public FileAdapter(List<MyFile> list,Context context) {
		this.context = context;
		this.list = list;
		imageLoader = ImageLoader.getIntance();
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
		ViewHolder holder = null;
		if(convertView==null){//重用convertView，减少渲染次数。
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder();
			//1.给holder赋值控件
			holder.iv = (ImageView) convertView.findViewById(R.id.icon);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(myFile.getName());
		if(myFile.getIcon()==null){
			//加载图片(如果是一个比较大的图片，IO操作会比较耗时，这样在UI主线程里面就会容易报ANR异常)
			//应该开启线程去做耗时的操作。---异步任务
			LoadFileAsyncTask  task = new LoadFileAsyncTask(holder.iv);
			//execute(String... params)//params:代表可变数量的参数；比如：可以放2个值，也可以放3个值等等。
			task.execute(myFile.getPath(),position+"");
			
		}else{
			holder.iv.setImageBitmap(myFile.getIcon());
		}
		return convertView;
	}
	
	class ViewHolder {
		TextView tv;
		ImageView iv;
	}

	/*
	 * AsyncTask<
	 * Params,参数
	 *  Progress,进度值
	 *   Result>加载的结果
	 */
	class LoadFileAsyncTask extends AsyncTask<String, Void, Void>{
		private ImageView iv;
		private Bitmap bm;

		public LoadFileAsyncTask(ImageView iv) {
			this.iv  = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			// 后台(线程)里面处理得事情
			//加载图片
			String path = params[0];
			String position = params[1];
			try {
				bm = imageLoader.getBitmapFromMemoryCache(path);
				//判断内存里面之前是否已经缓存了
				if(bm==null){
					//如果没缓存过，去SD卡加载
					bm = ImageLoader.decodeBitmapFromResource(path, 100);//图片压缩
					imageLoader.addBitmapToMemoryCache(path, bm);
				}
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
//			notifyDataSetChanged();
			iv.setImageBitmap(bm);
		}
		
	}
	
	
}
