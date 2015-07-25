package com.example.tz_kevin_homework5_20150724;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.string;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.adapter.StudentsAdapter;
import com.example.entity.Students;

/**
 * 
 * @author kevin.li
 * @version 1.0.0
 * @create 20150725
 * @function ����ҳ�� ����
 */
public class MenuActivity extends BaseActivity implements
		OnItemLongClickListener {
	// ͷ����Ϣ
	private int[] imvMenu = { R.drawable.menu0, R.drawable.menu1,
			R.drawable.menu2, R.drawable.menu3, R.drawable.menu4,
			R.drawable.menu5, R.drawable.menu7, R.drawable.menu8, 
			R.drawable.menu3, R.drawable.menu4,R.drawable.menu7,
			R.drawable.menu3, R.drawable.menu4,R.drawable.menu7};
	// ������
	private SimpleAdapter mAdapter;
	// ����
	private List<Map<String, Object>> mData;
	// gv��ʾ����
	private GridView gv;
	private final String key_imv = "imv";
	private final String key_name = "name";
	private final String key_info = "info";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initViews();
		initData();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		setupTopBaseView(getString(R.string.login_btn_menu), true);
		gv = (GridView) findViewById(R.id.gv_menu);
	}

	private void initData() {
		// TODO Auto-generated method stub
		initListData();

		mAdapter = new SimpleAdapter(
				this, 
				mData, 
				R.layout.activity_menu_item,
				new String[] { key_imv,key_name,key_info },
				new int[] { R.id.item_imv_menu,
						R.id.item_tv_menuName, R.id.item_tv_menuInfo });

		gv.setAdapter(mAdapter);
		gv.setOnItemLongClickListener(this);
	}

	/**
	 * ��ʼ��list
	 */
	private void initListData() {
		mData = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (int i = 0; i < imvMenu.length; i++) {
			map = new HashMap<String, Object>();
			map.put(key_imv, imvMenu[i]);
			map.put(key_name, "����"+(i+1));
			map.put(key_info, "�ĸ�����Ĳˣ��ǳ��ɿڰ���");
			mData.add(map);
		}

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		final int index = arg2;

		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setMessage("��ȷ��Ҫ������");
		dialog.setTitle("��ʾ");
		dialog.setNegativeButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				mData.remove(index);
				mAdapter.notifyDataSetChanged();

			}
		});
		dialog.setCancelable(false);
		dialog.show();
		return false;
	}
}
