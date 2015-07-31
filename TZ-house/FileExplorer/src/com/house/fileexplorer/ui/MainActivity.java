package com.house.fileexplorer.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.house.fileexplorer.R;
import com.house.fileexplorer.adapter.FileAdapter;
import com.house.fileexplorer.bean.MyFileResponse;
import com.house.fileexplorer.util.FileUtils;
import com.house.fileexplorer.view.FilePopupWindow;
import com.house.fileexplorer.view.FilePopupWindow.OnPopupClickListener;

public class MainActivity extends Activity {

	private List<MyFileResponse> fileList = new ArrayList<MyFileResponse>();
	// 文件路径
	private String path;

	private ListView listView;
	private FileAdapter fileAdapter;

	private Context mContext;
	// 实例化文件夹图标
	private Bitmap dirsIcon;
	// 实例化其他类型文件图标
	private Bitmap fileIcon;
	
	private FilePopupWindow popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		
		// 判断SD卡是否正常挂在
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 得到sd卡根目录
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			Toast.makeText(this, "SD Error", 0).show();
		}

		initView();
	}

	/**
	 * 初始化UI控件
	 */
	private void initView() {
		// 创建化文件夹图标
		dirsIcon = BitmapFactory
				.decodeResource(getResources(), R.drawable.dirs);
		// 创建其他类型文件图标
		fileIcon = BitmapFactory
				.decodeResource(getResources(), R.drawable.file);

		listView = (ListView) findViewById(R.id.fileListView);

		fileAdapter = new FileAdapter(fileList, mContext);
		listView.setAdapter(fileAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				File file = new File(fileList.get(position).getPath());
				if (file.isDirectory()) {
					initData(file.getAbsolutePath());
				}
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				popup.showPopup(view, position);
				popup.setClick(new OnPopupClickListener() {
					
					@Override
					public void details(View v, int position) {
						popup.dismiss();
						File file = new File(fileList.get(position).getPath());
						Toast.makeText(mContext, FileUtils.getFormatSize(FileUtils.getFileSize(file)), 0).show();
					}
					
					@Override
					public void delete(View v, int position) {
						popup.dismiss();
						FileUtils.deleteFile(fileList.get(position).getPath());
						fileList.remove(position);
						fileAdapter.notifyDataSetChanged();
					}
				});
				return true;
			}
		});
		popup = new FilePopupWindow(mContext);
		initData(path);
	}

	/**
	 * 遍历SD卡并初始化数据
	 * 
	 * @param path
	 *            路径
	 */
	private void initData(String path) {
		fileList.clear();
		// 遍历SD卡
		File file = new File(path);
		File[] listFiles = file.listFiles();
		MyFileResponse response = new MyFileResponse("返回", dirsIcon, file
				.getParentFile().getAbsolutePath(), false);
		// 将返回添加到listView的顶部
		fileList.add(response);
		// 遍历文件夹
		for (File f : listFiles) {
			MyFileResponse fileResponse = new MyFileResponse();
			fileResponse.setName(f.getName());
			fileResponse.setPath(f.getAbsolutePath());
			if (f.isDirectory()) {// 是文件夹
				fileResponse.setIcon(false);
				fileResponse.setIcon(dirsIcon);
			} else {// 文件
				// 图片
				if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					fileResponse.setIcon(true);
					fileResponse.setIcon(null);
				} else {// 其他文件
					fileResponse.setIcon(false);
					fileResponse.setIcon(fileIcon);
				}
			}
			fileList.add(fileResponse);
		}
		fileAdapter.notifyDataSetChanged();
	}
}
