package com.tz.gridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemLongClickListener{
	private GridView gv;
	private ImageView img_food,img_review;
	private TextView tv_food,tv_review;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initListView();
    }
	private void initListView() {
		gv = (GridView) findViewById(R.id.gv);
		gv.setOnItemLongClickListener(this);
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		int[] imgs_food = new int[]{
				R.drawable.food1,
				R.drawable.food2,
				R.drawable.food3,
				R.drawable.food4,
				R.drawable.food5,
				R.drawable.food6,
				R.drawable.food7,
				R.drawable.food8,
		};
		int[] imgs_review = new int[]{
				R.drawable.face1,
				R.drawable.face2,
				R.drawable.face3,
				R.drawable.face4,
				R.drawable.face5,
				R.drawable.face6,
				R.drawable.face7,
				R.drawable.face8,
		};
		String[] tv_review = new String[]{
				"张飞","曹操","孙权","吕布","诸葛亮","貂蝉","关羽","刘备"
		};
		for (int i = 0; i < 8; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img_food", imgs_food[i]);
			map.put("tv_food", "看着流口水，吃了还想吃");
			map.put("img_review", imgs_review[i]);
			map.put("tv_review", tv_review[i]);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, 
				data, 
				R.layout.grid_item, 
				new String[]{"img_food","tv_food","img_review","tv_review"}, 
				new int[]{R.id.img_food,R.id.tv_food,R.id.img_review,R.id.tv_review});
		gv.setNumColumns(2);
		gv.setAdapter(adapter);
	}
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		parent.removeViewInLayout(view);
		return true;
	}
	private void notifyDatasetChanged() {
		// TODO Auto-generated method stub

	}
}