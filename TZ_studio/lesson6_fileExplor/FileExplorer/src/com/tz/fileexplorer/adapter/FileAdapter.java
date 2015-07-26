package com.tz.fileexplorer.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.fileexplorer.R;
import com.tz.fileexplorer.bean.FileBean;
import com.tz.fileexplorer.utils.ImageLoder;

public class FileAdapter extends BaseAdapter {

	private Context ctx;
	private List<FileBean> list;
	private ImageLoder imageLoder;

	public FileAdapter(Context ctx, List<FileBean> list) {
		super();
		this.ctx = ctx;
		this.list = list;
		imageLoder=ImageLoder.getInstance();
	}

	@Override
	public int getCount() {
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(ctx, R.layout.file_item, null);
			holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
			holder.tv_file_name = (TextView) convertView
					.findViewById(R.id.tv_file_name);
			convertView.setTag(holder);
		} else{
			holder = (ViewHolder) convertView.getTag();
		}
	    final FileBean b=(FileBean) getItem(position);
		holder.tv_file_name.setText(b.getName());
		
		if (b.getIcon()==null) {//加载图片
		  new MyImageTask(holder.avatar).execute(b.getPath(),position+"");
		}else {
			holder.avatar.setImageBitmap(b.getIcon());
		}		
		return convertView;
	}
	
	/**
	 * 此类的描述:加载图片的task 
	 * @author:  studio	
	 */
	class MyImageTask extends  AsyncTask<String, Void, Void>{		
		private ImageView iv;
		private  Bitmap bm=null;
		 
		public MyImageTask(ImageView iv) {
			super();
			this.iv = iv;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		
		}

		@Override
		protected Void doInBackground(String... params) {
			String path=params[0];	
			String position=params[1];
			 bm=imageLoder.getImageFromCash(path);
			if (bm==null) {
				bm=imageLoder.imageCompress(path);
				imageLoder.addImageToCash(path, bm);
			}
			list.get(Integer.parseInt(position)).setIcon(bm);
			
			return null;
		}
		
		protected void onPostExecute(Void result) {		
			notifyDataSetChanged();
		};
		
	};

	 class ViewHolder {
		ImageView avatar;
		TextView tv_file_name;
	}

}
