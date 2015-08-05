	package com.lisn_6_file.adapter;
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
			// 布局填充器
			//方法一LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
			LayoutInflater inflater=LayoutInflater.from(context);//方法二
			viewHolder holder=null;
			if(convertView==null){//重用convertView，减少渲染次数,
				//System.out.println("########"+position);
				convertView=inflater.inflate(R.layout.listview_item, null); //渲染成布局
				holder=new viewHolder();
				holder.iv=(ImageView) convertView.findViewById(R.id.icon);
			    holder.tv=(TextView) convertView.findViewById(R.id.tv_name);
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
		 * 异步加载
		 * @author Administrator
		 *Params参数
		 * Progress进度值
		 * Result加载的结果
		 */
			class loadTask extends AsyncTask<String, Void, Void>{
				private ImageView iv;
				private Bitmap bm;
			   public loadTask(ImageView v){
				this.iv=v;
			   }
			@Override
			protected Void doInBackground(String... params) {
				
				// 后台（线程）里面处理事情
				String path=params[0];
				try {
					
				bm=loader.getBitmapFromMemoryCache(path);
				if(bm==null){
					bm=Imageloader.decodeBitmapFromResource(path, 80);
					loader.addBitmapToMemoryCache(path, bm);
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
				//notifyDataSetChanged();
				iv.setImageBitmap(bm);
			}
	}
	}
