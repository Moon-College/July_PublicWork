package com.tz.fileexplorer.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tz.fileexplorer.MainActivity;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.adapter.FileRecorder;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.utils.FileOpener;

public class MainFrag extends Fragment implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener, OnScrollListener {

	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	private ListView listView;
	private String currPath;
	private Button btnDelete;
	private File currFile;
	private MyFile mFile;
	private PopupWindow ppWindow;
	private FileRecorder<String, Integer> recorder;
	private View contentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main_fragment, null);
		this.contentView = v;
		return v;
	}

	private void initListView() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			String root = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			initData(root);
			if (listView != null) {
				listView.setOnItemClickListener(this);
				listView.setOnItemLongClickListener(this);
				listView.setOnScrollListener(this);
			}
		} else {
			Toast.makeText(getActivity(), "sdcard is not exist", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		recorder = new FileRecorder<String, Integer>();
		((MainActivity) getActivity()).getMoreButton().setOnClickListener(this);
		initListView();
	}

	/**
	 * 监听事件
	 */
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_del:
			showDialog(currFile);
			ppWindow.dismiss();
			break;
		case R.id.btn_more:
			System.out.println("ontouch");
			 showDropDownItem(v);
			break;

		}
	}

	private void showDropDownItem(View v) {
		final String[] items = { "音乐", "视频", "图片", "文档", "压缩包", "安装包", "收藏夹",
				"蓝牙", "日志", };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, items);

		ListView lView = (ListView) View.inflate(getActivity(),
				R.layout.dropdown_listview, null);
		System.out.println(lView);
		lView.setAdapter(adapter);
		PopupWindow ppWindow = new PopupWindow(lView,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT, true);
		ppWindow.setBackgroundDrawable(new ColorDrawable());
		ppWindow.showAsDropDown(v, 0, 0);
	}

	private boolean initData(String path) {
		File dirs = new File(path);
		final File[] files = dirs.listFiles();
		if (files == null) {
			return false;
		}
		list.clear();
		Arrays.sort(files);
		for (File f : files) {
			MyFile mFile = new MyFile();

			mFile.setName(f.getName());
			if (f.getName().trim().length() > 24) {
				String front = f.getName().substring(0, 16);
				String back = f.getName().substring(f.getName().length() - 5);
				mFile.setName(front + "..." + back);
			}
			mFile.setPath(f.getAbsolutePath());
			setFileIcon(f, mFile);
			list.add(mFile);
		}
		currPath = path;
		adapter = new FileAdapter(getActivity(), list);
		listView = (ListView) contentView.findViewById(R.id.listview);
		listView.setAdapter(adapter);
		Integer position = recorder.get(path);
		if (position == null) {
			position = 0;
		} else {
			adapter.notifyDataSetChanged();
			listView.setSelection(position);
		}
		return true;
	}

	private long prePressedTime;

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = list.get(position).getPath();
		File file = new File(path);
		if (file.isDirectory()) {
			if (initData(path) == false) {
				long currTime = System.currentTimeMillis();
				// 如若5s内连续点击不可文件夹，则需要等待提示
				if (currTime - prePressedTime > 5000) {
					Toast.makeText(getActivity(), "此文件夹不可打开，如需打开，请一键root", Toast.LENGTH_LONG)
							.show();
					prePressedTime = currTime;
				}
			}
		} else {
			FileOpener.openImage(this, file, null);
			FileOpener.openMusic(this, file, null);
			FileOpener.openTextFile(this, file, new String[] { "cfg" });
			// FileOpener.openOtherFiles(file);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		showPPWindow(view);
		return true;
	}

	@SuppressLint("InflateParams")
	private void showPPWindow(View view) {
		// TODO Auto-generated method stub
		View contentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.popup_window, null);
		btnDelete = (Button) contentView.findViewById(R.id.btn_del);
		btnDelete.setOnClickListener(this);
		ppWindow = new PopupWindow(contentView,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT, true);
		// 设置泡泡窗口透明背景
		ppWindow.setBackgroundDrawable(new ColorDrawable());

		// ppWindow.showAsDropDown(view, x, y);
		int loca[] = new int[2];
		// btnDelete.getLocationInWindow(loca);//这个不行
		view.getLocationOnScreen(loca);
		// 泡泡窗口的位置为所选item的正上方
		ppWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL | Gravity.TOP,
				loca[0], loca[1] - view.getHeight());
	}
	
	/**
	 * 
	 * @param f
	 *            真实文件/文件夹
	 * @param mFile
	 *            自定义文件
	 */
	private void setFileIcon(File f, MyFile mFile) {
		if (f.isDirectory()) {
			mFile.setIcon(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			mFile.setIcon(true);
		} else if (f.isFile()) {
			String name = f.getName().toLowerCase(Locale.US);
			if (name.endsWith("png") || name.endsWith("jpg")
					|| name.endsWith("jpeg")) {
				mFile.setIcon(null);
				mFile.setIcon(false);
			} else {
				mFile.setIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.file));
			}
		}
	}

	public void onBackPressed() {
		File file = new File(currPath);
		if (file.getParent() != null) {
			initData(file.getParent());
		} else {
			getActivity().finish();
		}
	}

	/**
	 * 弹出对话框
	 * 
	 * @param mFile
	 *            自定义文件
	 */
	public void showDialog(final File mFile) {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setTitle("Warning")
				.setMessage(
						"Do you wish to delete file\n[" + mFile.getName()
								+ "]?")
				.setPositiveButton("yes sure",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								deleteMyFile(mFile.getPath());
							}

						})
				.setNegativeButton("no thanks",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
							}
						});
		builder.create().show();
		btnDelete.setVisibility(View.INVISIBLE);
	}

	/**
	 * 并非真的删除文件，而是删除列表中的项目
	 * 
	 * @param path
	 */
	private void deleteMyFile(String path) {
		list.remove(mFile);
		adapter.notifyDataSetChanged();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// 记录listview滑动到的条目
		recorder.put(currPath, firstVisibleItem);
	}
}
