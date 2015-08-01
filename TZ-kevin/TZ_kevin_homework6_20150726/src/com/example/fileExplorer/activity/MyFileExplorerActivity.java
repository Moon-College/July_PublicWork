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
 * @function SD卡文件浏览页面
 */

public class MyFileExplorerActivity extends ListActivity implements
		OnItemClickListener {
	// 文本
	private TextView tvFileInfo;
	// 数据源
	private List<MyFile> mData;
	// 适配器
	private MyFileAdapter mAdapter;
	// 根路径
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
	 * 清空数据
	 */
	private void clearData() {
		// 清空上一次数据 不让其叠加
		if (null != mData || mData.size() != 0) {
			mData.removeAll(mData);
			mData.clear();

		}
	}

	/**
	 * 初始化文件数据
	 * 
	 * @param path
	 */
	private void initFileData(String path) {
		// TODO Auto-generated method stub
		clearData();
		MyFile backFile = new MyFile(BitmapFactory.decodeResource(
				getResources(), R.drawable.dirs), "返回", rootPath, false);
		mData.add(0, backFile);

		File file = new File(path);
		File[] subFiles = file.listFiles();

		MyFile myFile = null;
		for (int i = 0; i < subFiles.length; i++) {
			myFile = new MyFile();
			myFile.setFileName(subFiles[i].getName());
			myFile.setFilePath(subFiles[i].getAbsolutePath());

			if (subFiles[i].isDirectory()) {// 如果是路径的话 那么给其设置其属性
				myFile.setFileIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs));
				myFile.setPicture(false);
			} else {// 不是路径 那么有可能是文件或者图片
				if (subFiles[i].getName().toLowerCase().endsWith(".png")
						|| subFiles[i].getName().toLowerCase().endsWith(".jpg")
						|| subFiles[i].getName().toLowerCase()
								.endsWith(".jpeg")) {// 如果是图片
					myFile.setPicture(true);

				} else { // 如果文件
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

		if (file.isDirectory()) {// 如果是路径 那么
			initFileData(file.getAbsolutePath());
			mAdapter.notifyDataSetChanged();
		}

	}
}
