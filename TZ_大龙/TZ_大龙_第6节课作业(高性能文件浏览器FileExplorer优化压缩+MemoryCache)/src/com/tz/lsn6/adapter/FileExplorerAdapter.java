package com.tz.lsn6.adapter;

import java.util.List;

import android.R.integer;
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

import com.tz.lsn6.R;
import com.tz.lsn6.Util.FunctionHelper;
import com.tz.lsn6.Util.ImageLoader;
import com.tz.lsn6.bean.MyFile;

public class FileExplorerAdapter extends BaseAdapter {

	
	private Context context;
	private List<MyFile> list;
	private LayoutInflater mlayoutInflater;
	private int windowWidth;
	private final int ICON_WIDTH_SCALE = 10;
	private ImageLoader imageLoader;

	public FileExplorerAdapter(Context context,List<MyFile> list) {
		this.context = context;
		this.list = list;
		/* LayoutInflater 是一个将xml布局文件转换成视图的工具类  */
//		mlayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlayoutInflater = LayoutInflater.from(context);
		windowWidth = FunctionHelper.getWindowWidth(context);
		imageLoader = ImageLoader.getInstance();
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
	
	
	//用于临时保存converView
	class ViewHolder {
		TextView holderName = null;
		ImageView holderIcon = null;
	}
	
	/**
	 * 每天加载条目的时候会调些函数
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//获取条目的类信息
		MyFile myFile = (MyFile)getItem(position);
		
		//马袋包，用于保存最后一个请求view的信息
		ViewHolder holder = new ViewHolder();;
		
		//convertView 为空，说明是第一次加载进来
		if(convertView == null) {
			//不存在convertView 通过Layout渲染器生成一个convertView
			convertView = mlayoutInflater.inflate(R.layout.fileexplorer_listview_item, null);
			//对新生成的converView的控件赋值给holder
			holder.holderIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.holderName = (TextView) convertView.findViewById(R.id.tv_name);
			//把这个holder类保存到converView中 便于下次使用   (******************其实一直没理解setTag)
			convertView.setTag(holder);
			
			
		} else {
			//已存在convertView 则获取该view的Tag 里面包含一个ViewHolder
			holder = (ViewHolder)convertView.getTag();
		}
		
		/**
		 * 这里需要理解的是这个holder就直属convertView的，也就是说直接改变它的值直接影响convertView的，并不是一个独立的东西
		 */
		
		//设置holderName
		holder.holderName.setText(myFile.getName());
		
		if(myFile.getIcon() == null) {
			//异步去加载图片
			LoaderIconAsyncTask asyncTask = new LoaderIconAsyncTask(holder.holderIcon);
			asyncTask.execute(myFile.getPath(), position + "");
		} else {
			holder.holderIcon.setImageBitmap(myFile.getIcon());
		}
			
		
		return convertView;
	}

	
	class LoaderIconAsyncTask extends AsyncTask<String, integer, Void> {

		private Bitmap bm;
		private ImageView iv;
		
		public LoaderIconAsyncTask(ImageView imageView) {
			super();
			this.iv = imageView;
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			String path = params[0];
			bm = BitmapFactory.decodeFile(path);
			
			//使用缓存技术 检查缓存中是否存在，不存在则加入缓存中
			Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(path);
			if(bitmap == null) {
				//将图片进行压缩 再存入缓存中
				bm = ImageLoader.decodeBitmapFromResource(path, Math.round(windowWidth/ICON_WIDTH_SCALE));
				imageLoader.addBitmapToMemoryCache(path, bm);
				
			}
			//将图片加载到视图列表中
			//list.get(Integer.parseInt(params[1])).setIcon(bm);
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//这个是适配器的函数，通知数据更新
			//notifyDataSetChanged();
			
			iv.setImageBitmap(bm);
		}
		
	}

}
