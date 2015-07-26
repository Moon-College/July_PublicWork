package com.tz.ls6.fileexplorer.adapter;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

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

import com.example.tz_fileexplorer.R;
import com.tz.ls6.fileexplorer.entity.MyFile;
import com.tz.ls6.fileexplorer.util.ImageLoader;
/**
 * 文件列表的适配器
 * @author GWP
 *
 */
public class FileListViewAdapter extends BaseAdapter {
	private List<MyFile> files;
	private Context context;
	/**
	 * 图片缓存
	 */
	private ImageLoader imgLoader=ImageLoader.getInstance();
	public FileListViewAdapter(List<MyFile> files,Context context) {
		super();
		this.files = files;
		this.context=context;
	}

	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public Object getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder=null;
		if(view==null){
			LayoutInflater inflater=LayoutInflater.from(context);
			holder=new ViewHolder();
			view=inflater.inflate(R.layout.file_list_item, null);
			holder.ivIcon=(ImageView) view.findViewById(R.id.img_icon);
			holder.tvName=(TextView) view.findViewById(R.id.tv_name);
			view.setTag(holder);
		}else{
			holder=(ViewHolder) view.getTag();
		}
		MyFile file=files.get(position);
		Bitmap icon=file.getIcon();	
		if(icon==null){
			//先从缓存中取图片，没有再去从sd卡中读取
			icon=imgLoader.getBitmap(file.getPath());
			if(icon==null){
				new ImageAsyncTask(holder.ivIcon).execute(file.getPath());
			}
		}
		if(icon!=null){
			holder.ivIcon.setImageBitmap(icon);
		}
		holder.tvName.setText(file.getFileName());
		return view;
	}
	
	class ViewHolder{
		public ImageView ivIcon;
		public TextView tvName;
	}
	
	class ImageAsyncTask extends AsyncTask<String, Void, Bitmap>{
		private MyFile file;
		private String path;
		private ImageView ivIcon;
		public ImageAsyncTask(MyFile file) {
			super();
			this.file = file;
		}
		
		public ImageAsyncTask(ImageView ivIcon) {
			super();
			this.ivIcon = ivIcon;
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			path=params[0];
			//以像素为单位缩放图片
			Bitmap bm=resizeBitmap(path, 90);
			return bm;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			ivIcon.setImageBitmap(result);
			imgLoader.addBitmap(path, result);
			//notifyDataSetChanged();
		}
		
		private Bitmap resizeBitmap(String path,int width){
			BitmapFactory.Options options=new BitmapFactory.Options();
			//只解析图片的宽高
			options.inJustDecodeBounds=true;
			BitmapFactory.decodeFile(path, options);
			int orignalWidth=options.outWidth;
			int inSampleSize=1;
			if(orignalWidth>width){
				inSampleSize=Math.round((float)orignalWidth/width);
			}
			//缩放比：比如2 则缩放到1/2
			options.inSampleSize=inSampleSize;
			options.inJustDecodeBounds=false;
			Bitmap bm=BitmapFactory.decodeFile(path, options);
			return bm;
		}
		
	}
}
