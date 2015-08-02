package com.tz.gridactivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * 自定义GridActivity
 * @author 赵建祥
 *
 */
public class MainActivity extends GridActivity {

	private List<String> data = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		initData();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		setGridAdapter(adapter);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		data.add("拍拍手机");
		data.add("锤子手机");
		data.add("一加手机");
		data.add("魅族手机");
		data.add("小米手机");
	}

	/**
	 * 点击 选项移除选项
	 */
    protected void onGridItemClick(GridView g, View v, int position, long id) {
		data.remove(position);
		adapter.notifyDataSetChanged();
	}
}