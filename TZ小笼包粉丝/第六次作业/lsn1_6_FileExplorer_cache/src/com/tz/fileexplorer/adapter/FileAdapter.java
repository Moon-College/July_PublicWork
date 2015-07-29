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
		//R.java����һ��Ҫע�⣬���Լ�����Ŀ�������R
		ViewHolder holder = null;
		if(convertView==null){//����convertView��������Ⱦ������
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder();
			//1.��holder��ֵ�ؼ�
			holder.iv = (ImageView) convertView.findViewById(R.id.icon);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(myFile.getName());
		if(myFile.getIcon()==null){
			//����ͼƬ(�����һ���Ƚϴ��ͼƬ��IO������ȽϺ�ʱ��������UI���߳�����ͻ����ױ�ANR�쳣)
			//Ӧ�ÿ����߳�ȥ����ʱ�Ĳ�����---�첽����
			LoadFileAsyncTask  task = new LoadFileAsyncTask(holder.iv);
			//execute(String... params)//params:����ɱ������Ĳ��������磺���Է�2��ֵ��Ҳ���Է�3��ֵ�ȵȡ�
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
	 * Params,����
	 *  Progress,����ֵ
	 *   Result>���صĽ��
	 */
	class LoadFileAsyncTask extends AsyncTask<String, Void, Void>{
		private ImageView iv;
		private Bitmap bm;

		public LoadFileAsyncTask(ImageView iv) {
			this.iv  = iv;
		}

		@Override
		protected Void doInBackground(String... params) {
			// ��̨(�߳�)���洦�������
			//����ͼƬ
			String path = params[0];
			String position = params[1];
			try {
				bm = imageLoader.getBitmapFromMemoryCache(path);
				//�ж��ڴ�����֮ǰ�Ƿ��Ѿ�������
				if(bm==null){
					//���û�������ȥSD������
					bm = ImageLoader.decodeBitmapFromResource(path, 100);//ͼƬѹ��
					imageLoader.addBitmapToMemoryCache(path, bm);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// �̴߳�����ϻص��ķ���
			super.onPostExecute(result);
			//ˢ��������
//			notifyDataSetChanged();
			iv.setImageBitmap(bm);
		}
		
	}
	
	
}
