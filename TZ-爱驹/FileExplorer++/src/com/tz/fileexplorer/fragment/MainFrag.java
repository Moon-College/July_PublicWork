package com.tz.fileexplorer.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.tz.fileexplorer.MainActivity;
import com.tz.fileexplorer.R;
import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.adapter.FileRecorder;
import com.tz.fileexplorer.bean.BaseFile;
import com.tz.fileexplorer.utils.BitmapCache;
import com.tz.fileexplorer.utils.FileCompartor;
import com.tz.fileexplorer.utils.FileOpener;
import com.tz.fileexplorer.utils.FileReader;
import com.tz.fileexplorer.utils.FileReader.Type;
import com.tz.fileexplorer.utils.Suffix;

/**
 * 
 * @author wztscau
 * @since 2015年9月5日
 *
 */
public class MainFrag extends Fragment implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener, OnScrollListener {

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == MSG_SENDED) {
				pDialog.dismiss();
				listView.setAdapter(adapter);
				listView.requestFocus();
			}
		};
	};

	private List<BaseFile> list = new ArrayList<BaseFile>();
	private FileAdapter adapter;
	private ListView listView;
	private String currPath;
	private Button btnDelete;
	private File currFile;
	private PopupWindow ppWindow;
	private PopupWindow ppWindow2;
	private FileRecorder<String, Integer> recorder;
	private View parentView;
	private long prePressedTime;
	private DisplayMetrics dm;
	private ProgressDialog pDialog;
	private static final int MSG_SENDED = 1;
	private static String[][] suffixes = Suffix.suffixes;
	private boolean typeList;

	private ImageButton toTop;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main_fragment, null);
		this.parentView = v;
		return v;
	}

	private void initListView() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			String root = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			listView = (ListView) parentView.findViewById(R.id.listview);
			initData(root);
			if (listView != null) {
				listView.setOnItemClickListener(this);
				listView.setOnItemLongClickListener(this);
				listView.setOnScrollListener(this);
			}
		} else {
			Toast.makeText(getActivity(), "sdcard is not exist",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		recorder = new FileRecorder<String, Integer>();
		((MainActivity) getActivity()).getMoreButton().setOnClickListener(this);
		initListView();
		toTop = (ImageButton) parentView.findViewById(R.id.toTop);
		toTop.setOnClickListener(this);

		dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

	private DisplayMetrics getScreenResolution() {
		return dm;
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
			showDropDownItem(v);
			break;
		case R.id.toTop:
			// listView.setSelection(0);
			listView.smoothScrollToPosition(0);
			break;
		}
	}

	private void showDropDownItem(View v) {
		final String[] items = { "音乐", "视频", "图片", "文档", "压缩包", "安装包", "收藏夹",
				"蓝牙", "日志", };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.simple_dropdown_item1, items);

		ListView lView = (ListView) View.inflate(getActivity(),
				R.layout.dropdown_listview, null);
		lView.setOnItemClickListener(this);
		lView.setAdapter(adapter);
		final int ppWidth = getScreenResolution().widthPixels / 3;
		ppWindow2 = new PopupWindow(lView, ppWidth,
				WindowManager.LayoutParams.WRAP_CONTENT, true);
		ppWindow2.setBackgroundDrawable(new ColorDrawable());
		ppWindow2.showAsDropDown(v, -ppWidth * 3 / 4, 0);
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
			BaseFile mFile = new BaseFile(f.getAbsolutePath());
			// BaseFile mFile = new BaseFile();
			// System.out.println(f.getAbsolutePath());

			mFile.setName(f.getName());
			if (f.getName().trim().length() > 24) {
				String front = f.getName().substring(0, 16);
				String back = f.getName().substring(f.getName().length() - 5);
				mFile.setName(front + "..." + back);
			}
			if (f.isDirectory()) {
				File[] tmp = f.listFiles();
				if (tmp != null && tmp.length == 0) {
					mFile.setEmpty(true);
				}
			}
			mFile.setPath(f.getAbsolutePath());
			setFileIcon(f, mFile);
			list.add(mFile);
		}
		currPath = path;
		 Collections.sort(list, new FileCompartor());
		adapter = new FileAdapter(getActivity(), list);

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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.listview:
			openFile(position);
			break;
		case R.id.dropdown_listview:
			showTypeFileList(position);
			typeList = true;
			break;
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		showDelButton(view, position);
		return true;
	}

	private void openFile(int position) {
		String path = list.get(position).getPath();
		File file = new File(path);
		if (file.isDirectory()) {
			if (initData(path) == false) {
				long currTime = System.currentTimeMillis();
				// 如若5s内连续点击不可文件夹，则需要等待提示
				if (currTime - prePressedTime > 5000) {
					Toast.makeText(getActivity(), "此文件夹不可打开，如需打开，请一键root",
							Toast.LENGTH_LONG).show();
					prePressedTime = currTime;
				}
			}
		} else {
			FileOpener.openImage(this, file, null);
			FileOpener.openMusic(this, file, null);
			FileOpener.openTextFile(this, file, new String[] { "cfg" });
			FileOpener.openVideo(this, file, null);

			// FileOpener.openOtherFiles(file);
		}
	}

	private void showTypeFileList(int position) {
		switch (position) {
		case 0:
			showTypeFileList(Type.MUSIC, new String[] { "mp3" });
			break;
		case 1:
			showTypeFileList(Type.MEDIA, new String[] { ".mp4" });
			break;
		case 2:
			showTypeFileList(Type.IMAGE,
					new String[] { ".png", ".jpg", ".jpeg" });
			break;
		case 3:
			showTypeFileList(Type.DOCUMENT, new String[] { ".txt" });
			break;
		case 4:
			showTypeFileList(Type.ZIP, new String[] { ".zip", ".rar" });
			break;
		case 5:
			showTypeFileList(Type.APK, new String[] { ".apk" });
			break;
		case 6:
			showTypeFileList(Type.FAVOURITE, null);
			break;
		case 7:
			showTypeFileList(Type.BLUETOOTH, null);
			break;
		case 8:
			showTypeFileList(Type.LOG, new String[] { "log" });
			break;
		}
	}

	@SuppressLint("NewApi")
	private void showTypeFileList(final Type t, final String[] s) {
		final File[] listFiles = Environment.getExternalStorageDirectory()
				.listFiles();
		// list = FileReader.readFile(getActivity(), listFiles, list);
		// adapter = new FileAdapter(getActivity(),
		// FileReader.readFile(getActivity(), listFiles, list));
		// adapter.notifyDataSetChanged();
		// listView.setAdapter(adapter);
		pDialog = new ProgressDialog(getActivity());
		pDialog.setCancelable(false);
		pDialog.show();
		ppWindow2.dismiss();
		new Thread(new Runnable() {

			@Override
			public void run() {
				list.clear();
				// 花了11s才完成，亟需改进
				long start = System.currentTimeMillis();
				List<BaseFile> readFile = FileReader.readFile(getActivity(),
						listFiles, list, t, s);
				long end = System.currentTimeMillis();
				System.out.println(t + " 所花时间：" + (end - start) / 1000.0 + "s");
				adapter = new FileAdapter(getActivity(), readFile);
				Message msg = Message.obtain();
				msg.what = MSG_SENDED;
				handler.sendMessage(msg);
			}
		}).start();
	}

	@SuppressLint("InflateParams")
	private void showDelButton(View view, int position) {
		currFile = (BaseFile) adapter.getItem(position);
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
		// btnDelete.getLocationInWindow(location);//这个不行
		view.getLocationOnScreen(loca);
		// 泡泡窗口的位置为所选item的正上方
		ppWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL | Gravity.TOP,
				0, loca[1] - view.getHeight());
	}

	/**
	 * 
	 * @param f
	 *            真实文件/文件夹
	 * @param mFile
	 *            自定义文件
	 */
	private void setFileIcon(File f, BaseFile mFile) {
		if (f.isDirectory()) {
			mFile.setIcon(BitmapCache
					.getBitmap(getResources(), R.drawable.dirs));
			mFile.setIcon(true);
		} else if (f.isFile()) {
			String name = f.getName().toLowerCase(Locale.US);
			boolean breakOuter = false;
			// 先初始化所有文件Icon为R.drawable.file
			mFile.setIcon(BitmapCache
					.getBitmap(getResources(), R.drawable.file));
			int[] drawables = {
				R.drawable.music,R.drawable.media,-1,R.drawable.document,
				R.drawable.zip,R.drawable.emo_im_surprised,0,0,0,
			};
			for (int i = 0; i < suffixes.length; i++) {
				// TODO Auto-generated method stub
				// 这里亟待改进
				for(int j=0;j<drawables.length;j++){
					noName(i, j, name, mFile, drawables[j], suffixes);
				}
			}
		}
	}

	private boolean noName(int i, int index, String name, BaseFile mFile,
			int resId, String[][] suffixes) {
		if (i == index) {
			for (int j = 0; j < suffixes[i].length; j++) {
				if (name.endsWith(suffixes[i][j])) {
					if (resId > 0)
						mFile.setIcon(BitmapCache.getBitmap(getResources(),
								resId));
					else {
						mFile.setIcon(null);
					}
					return true;
				}
			}
		}
		return false;
	}

	public void onBackPressed() {
		File file = new File(currPath);
		if (file.getParent() != null) {
			if (typeList) {
				initData(currPath);
				typeList = false;
			} else {
				initData(file.getParent());
			}
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
	private void showDialog(final File mFile) {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setTitle("Warning")
				.setMessage(
						"Do you wish to delete file\n["
								+ new File(mFile.getPath()).getAbsolutePath()
								+ "]\n[" + mFile.getName() + "]?")
				.setPositiveButton("yes sure",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								deleteFile(currFile);
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
	private void deleteFile(File mFile) {
		list.remove(mFile);
		adapter.notifyDataSetChanged();
		Toast.makeText(getActivity(), "已删除文件 " + mFile.getName(),
				Toast.LENGTH_SHORT).show();
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
