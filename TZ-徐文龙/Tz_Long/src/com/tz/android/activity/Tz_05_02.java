package com.tz.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.tz.android.R;
import com.tz.android.bean.Student;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Tz_05_02 extends Activity {
	
	private Context context;
	private GridView gridView;
	private List<Integer> lists;
	private int width;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tz_05_02_activity);
		gridView=(GridView) findViewById(R.id.tz_05_02_gridview);
		lists=new ArrayList<Integer>();
		context=this;
		DisplayMetrics dm=new DisplayMetrics();
		WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		manager.getDefaultDisplay().getMetrics(dm);
		width=dm.widthPixels;
		int itemWidth=(width-10*3)/2;
		int itemHeight=itemWidth*2;
		gridView.setNumColumns(2);
		gridView.setHorizontalSpacing(10);
		gridView.setVerticalSpacing(10);

		MyAdapter adapter=new MyAdapter(lists, context,itemWidth);
		gridView.setAdapter(adapter);
		initList();
		adapter.notifyDataSetChanged();
		
	}
	
	void initList(){
		lists.clear();
		lists.add(R.drawable.img1);
		lists.add(R.drawable.img2);
		lists.add(R.drawable.img7);
		lists.add(R.drawable.img9);
		lists.add(R.drawable.img10);
		lists.add(R.drawable.img13);
		lists.add(R.drawable.img15);
	}
	
	
	

	
class MyAdapter extends BaseAdapter{
		
		private List<Integer> lists;
		private Context context;
		private int itemWidth;
		
		
		

		public MyAdapter(List<Integer> lists, Context context,int itemWidth) {
			super();
			this.lists = lists;
			this.context = context;
			this.itemWidth=itemWidth;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lists.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return lists.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			

			
			if(arg1==null){
				arg1=LayoutInflater.from(context).inflate(R.layout.tz_05_02_grid_item,null);
			
			}

			ImageView img=(ImageView) arg1.findViewById(R.id.tz_05_02_item_img);
			//img.setScaleType(ScaleType.);
			LayoutParams lp=new LayoutParams(itemWidth, itemWidth*2);
			img.setLayoutParams(lp);

			img.setImageDrawable(getResources().getDrawable(lists.get(arg0)));

			

			return arg1;
		}
		
		
		
	}
}
