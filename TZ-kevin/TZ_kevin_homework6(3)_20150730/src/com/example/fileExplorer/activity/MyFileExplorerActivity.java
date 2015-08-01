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
 * @function SD卡文件浏览页面
 */

/**
 * @author kevin.li
 * @version 1.0.1
 * @create 20150730
 * @describe 1.修复了bug，当file的路径为根路径时候 点击返回  报空指针异常 nullPointerExp 那么 需要在
 *           位置为rootPath 时候不让 往下一个层级了 
 */

/**
 * @author kevin.li
 * @version 1.0.2
 * @create 20150731
 * @describe 1.点击右边 查看详情 会查看详细的信息 popuwindow的使用 
 * 			 2.长按item 会删除该项目，注意仅仅是显示删除而不是物理删除
 */
public class MyFileExplorerActivity extends ListActivity implements
		OnItemClickListener, OnItemLongClickListener, OnItemButtonClickListener {

	// 数据源
	private List<MyFile> mData;
	// 适配器
	private MyFileAdapter mAdapter;
	// 根路径
	private String rootPath;
	// 跟路径上一层级
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

		try {
			File file = new File(path);
			MyFile backFile = new MyFile(BitmapFactory.decodeResource(
					getResources(), R.drawable.dirs), "返回", file
					.getParentFile().getAbsolutePath(), false);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar calendar = Calendar.getInstance();
			backFile.setFileTime(sdf.format(new Date(calendar.getTimeInMillis())));
			mData.add(0, backFile); // 点击返回按钮 需要返回上一个目录层级

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

				if (subFiles[i].isDirectory()) {// 如果是路径的话 那么给其设置其属性
					myFile.setFileIcon(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
					myFile.setPicture(false);
				} else {// 不是路径 那么有可能是文件或者图片
					if (subFiles[i].getName().toLowerCase().endsWith(".png")
							|| subFiles[i].getName().toLowerCase()
									.endsWith(".jpg")
							|| subFiles[i].getName().toLowerCase()
									.endsWith(".jpeg")) {// 如果是图片
						myFile.setPicture(true);
						myFile.setFileIcon(null);
					} else { // 如果文件
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
	 * 点击目录的跳转
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 当file的路径为根路径时候 点击返回 报空指针异常 nullPointerExp
		parentPath = new File(rootPath).getParentFile().getAbsolutePath();
		MyFile myFile = (MyFile) mAdapter.getItem(position);
		String path = myFile.getFilePath();
		File file = new File(path);
		if (position == 0) { // 这一项是返回
			if (path.equals(parentPath)) {
				System.out.println(">>>>>>path:" + path + ">>>>>>parentPath:"
						+ parentPath);
				Toast.makeText(this, "已经是最上层目录了...", 0).show();
				return;
			}
		}
		if (file.isDirectory()) {// 如果是路径 那么
			initFileData(file.getAbsolutePath());
			mAdapter.notifyDataSetChanged();
		}

	}

	/**
	 * 长按删除 监听
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
		// 需要判断一下 给出一个合理的提示
		if (file.isDirectory()) {
			builder.setMessage("确定要删除 " + myFile.getFileName() + " 文件夹？");
		} else {
			builder.setMessage("确定要删除 " + myFile.getFileName() + " 文件？");
		}

		builder.setTitle("提示").setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mData.remove(index);
				mAdapter.notifyDataSetChanged();

			}
		}).setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).show();

		return true;
	}

	/**
	 * 点击详细信息按钮 监听
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
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);// focusable一定要设置为true
																			// 获取焦点
		pw.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.local_popup_bg));

		tvName.setText("名称： " + myFile.getFileName());
		tvPath.setText("路径： " + myFile.getFilePath());
		tvTimeSharp.setText("时间戳： " + myFile.getFileTime());
		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pw.dismiss();
			}
		});
		// 弹出到指定的位置
		int[] location = new int[2];
		v.getLocationInWindow(location);
		pw.showAtLocation(v, Gravity.CENTER, 0, ScreenUtils.getSrennSize(this)
				.get(ScreenUtils.HEIGHT) / 8);
	}

}
