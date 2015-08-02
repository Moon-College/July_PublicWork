package com.tz.gridactivity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * �Զ���GridActivity
 * @author �Խ���
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
	 * ��ʼ������
	 */
	private void initData() {
		data.add("�����ֻ�");
		data.add("�����ֻ�");
		data.add("һ���ֻ�");
		data.add("�����ֻ�");
		data.add("С���ֻ�");
	}

	/**
	 * ��� ѡ���Ƴ�ѡ��
	 */
    protected void onGridItemClick(GridView g, View v, int position, long id) {
		data.remove(position);
		adapter.notifyDataSetChanged();
	}
}