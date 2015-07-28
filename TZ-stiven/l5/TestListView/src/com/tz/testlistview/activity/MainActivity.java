package com.tz.testlistview.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tz.testlistview.R;
import com.tz.testlistview.adapter.InfoAdapter;
import com.tz.testlistview.bean.Person;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

/** 
 * @author 作者 tz_stiven QQ:114442034 
 * @version 创建时间：2015年7月23日 下午7:55:10 
 * 类说明 
 */
public class MainActivity extends Activity {
	
	private Context mContext;
	
	private ListView mLv_test;
	
	private List<Person> mListData = new ArrayList<Person>();
	
	private InfoAdapter mAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	private void initData() {
		
		int[] icons = {
				R.drawable.weibaobei_29,
				R.drawable.weibaobei_31,
				R.drawable.weibaobei_33,
				R.drawable.weibaobei_35,
				R.drawable.weibaobei_47,
				R.drawable.weibaobei_49,
				R.drawable.weibaobei_51,
				R.drawable.weibaobei_53
		};
		
		int[] hobbys = {
				R.string.h1,
				R.string.h2,
				R.string.h3,
				R.string.h4,
				R.string.h5,
				R.string.h6,
				R.string.h7,
				R.string.h8,
				R.string.h9,
				R.string.h10,
				R.string.h11,
				R.string.h12,
				R.string.h13
		};
		
		
		for(int i = 0; i < 100; i++){
			
			Person person = new Person();
			Random n1 = new Random();
			person.icon = icons[n1.nextInt(7)];
			person.name = "stiven" + i;
			
			switch (n1.nextInt(2)) {
			case 0:
				person.sex = "女";
				break;
			case 1:
				person.sex = "男";
				break;
			default:
				person.sex = "阴阳人";
				break;
			}
			
			person.num = n1.nextInt(100);
			
			person.hobby = hobbys[n1.nextInt(12)];
			
			mListData.add(person);
		}
	}

	private void initView() {
		
		mLv_test = (ListView)findViewById(R.id.lv_test);
		
		mAdapter = new InfoAdapter(this, mListData);
		mLv_test.setAdapter(mAdapter);
		//mLv_test.setAdapter(adapter);
		
		mLv_test.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				final int selectId = position;
				
				final Dialog dialog = new Dialog(mContext, R.style.dialog_delete);
				
				dialog.setContentView(R.layout.dialog_delete_listitem);
				dialog.setCancelable(false);
				TextView tv_name = (TextView)dialog.findViewById(R.id.tv_dialog_name);
				TextView tv_ok = (TextView)dialog.findViewById(R.id.tv_dialog_ok);
				TextView tv_cancel = (TextView)dialog.findViewById(R.id.tv_dialog_cancel);
				
				tv_name.setText(mListData.get(position).name);
				
				tv_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						mListData.remove(selectId);
						
						mAdapter.notifyDataSetChanged();
						dialog.dismiss();
					}
				});
				
				tv_cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						dialog.dismiss();
						
					}
				});
				
				dialog.show();
				
				return true;
			}
		});
		
	}
}
