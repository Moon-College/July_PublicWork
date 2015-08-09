package com.tz.lsn6.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lsn6.R;
import com.tz.lsn6.Util.ImageLoader;
import com.tz.lsn6.bean.MyFile;

public class FileExplorerAdapter extends BaseAdapter {

	
	private Context context;
	private List<MyFile> list;
	private LayoutInflater mlayoutInflater;
	private int windownWidth;
	private final int ICON_WIDTH_SCALE = 7;

	public FileExplorerAdapter(Context context,List<MyFile> list) {
		this.context = context;
		this.list = list;
//		mlayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlayoutInflater = LayoutInflater.from(context);
		windownWidth = getWindowWidth(context);
	}
	
	private int getWindowWidth(Context cnt) {
		//��ȡ�ֻ���Ļ�Ŀ��
		WindowManager wm = (WindowManager) cnt.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
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
	
	
	//������ʱ����converView
	class ViewHolder {
		TextView holderName = null;
		ImageView holderIcon = null;
	}
	
	@SuppressWarnings("null")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//����������ڱ������һ������view����Ϣ
		ViewHolder holder = null;
		
//		System.out.println("position��" + position);
		//convertView Ϊ�գ�˵���ǵ�һ�μ��ؽ���
		if(convertView == null) {
			//������convertView ͨ��Layout��Ⱦ������һ��convertView
			convertView = mlayoutInflater.inflate(R.layout.fileexplorer_listview_item, null);

			holder = new ViewHolder();
			//�������ɵ�converView�Ŀؼ���ֵ��holder
			holder.holderIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.holderName = (TextView) convertView.findViewById(R.id.tv_name);
			//�����holder�ౣ�浽converView�� �����´�ʹ��   (******************����һֱû���setTag)
			convertView.setTag(holder);
			
		} else {
			//�Ѵ���convertView �򽫵�ǰview��ֵ��holder
			holder = (ViewHolder)convertView.getTag();
		}
		
//		ImageView iv_icon = (ImageView)convertView.findViewById(R.id.iv_icon);
//		TextView tv_name = (TextView)convertView.findViewById(R.id.tv_name);
		
		MyFile myFile = (MyFile)getItem(position);
		holder.holderName.setText(myFile.getName());
		holder.holderIcon.setImageBitmap(myFile.getIcon());
		
		//�ж��Ƿ�ΪͼƬ
		if(myFile.isImg()) {
			if(myFile.getIcon() == null) {
				//�첽ȥ����ͼƬ
				System.out.println("***************************get start asyn...execute");
				LoaderIconAsyncTask asyncTask = new LoaderIconAsyncTask();
				asyncTask.execute(myFile.getPath(), position + "");
			} else {
				//�󲿷ִ�������
//				holder.holderIcon.setImageBitmap(myFile.getIcon());
				holder.holderIcon.setImageBitmap(ImageLoader.decodeBitmapFromResource(myFile.getPath(), Math.round((float)windownWidth/ICON_WIDTH_SCALE)));
			}
			
		} 
		
		return convertView;
	}

	
	class LoaderIconAsyncTask extends AsyncTask<String, integer, Void> {

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			String path = params[0];
//			Bitmap bm = BitmapFactory.decodeFile(path);
			System.out.println("width:" + windownWidth);
			Bitmap bm = ImageLoader.decodeBitmapFromResource(path, Math.round(windownWidth/ICON_WIDTH_SCALE));
			list.get(Integer.parseInt(params[1])).setIcon(bm);
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
			//������������ĺ�����֪ͨ���ݸ���
			notifyDataSetChanged();
		}
		
	}

}
