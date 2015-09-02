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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tz.fileexplorer.MainActivity;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.adapter.FileRecorder;
import com.tz.fileexplorer.bean.MyFile;

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
			Toast.makeText(getActivity(), "sdcard is not exist", 0).show();
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
		}
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
		String fileName = file.getName();
		if (file.isDirectory()) {
			if (initData(path) == false) {
				long currTime = System.currentTimeMillis();
				// 如若5s内连续点击不可文件夹，则需要等待提示
				if (currTime - prePressedTime > 5000) {
					Toast.makeText(getActivity(), "此文件夹不可打开，如需打开，请一键root", 1)
							.show();
					prePressedTime = currTime;
				}
			}
		} else if (fileName.endsWith("txt") || fileName.endsWith("log")
				|| fileName.endsWith("xml")) {
			openTextFile(file);
		} else if (fileName.endsWith("jpg") || fileName.endsWith("jpeg")
				|| fileName.endsWith("png")) {
			openImage(file);
		} else {
			// openOtherFiles(file);
		}

	}

	private void openOtherFiles(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CHOOSER);
		startActivity(intent);

	}

	private void openImage(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setDataAndType(Uri.fromFile(file), "image/*");
		startActivity(intent);
	}

	private void openTextFile(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		Uri data = Uri.fromFile(file);
		intent.setData(data);
		startActivity(intent);
	}

	@SuppressLint("InflateParams")
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		View contentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.popup_window, null);
		mFile = list.get(position);
		currFile = new File(mFile.getPath());
		btnDelete = (Button) contentView.findViewById(R.id.btn_del);
		btnDelete.setOnClickListener(this);
		ppWindow = new PopupWindow(contentView,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT, true);
		// 设置泡泡窗口透明背景
		ppWindow.setBackgroundDrawable(new ColorDrawable());
		
		int x = view.getWidth() / 2 ;
		int y = -view.getHeight() * 2;

		// 泡泡窗口的位置为所选item的下方,靠右侧的位置
		ppWindow.showAsDropDown(view, x, y);
		return true;
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
