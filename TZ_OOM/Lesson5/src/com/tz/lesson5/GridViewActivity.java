package com.tz.lesson5;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.tz.lesson5.adapter.GridViewAdapter;
import com.tz.lesson5.bean.Menu;

public class GridViewActivity extends Activity implements OnItemLongClickListener {

	private GridView mGridView;
	private GridViewAdapter adapter;
	
	private List<Menu> menus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		
		mGridView = (GridView) findViewById(R.id.gv);
		menus = initData();
		adapter = new GridViewAdapter(this, menus);
		mGridView.setAdapter(adapter);
		
		mGridView.setOnItemLongClickListener(this);
	}

	
	private List<Menu> initData() {
		List<Menu> menus = new ArrayList<Menu>();
		for(int i=0; i< 24; i++) {
			Menu menu = new Menu();
			
			menu.setIcon(getResources().getIdentifier("food"+(i+1), "drawable", getPackageName()));
			menu.setDescription("��ζ����");
			menu.setAuthor("��ζ����");
			// ��ȡfaceͼƬ��ԴID�������õ�AuthIcon��
			menu.setAuthIcon(getResources().getIdentifier("face" + ((i % 10) +1), "drawable", getPackageName()));
			menus.add(menu);
		}
		return menus;
	}

	/**
	 * GridView�� �����¼� 
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		final Menu menu = adapter.getItem(position);
		if(menu != null && menus.contains(menu)) {
			AlertDialog dialog = new AlertDialog.Builder(this)
										.setTitle("��ʾ")
										.setMessage("��ȷ��Ҫɾ����" + (position + 1) + "����")
										.setNegativeButton("ȡ��", null)
										.setNeutralButton("ȷ��", new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int which) {
												menus.remove(menu);
												adapter.notifyDataSetChanged();
												
											}
										})
										.create();
			dialog.show();
		}
		return true;
	}
}
