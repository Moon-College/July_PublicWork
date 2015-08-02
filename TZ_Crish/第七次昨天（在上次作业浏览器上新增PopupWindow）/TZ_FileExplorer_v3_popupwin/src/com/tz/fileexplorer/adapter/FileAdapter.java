package com.tz.fileexplorer.adapter;

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
import com.tz.fileexplorer.beans.MyFile;
import com.tz.fileexplorer.utils.ImageLoader;

public class FileAdapter extends BaseAdapter {
	private List<MyFile> list = new ArrayList<MyFile>();
	private Context context; 
	private LayoutInflater inflater;
	private ImageLoader instance;
	
	public FileAdapter(List<MyFile> list, Context context) {
	    inflater = LayoutInflater.from(context);
	    instance = ImageLoader.getInstance();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
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
		ViewHolder holder = null;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.listview_fileitem, null);
			holder.tvFileName = (TextView) convertView.findViewById(R.id.tv_filename);
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_file_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		MyFile myFile = list.get(position);
		holder.tvFileName.setText(myFile.getName());
		
		if(myFile.getIcon() == null){
			//����ͼƬ(�����һ���Ƚϴ��ͼƬ��IO������ȽϺ�ʱ��������UI���߳�����ͻ����ױ�ANR�쳣)
			//Ӧ�ÿ����߳�ȥ����ʱ�Ĳ�����---�첽����
			LoadFileAsyncTask  task = new LoadFileAsyncTask(holder.ivIcon);
			//execute(String... params)//params:����ɱ������Ĳ��������磺���Է�2��ֵ��Ҳ���Է�3��ֵ�ȵȡ�
			task.execute(myFile.getPath(), position+"");
			
		}else{
			holder.ivIcon.setImageBitmap(myFile.getIcon());
		}
		
		return convertView;
	}
	
	class ViewHolder {
		ImageView ivIcon; //�ļ�ͼ��
		TextView tvFileName; //�ļ�����
	}

	/*
	 * AsyncTask<
	 * Params,����
	 *  Progress,����ֵ
	 *   Result>���صĽ��
	 */
	class LoadFileAsyncTask extends AsyncTask<String, Void, Void>{
		private ImageView ivIcon;
		
		public  LoadFileAsyncTask(ImageView ivIcon) {
			this.ivIcon = ivIcon;
		}

		@Override
		protected Void doInBackground(String... params) {
			// ��̨(�߳�)���洦�������
			//����ͼƬ
			String path = params[0];
			String position = params[1];
			try {
				//Bitmap bitmap = BitmapFactory.decodeFile(path);
				Bitmap bitmap = instance.getBitmapFromCache(path);
				if(bitmap == null) {
					bitmap = instance.decodeBitmapFromFile(path, ivIcon.getWidth(), ivIcon.getHeight());
					instance.addBitmapToCache(path, bitmap); //��ӻ���		
				}
				
				list.get(Integer.parseInt(position)).setIcon(bitmap);
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
			notifyDataSetChanged();
		}
		
	}
	
	
}

