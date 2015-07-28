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
 * @function ѧ����Ϣҳ��
 */
public class StudentsActivity extends BaseActivity implements
		OnItemClickListener {
	// ͷ����Ϣ
	private int[] imvHead = { R.drawable.pic_0, R.drawable.pic_1,
			R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
			R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7,
			R.drawable.pic_8, R.drawable.pic_9, };
	// ������
	private StudentsAdapter mAdapter;
	// ����
	private List<Students> list;
	// lv��ʾ����
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
	 * ��ʼ��list
	 */
	private void initListData() {
		list = new ArrayList<Students>();
		Students students = null;
		for (int i = 0; i < imvHead.length; i++) {
			students = new Students();
			students.setHead(imvHead[i]);
			students.setScreenNames("̶��" + (i + 1) + "�Ŵ�ʦ");
			students.setSex(Math.random() > 0.5 ? "1" : "0");
			students.setHobby("ѧϰ");
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
		dialog.setMessage("��ȷ��Ҫɾ��" + list.get(position).getScreenNames() + "��");
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

				list.remove(index);
				mAdapter.notifyDataSetChanged();

			}
		});
		dialog.setCancelable(false);
		dialog.show();

	}
}
