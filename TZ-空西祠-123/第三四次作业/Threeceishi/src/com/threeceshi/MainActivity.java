package com.threeceshi;

import java.util.ArrayList;
import java.util.List;

import com.entity.dao;
import com.threeceishi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity implements OnClickListener{
	private int[] imageid=new int[]{R.drawable.face1,R.drawable.face2,R.drawable.face3,R.drawable.face4,R.drawable.face5,R.drawable.face6,R.drawable.face7,R.drawable.face8};
	private List<dao> mlist=new ArrayList<dao>();
	private ListView listview;
	private Button btn;
	private adapter apter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		init();
		btn=(Button) findViewById(R.id.button);
		btn.setOnClickListener(this);
		listview=(ListView) findViewById(R.id.listview1);
	    apter=new adapter(mlist,MainActivity.this);
		listview.setAdapter(apter);
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}

	private void init() {
		// TODO Auto-generated method stub
		for(int i=0;i<80;i++){
			dao bean=new dao(imageid[i/10], "张三"+i, "男", "颜值20"+i, "睡觉");
		 mlist.add(bean);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int posin=5;//指定某项置顶
		dao a=mlist.get(5);
		mlist.add(0, a);
		apter.notifyDataSetChanged();
		//listview.setSelection(0);//置顶
	}

	

	
}
