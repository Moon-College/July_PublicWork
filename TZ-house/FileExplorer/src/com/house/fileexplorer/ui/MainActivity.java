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
	// �ļ�·��
	private String path;

	private ListView listView;
	private FileAdapter fileAdapter;

	private Context mContext;
	// ʵ�����ļ���ͼ��
	private Bitmap dirsIcon;
	// ʵ�������������ļ�ͼ��
	private Bitmap fileIcon;
	
	private FilePopupWindow popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		
		// �ж�SD���Ƿ���������
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// �õ�sd����Ŀ¼
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			Toast.makeText(this, "SD Error", 0).show();
		}

		initView();
	}

	/**
	 * ��ʼ��UI�ؼ�
	 */
	private void initView() {
		// �������ļ���ͼ��
		dirsIcon = BitmapFactory
				.decodeResource(getResources(), R.drawable.dirs);
		// �������������ļ�ͼ��
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
	 * ����SD������ʼ������
	 * 
	 * @param path
	 *            ·��
	 */
	private void initData(String path) {
		fileList.clear();
		// ����SD��
		File file = new File(path);
		File[] listFiles = file.listFiles();
		MyFileResponse response = new MyFileResponse("����", dirsIcon, file
				.getParentFile().getAbsolutePath(), false);
		// ��������ӵ�listView�Ķ���
		fileList.add(response);
		// �����ļ���
		for (File f : listFiles) {
			MyFileResponse fileResponse = new MyFileResponse();
			fileResponse.setName(f.getName());
			fileResponse.setPath(f.getAbsolutePath());
			if (f.isDirectory()) {// ���ļ���
				fileResponse.setIcon(false);
				fileResponse.setIcon(dirsIcon);
			} else {// �ļ�
				// ͼƬ
				if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					fileResponse.setIcon(true);
					fileResponse.setIcon(null);
				} else {// �����ļ�
					fileResponse.setIcon(false);
					fileResponse.setIcon(fileIcon);
				}
			}
			fileList.add(fileResponse);
		}
		fileAdapter.notifyDataSetChanged();
	}
}
