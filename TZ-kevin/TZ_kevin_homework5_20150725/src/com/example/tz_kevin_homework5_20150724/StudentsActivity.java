package com.example.tz_kevin_homework5_20150724;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.StudentsAdapter;
import com.example.entity.Students;

/**
 * 
 * @author kevin.li
 * @version 1.0.0
 * @create 20150724
 * @function 学生信息页面
 */
public class StudentsActivity extends BaseActivity implements
		OnItemClickListener {
	// 头像信息
	private int[] imvHead = { R.drawable.pic_0, R.drawable.pic_1,
			R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
			R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7,
			R.drawable.pic_8, R.drawable.pic_9, };
	// 适配器
	private StudentsAdapter mAdapter;
	// 数据
	private List<Students> list;
	// lv显示数据
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		initViews();
		initData();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		setupTopBaseView(getString(R.string.login_btn_students), true);
		lv = (ListView) findViewById(R.id.lv_ss);
	}

	private void initData() {
		// TODO Auto-generated method stub
		initListData();
		mAdapter = new StudentsAdapter(this, list);

		lv.setAdapter(mAdapter);
		lv.setOnItemClickListener(this);
	}

	/**
	 * 初始化list
	 */
	private void initListData() {
		list = new ArrayList<Students>();
		Students students = null;
		for (int i = 0; i < imvHead.length; i++) {
			students = new Students();
			students.setHead(imvHead[i]);
			students.setScreenNames("潭州" + (i + 1) + "号大师");
			students.setSex(Math.random() > 0.5 ? "1" : "0");
			students.setHobby("学习");
			students.setFaceScore((200 - i * 7) + "");
			list.add(students);

		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		final int index = position;

		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setMessage("您确定要删除" + list.get(position).getScreenNames() + "吗？");
		dialog.setTitle("提示");
		dialog.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				list.remove(index);
				mAdapter.notifyDataSetChanged();

			}
		});
		dialog.setCancelable(false);
		dialog.show();

	}
}
