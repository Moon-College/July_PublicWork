package com.tz.fileadapter.file;

import java.util.ArrayList;
import java.util.List;

import com.tz.fileadapter.R;
import com.tz.fileadapter.baen.Myfile;

import android.Manifest.permission;
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

public class fileadpeter extends BaseAdapter {
	private List<Myfile> list =new ArrayList<Myfile>();
	private Context context;
	
    public fileadpeter( List<Myfile> list,Context context) { 
    	this.context = context;
    	this.list=list;
	
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
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View v=inflater.inflate(R.layout.listview_item, null);
		TextView tv_name =(TextView) v.findViewById(R.id.tv_name);
		Myfile myFile=list.get(position);
		tv_name.setText(myFile.getName());
		ImageView icon=(ImageView) v.findViewById(R.id.icon);
		if(myFile.getIcon() == null){
			LoadFileAsyncTack tack = new LoadFileAsyncTack();
			tack.execute(myFile.getPath(),position+"");
		
		}else{
		icon.setImageBitmap(myFile.getIcon());
		}
		return v;
	}

	class LoadFileAsyncTack extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			String path = params[0];
			String position = params[1];
			try {
				Bitmap bitmap = BitmapFactory.decodeFile(path);
				list.get(Integer.parseInt(position)).setIcon(bitmap);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			notifyDataSetChanged();
		}
}
}
