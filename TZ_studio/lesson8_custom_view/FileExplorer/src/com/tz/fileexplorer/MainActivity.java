package com.tz.fileexplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.FileBean;
import com.tz.fileexplorer.utils.FileUtils;
import com.tz.fileexplorer.utils.PopuWindowUtils;
import com.tz.fileexplorer.utils.PopuWindowUtils.IOperaPopWindow;
import com.tz.fileexplorer.utils.SDUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements IOperaPopWindow {

	private ListView listview;
	private List<FileBean> list;
	private FileAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) this.findViewById(R.id.listview);
		list = new ArrayList<FileBean>();
		adapter = new FileAdapter(this, list);
		listview.setAdapter(adapter);
		initListener();
		MySeekFileTask task = new MySeekFileTask();
		task.execute(SDUtils.getSDPath());

	}

	private void initListener() {
		PopuWindowUtils.newInstance(this).setOpera(this);
		// 点击事件
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FileBean fileb = list.get(position);
				if (fileb.isDir()) {
					MySeekFileTask task = new MySeekFileTask();
					task.execute(fileb.getPath());
				}
				
			}

		});
		// 长按事件
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				FileBean fileBean = (FileBean) adapter.getItem(position);
				if (fileBean.getName().equals("返回")) {
					return true;
				}
				if (view != null) {
					ImageView icon = (ImageView) view.findViewById(R.id.avatar);
					int[] location = new int[2];
					view.getLocationInWindow(location);
					PopupWindow popupWindow = PopuWindowUtils.newInstance(MainActivity.this)
							.getPopupWindow(view.getHeight(), fileBean);
					popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + icon.getRight(), location[1]);
				}
				return true;
			}
		});

	}

	/**
	 * 此类的描述:加载文件
	 * 
	 * @author: studio
	 */
	private List<FileBean> showFile(String path) {
		List<FileBean> list = new ArrayList<FileBean>();
		File f = new File(path);
		if (f.isDirectory()) {
			if (!f.getAbsolutePath().equals(SDUtils.getSDPath())) {
				FileBean FileBack = new FileBean("返回", f.getParentFile().getAbsolutePath(),
						BitmapFactory.decodeResource(getResources(), R.drawable.dirs), true);
				list.add(FileBack);
			}
			FileBean bean = null;
			File[] listFiles = f.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				File eachFile = listFiles[i];
				bean = new FileBean();
				if (eachFile.isDirectory()) {// 如果是目录
					bean.setDir(true);
					bean.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
				} else {// 如果是文件
					boolean tag = eachFile.getName().toLowerCase().endsWith(".png")
							|| eachFile.getName().toLowerCase().endsWith(".jpg")
							|| eachFile.getName().toLowerCase().endsWith(".jpeg");
					if (tag) {// 如果是文件，并且是图片文件
						bean.setDir(false);
						bean.setIcon(null);
					} else {// 普通文件
						bean.setDir(false);
						bean.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
					}

				}
				bean.setPath(listFiles[i].getPath());
				bean.setName(eachFile.getName());
				list.add(bean);
			}
		}

		return list;
	}

	/**
	 * 此类的描述:用异步任务来加载SD卡文件
	 * 
	 * @author: studio
	 */
	class MySeekFileTask extends AsyncTask<String, Void, List<FileBean>> {
		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(MainActivity.this, null, "正在加载文件");
		}

		@Override
		protected List<FileBean> doInBackground(String... params) {
			return showFile(params[0]);
		}

		@Override
		protected void onPostExecute(List<FileBean> result) {
			super.onPostExecute(result);
			dialog.dismiss();
			list.clear();
			list.addAll(result);
			if (result != null && result.size() > 0) {
				adapter.notifyDataSetChanged();
			}
		}

	}

	@Override
	public void opearDetail(FileBean filebean) {
		int count = 0;
		String size = "";
		if (filebean.isDir()) {
			File[] files = new File(filebean.getPath()).listFiles();
			long totalSize = 0;
			if (files != null) {
				// 获取该文件夹下有多少个子文件
				count = files.length;
				// 计算文件夹下所有文件的总大小
				for (File f : files) {
					if (f != null) {
						totalSize += f.length();
					}
				}
			}
			// 转换为多少KB或多少M
			size = FileUtils.sizeToStr(totalSize);

		} else {
			// 转换为多少KB或多少M
			size = FileUtils.sizeToStr(new File(filebean.getPath()).length());
		}

		AlertDialog dialog = createAlertDialog("详细", size, count, filebean, false);
		dialog.show();

	}

	@Override
	public void opearDel(FileBean filebean) {
		AlertDialog dialog = createAlertDialog("删除", "", 0, filebean, true);
		dialog.show();

	}

	@Override
	public void opearInto(FileBean filebean) {
		new MySeekFileTask().execute(filebean.getPath());

	};

	/**
	 * 创建一个弹出对话框
	 * 
	 * @param size
	 *            文件夹大小
	 * @param count
	 *            子文件数量
	 * @return 返回一个弹出对话框
	 */
	private AlertDialog createAlertDialog(String title, String size, int count, final FileBean filebean,
			boolean isDelete) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (isDelete) {
			String message = new File(filebean.getPath()).isFile() ? ("您确定 要删除" + filebean.getName() + "文件吗？ ")
					: "您确定 要删除" + filebean.getName() + "文件夹吗？";
			builder.setMessage(message);
			builder.setNegativeButton("取消", null);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					boolean success = false;
					if (new File(filebean.getPath()).isFile()) {
						success = new File(filebean.getPath()).delete();
					} else {
						success = FileUtils.deleteFile(new File(filebean.getPath()));
					}
					// 如果删除成功 ，通知数据更新
					if (success) {
						if (list.contains(filebean)) {
							list.remove(filebean);
							adapter.notifyDataSetChanged();
						}
					}
				}

			});
		} else {
			LinearLayout linearLayout = createLinearLayout(size, count, filebean);
			builder.setView(linearLayout);
			builder.setNegativeButton("确定", null);
		}
		builder.setTitle(title);

		return builder.create();
	}

	/**
	 * 创建一个View
	 * 
	 * @param size
	 *            文件夹下总大小
	 * @param count
	 *            文件夹下的子文件数
	 * @return
	 */
	private LinearLayout createLinearLayout(String size, int count, FileBean sdFile) {
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		linearLayout.setLayoutParams(params);

		TextView tvSize = new TextView(this);
		LinearLayout.LayoutParams tvSizeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		tvSize.setLayoutParams(tvSizeParams);
		tvSize.setPadding(10, 8, 10, 8);
		tvSize.setText("文件总大小为： " + size);
		tvSize.setTextColor(Color.BLACK);
		linearLayout.addView(tvSize);

		if (sdFile.isDir()) {
			TextView tvCount = new TextView(this);
			LinearLayout.LayoutParams tvCountParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			tvCount.setLayoutParams(tvCountParams);
			tvCount.setPadding(10, 8, 10, 8);
			tvCount.setText("总共文件数量： " + count);
			tvCount.setTextColor(Color.BLACK);
			linearLayout.addView(tvCount);
		} else {
			CheckBox cb = new CheckBox(this);
			LinearLayout.LayoutParams cbParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			cb.setLayoutParams(cbParams);
			cb.setPadding(5, 8, 10, 8);
			cb.setText("是否是文件");
			// cb.setChecked(sdFile.getFile().isFile());
			cb.setEnabled(false);
			cb.setTextColor(Color.BLACK);
			linearLayout.addView(cb);
		}

		TextView tvModify = new TextView(this);
		LinearLayout.LayoutParams tvModifyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		tvModify.setLayoutParams(tvModifyParams);
		tvModify.setPadding(10, 8, 10, 8);
		tvModify.setText("最后修改时间：" + dateTimeToStr(new File(sdFile.getPath()).lastModified()));
		tvModify.setTextColor(Color.BLACK);
		linearLayout.addView(tvModify);

		return linearLayout;
	}

	/**
	 * 将时间戳转换成字符串
	 * 
	 * @param dateTime
	 *            时间戳
	 * @return 日期字符串
	 */
	private String dateTimeToStr(long dateTime) {
		Date date = new Date(dateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

}
