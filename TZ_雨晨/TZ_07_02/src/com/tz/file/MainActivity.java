package com.tz.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.file.adapter.FileAdapter;
import com.tz.file.bean.FileBean;

public class MainActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	private List<FileBean> mDatas;
	private ListView lv;
	private FileAdapter mAdapter;
	private File mRootSd = Environment.getExternalStorageDirectory();

	private File parentFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv_list);
		mDatas = new ArrayList<FileBean>();
		mAdapter = new FileAdapter(mDatas, this);
		lv.setAdapter(mAdapter);
		if (isCanUseSD()) {

			LoadSDTask loadSDTask = new LoadSDTask();
			loadSDTask.execute(mRootSd);
		} else {
			Toast.makeText(this, "sd卡不存在", Toast.LENGTH_SHORT).show();
		}
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
	}

	private List<FileBean> getSDFile(File file) {

		List<FileBean> list = new ArrayList<FileBean>();
		// 判断当前是否是目录
		if (file.isDirectory()) {
			// 判断是否为SD卡根目录
			if (!file.getAbsoluteFile()
					.equals(Environment.getExternalStorageDirectory()
							.getAbsoluteFile())) {
				parentFile = file;
				// 不是根目录，添加返回按钮
				FileBean bean = new FileBean("返回", R.drawable.ic_launcher, file
						.getParentFile().getAbsolutePath(), false);
				list.add(bean);
			}
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					// 目录
					FileBean bean = new FileBean(f.getName(),
							R.drawable.ic_launcher, f.getAbsolutePath(), false);
					list.add(bean);
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith("jpg")
						|| f.getName().toLowerCase().endsWith(".jpeg")) {
					// img
					FileBean bean = new FileBean(f.getName(), 0,
							f.getAbsolutePath(), true);
					list.add(bean);
				} else {
					// 文件
					FileBean bean = new FileBean(f.getName(),
							R.drawable.ic_launcher, f.getAbsolutePath(), false);
					list.add(bean);
				}
			}
		}

		return list;
	}

	/**
	 * 判断sd卡是否可用
	 * 
	 * @param @return
	 * @return boolean
	 */
	public boolean isCanUseSD() {
		try {
			return Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 加载sd卡线程任务
	 * 
	 * @Title: MainActivity.java (此处参考了 tz_oom 思路)
	 * @Package com.tz.file
	 * @Description: (todo)
	 * @author tokey
	 * @date 2015年7月28日 下午6:14:42
	 */
	class LoadSDTask extends AsyncTask<File, Void, List<FileBean>> {
		private ProgressDialog mProgressDialog;

		@Override
		protected void onPostExecute(List<FileBean> result) {
			if (mProgressDialog != null && mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			if (result != null) {
				mDatas.clear();
				mDatas.addAll(result);
				mAdapter.notifyDataSetChanged();
			}
		}

		@Override
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setTitle("加载SD卡中...");
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.show();
		}

		@Override
		protected List<FileBean> doInBackground(File... params) {
			return getSDFile(params[0]);
		}

	}

	/**
	 * Item长点击
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		FileBean bean = (FileBean) mAdapter.getItem(position);
		File file = new File(bean.getPath());

		LinearLayout popview = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		popview.setLayoutParams(lp);
		popview.setOrientation(LinearLayout.VERTICAL);
		popview.addView(getTextView("名字：" + bean.getName()));
		popview.addView(getTextView("地址：" + bean.getPath()));
		popview.addView(getTextView("大小：" + file.length() / 1024 + "kb"));
		// 添加删除按钮
		Button btn_delete = new Button(this);
		btn_delete.setText("删除");
		popview.addView(btn_delete);

		final PopupWindow popupWindow = new PopupWindow(popview, 400, 400);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
		// 使其聚焦
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 删除事件
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delete(popupWindow,position);
			}
		});
		// popupWindow.showAsDropDown(view, parent.getWidth()/2-200, 0,
		// Gravity.CENTER);
		// popupWindow.showAsDropDown(view);
		popupWindow.showAsDropDown(view, view.getWidth() / 2 - 200, 0);
		return false;
	}

	protected void delete(PopupWindow p, int position) {
		File file = new File(mDatas.get(position).getPath());
		boolean flag = false;
		if (file != null) {
			if (file.isDirectory()) {
				// 文件夹删除
				flag = deleteDicectory(file);
			} else if (file.isFile()) {
				// 文件删除
				flag = file.delete();
			}
			// 提示删除是否成功
			if (flag) {
				Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT)
						.show();
				// 刷新数据
				mDatas.remove(position);
				mAdapter.notifyDataSetChanged();
			} else {
				Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT)
						.show();
			}
			// 清除popupwidndow
			p.dismiss();
		}
	}

	/**
	 * 递归删除文件
	 * 
	 * @param file
	 */
	private boolean deleteDicectory(File file) {
		boolean flag = false;
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					if (file.isDirectory()) {
						deleteDicectory(f);
						file.delete();// 删除目录下的所有文件后，该目录变成了空目录，可直接删除
					} else if (file.isFile()) {
						file.delete();
					}
				}
			}
			flag = file.delete();// 删除最外层目录
		}
		return flag;
	}

	public View getTextView(String value) {
		TextView tv = new TextView(this);
		tv.setSingleLine();
		tv.setText(value);
		return tv;
	}

	/**
	 * Item点击
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = ((FileBean) mAdapter.getItem(position)).getPath();
		// 判断是否是目录
		File f = new File(path);
		if ((f).isDirectory()) {
			new LoadSDTask().execute(f);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断是否为SD卡根目录
			if (!parentFile.getAbsoluteFile()
					.equals(Environment.getExternalStorageDirectory()
							.getAbsoluteFile())) {
				LoadSDTask loadSDTask = new LoadSDTask();
				loadSDTask.execute(mRootSd);
			} else {
				Toast.makeText(this, "根目录了", Toast.LENGTH_SHORT).show();
			}
		}
		return false;
	}

}
