package com.tz.l5.gridview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.tz.l5.gridview.adapter.GridViewAdapter;
import com.tz.l5.gridview.entity.Item;

public class MainActivity extends Activity {
	private GridView gvItems;
	private List<Item> items;
	private int[] icons={
		R.drawable.face1,
		R.drawable.face2,
		R.drawable.face3,
		R.drawable.face4,
		R.drawable.face5,
		R.drawable.face6,
		R.drawable.face7,
		R.drawable.face8
	};
	
	private String[] names={
			"’≈∑…",
			"≤‹≤Ÿ",
			"ÀÔ»®",
			"¬¿≤º",
			"÷Ó∏¡¡",
			"ıı≤ı",
			"πÿ”",
			"¡ı±∏"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	private void initData() {
		items=new ArrayList<Item>();
		for(int i=0;i<icons.length;i++){
			Item item=new Item();
			item.setIconId(icons[i]);
			item.setName(names[i]);
			items.add(item);
		}
		gvItems.setAdapter(new GridViewAdapter(items, this));
	}

	private void initView() {
		gvItems=(GridView) findViewById(R.id.gv_pics);
	}

	
}
