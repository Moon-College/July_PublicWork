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
		//��ȡ��Ļ�������ֵ
		screenWidth=context.getResources().getDisplayMetrics().widthPixels;
		//��ȡImageLoaderʵ��
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
		//R.java����һ��Ҫע�⣬���Լ�����Ŀ�������R
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
			//����ͼƬ(�����һ���Ƚϴ��ͼƬ��IO������ȽϺ�ʱ��������UI���߳�����ͻ����ױ�ANR�쳣)
			//Ӧ�ÿ����߳�ȥ����ʱ�Ĳ�����---�첽����
			LoadFileAsyncTask  task = new LoadFileAsyncTask(mWrapper.ivIcon);
			//execute(String... params)//params:����ɱ������Ĳ��������磺���Է�2��ֵ��Ҳ���Է�3��ֵ�ȵȡ�
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
	 * Params,����
	 *  Progress,����ֵ
	 *   Result>���صĽ��
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
			// ��̨(�߳�)���洦�������
			//����ͼƬ
			
			String path = params[0];
			String position = params[1];
			 
			try {
				// Bitmap bitmap = BitmapFactory.decodeFile(path);
				
				//���Գ建���л�ȡbitmap
				 bitmap=mImageLoader.getBitmapFromMemoryCache(path);
				if (bitmap==null) {
					//���������û���ҵ��������ѹ�������ͼƬ���Ҵ��뻺����
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
			// �̴߳�����ϻص��ķ���
			super.onPostExecute(result);
			//ˢ��������
			//notifyDataSetChanged();
			iv.setImageBitmap(bitmap);
			
		}
		
	}
	
	
}
