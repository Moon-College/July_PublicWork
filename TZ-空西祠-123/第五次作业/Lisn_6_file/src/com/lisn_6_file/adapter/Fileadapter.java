package com.lisn_6_file.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.lisn_6_file.R;
import com.lisn_6_file.bean.MyFile;
import com.lisn_6_file.utils.Imageloader;

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

public class Fileadapter extends BaseAdapter {
	private Context context;
	private Imageloader loader;
	private List<MyFile> list=new ArrayList<MyFile>();
	public Fileadapter(List<MyFile> list,Context context){
	this.list=list;
	this.context=context;
	loader=Imageloader.getIntance();
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
		MyFile myfile=list.get(position);
		// ���������
		//����һLayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
		LayoutInflater inflater=LayoutInflater.from(context);//������
		viewHolder holder=null;
		if(convertView==null){//����convertView��������Ⱦ����,
			System.out.println("########"+position);
			convertView=inflater.inflate(R.layout.listview_item, null); //��Ⱦ�ɲ���
			holder=new viewHolder();
			holder.iv=(ImageView) convertView.findViewById(R.id.imageview1);
		    holder.tv=(TextView) convertView.findViewById(R.id.textview);
		    convertView.setTag(holder);
		}else{
			holder=(viewHolder) convertView.getTag();
		}
       		
		holder.tv.setText(myfile.getName());
	    if(myfile.getBitmap()==null){
	    	loadTask task=new loadTask(holder.iv);
	    	task.execute(myfile.getPath(),position+"");
	    }else{
			holder.iv.setImageBitmap(myfile.getBitmap());
	    }
		return convertView;
	}
	class viewHolder{
		TextView tv;
		ImageView iv;
	}
	/**
	 * �첽����
	 * @author Administrator
	 *Params����
	 * Progress����ֵ
	 * Result���صĽ��
	 */
class loadTask extends AsyncTask<String, Void, Void>{
	private ImageView iv;
	private Bitmap bm;
   public loadTask(ImageView v){
	this.iv=v;
   }
		@Override
		protected Void doInBackground(String... params) {
			
			// ��̨���̣߳����洦������
			String path=params[0];
			String path1=params[1];
			try {
				//��ȡ��Ļ�߶ȣ�����ͼƬΪ��Ļ�ļ���֮��,ͼƬѹ������С�ڴ����ģ��ӿ������ٶ�
				//Imageloader.decodeitmapForm(path1, 30)
				//Bitmap map=BitmapFactory.decodeFile(path);//����·��
			//list.get(Integer.parseInt(path1)).setBitmap(Imageloader.decodeitmapForm(path, 80));
				bm=loader.getBitmapFromMemoryCache(path);
				if(bm==null){
					bm=Imageloader.decodeitmapForm(path, 80);
					loader.addBitmapCache(path, bm);
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// �̴߳�����ϻص��ķ���
			super.onPostExecute(result);
			//notifyDataSetChanged();
			iv.setImageBitmap(bm);
		}
	
}

}
