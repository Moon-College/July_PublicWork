package com.example.fileExplorer.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.fileExplorer.R;
import com.example.fileExplorer.adapter.MyFileAdapter;
import com.example.fileExplorer.bean.MyFile;
import com.example.fileExplorer.utils.SDCardUtils;

/**
 * @author kevin.li
 * @version 1.0.0
 * @create 20150727
 * @function SD���ļ����ҳ��
 */

public class MyFileExplorerActivity extends ListActivity implements
		OnItemClickListener {
	// �ı�
	private TextView tvFileInfo;
	// ����Դ
	private List<MyFile> mData;
	// ������
	private MyFileAdapter mAdapter;
	// ��·��
	private String rootPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfile);
		initView();
		initData();
	}

	private void initView() {
		// TODO Auto-generated method stub
		tvFileInfo = (TextView) findViewById(R.id.tv_fileInfo);
	}

	private void initData() {
		// TODO Auto-generated method stub
		rootPath = SDCardUtils.getRootFilePath(this);
		mData = new ArrayList<MyFile>();
		initFileData(rootPath);
		mAdapter = new MyFileAdapter(this, mData);
		setListAdapter(mAdapter);
		getListView().setOnItemClickListener(this);
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
		MyFile backFile = new MyFile(BitmapFactory.decodeResource(
				getResources(), R.drawable.dirs), "����", rootPath, false);
		mData.add(0, backFile);

		File file = new File(path);
		File[] subFiles = file.listFiles();

		MyFile myFile = null;
		for (int i = 0; i < subFiles.length; i++) {
			myFile = new MyFile();
			myFile.setFileName(subFiles[i].getName());
			myFile.setFilePath(subFiles[i].getAbsolutePath());

			if (subFiles[i].isDirectory()) {// �����·���Ļ� ��ô��������������
				myFile.setFileIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs));
				myFile.setPicture(false);
			} else {// ����·�� ��ô�п������ļ�����ͼƬ
				if (subFiles[i].getName().toLowerCase().endsWith(".png")
						|| subFiles[i].getName().toLowerCase().endsWith(".jpg")
						|| subFiles[i].getName().toLowerCase()
								.endsWith(".jpeg")) {// �����ͼƬ
					myFile.setPicture(true);

				} else { // ����ļ�
					myFile.setFileIcon(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
					myFile.setPicture(false);
				}

			}

			mData.add(myFile);

		}

	}

	//
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyFile myFile = (MyFile) mAdapter.getItem(position);
		String path = myFile.getFilePath();
		File file = new File(path);

		if (file.isDirectory()) {// �����·�� ��ô
			initFileData(file.getAbsolutePath());
			mAdapter.notifyDataSetChanged();
		}

	}
}
