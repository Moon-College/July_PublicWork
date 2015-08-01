package com.example.fileExplorer.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fileExplorer.R;
import com.example.fileExplorer.adapter.MyFileAdapter;
import com.example.fileExplorer.adapter.MyFileAdapter.OnItemButtonClickListener;
import com.example.fileExplorer.bean.MyFile;
import com.example.fileExplorer.utils.SDCardUtils;
import com.example.fileExplorer.utils.ScreenUtils;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function SD���ļ����ҳ��
 */

/**
 * @author kevin.li
 * @version 1.0.1
 * @create 20150730
 * @describe 1.�޸���bug����file��·��Ϊ��·��ʱ�� �������  ����ָ���쳣 nullPointerExp ��ô ��Ҫ��
 *           λ��ΪrootPath ʱ���� ����һ���㼶�� 
 */

/**
 * @author kevin.li
 * @version 1.0.2
 * @create 20150731
 * @describe 1.����ұ� �鿴���� ��鿴��ϸ����Ϣ popuwindow��ʹ�� 
 * 			 2.����item ��ɾ������Ŀ��ע���������ʾɾ������������ɾ��
 */
public class MyFileExplorerActivity extends ListActivity implements
		OnItemClickListener, OnItemLongClickListener, OnItemButtonClickListener {

	// ����Դ
	private List<MyFile> mData;
	// ������
	private MyFileAdapter mAdapter;
	// ��·��
	private String rootPath;
	// ��·����һ�㼶
	private String parentPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfile);
		initView();
		initData();
	}

	private void initView() {
		// TODO Auto-generated method stub
	}

	private void initData() {
		// TODO Auto-generated method stub
		rootPath = SDCardUtils.getRootFilePath(this);
		mData = new ArrayList<MyFile>();
		initFileData(rootPath);
		mAdapter = new MyFileAdapter(this, mData);
		setListAdapter(mAdapter);
		getListView().setOnItemClickListener(this);
		getListView().setOnItemLongClickListener(this);
		mAdapter.setOnItemButtonClickListener(this);
	}

	/**
	 * �������
	 */
	private void clearData() {
		// �����һ������ ���������
		if (null != mData || mData.size() != 0) {
			mData.removeAll(mData);
			mData.clear();

		}
	}

	/**
	 * ��ʼ���ļ�����
	 * 
	 * @param path
	 */
	private void initFileData(String path) {
		// TODO Auto-generated method stub
		clearData();

		try {
			File file = new File(path);
			MyFile backFile = new MyFile(BitmapFactory.decodeResource(
					getResources(), R.drawable.dirs), "����", file
					.getParentFile().getAbsolutePath(), false);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar calendar = Calendar.getInstance();
			backFile.setFileTime(sdf.format(new Date(calendar.getTimeInMillis())));
			mData.add(0, backFile); // ������ذ�ť ��Ҫ������һ��Ŀ¼�㼶

			File[] subFiles = file.listFiles();

			MyFile myFile = null;
			for (int i = 0; i < subFiles.length; i++) {
				myFile = new MyFile();
				myFile.setFileName(subFiles[i].getName());
				myFile.setFilePath(subFiles[i].getAbsolutePath());
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				myFile.setFileTime(sdf1.format(new Date(subFiles[i]
						.lastModified())));

				if (subFiles[i].isDirectory()) {// �����·���Ļ� ��ô��������������
					myFile.setFileIcon(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
					myFile.setPicture(false);
				} else {// ����·�� ��ô�п������ļ�����ͼƬ
					if (subFiles[i].getName().toLowerCase().endsWith(".png")
							|| subFiles[i].getName().toLowerCase()
									.endsWith(".jpg")
							|| subFiles[i].getName().toLowerCase()
									.endsWith(".jpeg")) {// �����ͼƬ
						myFile.setPicture(true);
						myFile.setFileIcon(null);
					} else { // ����ļ�
						myFile.setFileIcon(BitmapFactory.decodeResource(
								getResources(), R.drawable.file));
						myFile.setPicture(false);
					}

				}

				mData.add(myFile);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * ���Ŀ¼����ת
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ��file��·��Ϊ��·��ʱ�� ������� ����ָ���쳣 nullPointerExp
		parentPath = new File(rootPath).getParentFile().getAbsolutePath();
		MyFile myFile = (MyFile) mAdapter.getItem(position);
		String path = myFile.getFilePath();
		File file = new File(path);
		if (position == 0) { // ��һ���Ƿ���
			if (path.equals(parentPath)) {
				System.out.println(">>>>>>path:" + path + ">>>>>>parentPath:"
						+ parentPath);
				Toast.makeText(this, "�Ѿ������ϲ�Ŀ¼��...", 0).show();
				return;
			}
		}
		if (file.isDirectory()) {// �����·�� ��ô
			initFileData(file.getAbsolutePath());
			mAdapter.notifyDataSetChanged();
		}

	}

	/**
	 * ����ɾ�� ����
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		final int index = position;
		MyFile myFile = mData.get(position);
		File file = new File(myFile.getFilePath());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		@SuppressWarnings("unused")
		Dialog dialog = builder.create();
		// ��Ҫ�ж�һ�� ����һ���������ʾ
		if (file.isDirectory()) {
			builder.setMessage("ȷ��Ҫɾ�� " + myFile.getFileName() + " �ļ��У�");
		} else {
			builder.setMessage("ȷ��Ҫɾ�� " + myFile.getFileName() + " �ļ���");
		}

		builder.setTitle("��ʾ").setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mData.remove(index);
				mAdapter.notifyDataSetChanged();

			}
		}).setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).show();

		return true;
	}

	/**
	 * �����ϸ��Ϣ��ť ����
	 */
	@Override
	public void onClick(View v, int position, long id) {
		// TODO Auto-generated method stub
		MyFile myFile = mData.get(position);
		View contentView = View.inflate(this, R.layout.popup, null);
		TextView tvName = (TextView) contentView.findViewById(R.id.tv_name);
		TextView tvPath = (TextView) contentView.findViewById(R.id.tv_path);
		TextView tvTimeSharp = (TextView) contentView
				.findViewById(R.id.tv_timesharp);
		Button btnOK = (Button) contentView.findViewById(R.id.btn_ok);
		final PopupWindow pw = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);// focusableһ��Ҫ����Ϊtrue
																			// ��ȡ����
		pw.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.local_popup_bg));

		tvName.setText("���ƣ� " + myFile.getFileName());
		tvPath.setText("·���� " + myFile.getFilePath());
		tvTimeSharp.setText("ʱ����� " + myFile.getFileTime());
		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pw.dismiss();
			}
		});
		// ������ָ����λ��
		int[] location = new int[2];
		v.getLocationInWindow(location);
		pw.showAtLocation(v, Gravity.CENTER, 0, ScreenUtils.getSrennSize(this)
				.get(ScreenUtils.HEIGHT) / 8);
	}

}
