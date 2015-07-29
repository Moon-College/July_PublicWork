package com.tz.stiven.testgridview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tz.stiven.testgridview.adapter.InfoAdapter;
import com.tz.stiven.testgridview.dialog.Person;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {
	
	private Context mContext;
	
	private GridView mGV_test;
	
	private InfoAdapter mAdapter;
	
	private List<Person> mListData = new ArrayList<Person>();
	
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
				R.drawable.weibaobei_1,
				R.drawable.weibaobei_2,
				R.drawable.weibaobei_3,
				R.drawable.weibaobei_4,
				R.drawable.weibaobei_5,
				R.drawable.weibaobei_6,
				R.drawable.weibaobei_7,
				R.drawable.weibaobei_8
		};
		
		int[] foodImages = {
				R.drawable.p1,
				R.drawable.p2,
				R.drawable.p3,
				R.drawable.p4,
				R.drawable.p5,
				R.drawable.p6,
				R.drawable.p7,
				R.drawable.p8,
				R.drawable.p9
		};
		
		for(int i = 0; i < 100; i++){
			Person person = new Person();
			Random random = new Random();
			person.icon = icons[random.nextInt(8)];
			person.foodImage = foodImages[random.nextInt(9)];
			person.name = "stiven" + 1;
			person.numString = random.nextInt(30);
			person.describe = "色香味俱全的" + person.numString + "道菜";
			
			mListData.add(person);
		}
		
	}

	private void initView() {
		
		mGV_test = (GridView)findViewById(R.id.gv_test);
		
		mAdapter = new InfoAdapter(mContext, mListData);
		
		mGV_test.setAdapter(mAdapter);
		
	}
	
}
