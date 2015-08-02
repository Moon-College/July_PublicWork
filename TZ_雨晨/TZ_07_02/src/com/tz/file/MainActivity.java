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
			Toast.makeText(this, "sd��������", Toast.LENGTH_SHORT).show();
		}
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
	}

	private List<FileBean> getSDFile(File file) {

		List<FileBean> list = new ArrayList<FileBean>();
		// �жϵ�ǰ�Ƿ���Ŀ¼
		if (file.isDirectory()) {
			// �ж��Ƿ�ΪSD����Ŀ¼
			if (!file.getAbsoluteFile()
					.equals(Environment.getExternalStorageDirectory()
							.getAbsoluteFile())) {
				parentFile = file;
				// ���Ǹ�Ŀ¼����ӷ��ذ�ť
				FileBean bean = new FileBean("����", R.drawable.ic_launcher, file
						.getParentFile().getAbsolutePath(), false);
				list.add(bean);
			}
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					// Ŀ¼
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
					// �ļ�
					FileBean bean = new FileBean(f.getName(),
							R.drawable.ic_launcher, f.getAbsolutePath(), false);
					list.add(bean);
				}
			}
		}

		return list;
	}

	/**
	 * �ж�sd���Ƿ����
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
	 * ����sd���߳�����
	 * 
	 * @Title: MainActivity.java (�˴��ο��� tz_oom ˼·)
	 * @Package com.tz.file
	 * @Description: (todo)
	 * @author tokey
	 * @date 2015��7��28�� ����6:14:42
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
			mProgressDialog.setTitle("����SD����...");
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.show();
		}

		@Override
		protected List<FileBean> doInBackground(File... params) {
			return getSDFile(params[0]);
		}

	}

	/**
	 * Item�����
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
		popview.addView(getTextView("���֣�" + bean.getName()));
		popview.addView(getTextView("��ַ��" + bean.getPath()));
		popview.addView(getTextView("��С��" + file.length() / 1024 + "kb"));
		// ���ɾ����ť
		Button btn_delete = new Button(this);
		btn_delete.setText("ɾ��");
		popview.addView(btn_delete);

		final PopupWindow popupWindow = new PopupWindow(popview, 400, 400);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
		// ʹ��۽�
		popupWindow.setFocusable(true);
		// ����������������ʧ
		popupWindow.setOutsideTouchable(true);
		// ɾ���¼�
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
				// �ļ���ɾ��
				flag = deleteDicectory(file);
			} else if (file.isFile()) {
				// �ļ�ɾ��
				flag = file.delete();
			}
			// ��ʾɾ���Ƿ�ɹ�
			if (flag) {
				Toast.makeText(MainActivity.this, "ɾ���ɹ�", Toast.LENGTH_SHORT)
						.show();
				// ˢ������
				mDatas.remove(position);
				mAdapter.notifyDataSetChanged();
			} else {
				Toast.makeText(MainActivity.this, "ɾ��ʧ��", Toast.LENGTH_SHORT)
						.show();
			}
			// ���popupwidndow
			p.dismiss();
		}
	}

	/**
	 * �ݹ�ɾ���ļ�
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
						file.delete();// ɾ��Ŀ¼�µ������ļ��󣬸�Ŀ¼����˿�Ŀ¼����ֱ��ɾ��
					} else if (file.isFile()) {
						file.delete();
					}
				}
			}
			flag = file.delete();// ɾ�������Ŀ¼
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
	 * Item���
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = ((FileBean) mAdapter.getItem(position)).getPath();
		// �ж��Ƿ���Ŀ¼
		File f = new File(path);
		if ((f).isDirectory()) {
			new LoadSDTask().execute(f);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �ж��Ƿ�ΪSD����Ŀ¼
			if (!parentFile.getAbsoluteFile()
					.equals(Environment.getExternalStorageDirectory()
							.getAbsoluteFile())) {
				LoadSDTask loadSDTask = new LoadSDTask();
				loadSDTask.execute(mRootSd);
			} else {
				Toast.makeText(this, "��Ŀ¼��", Toast.LENGTH_SHORT).show();
			}
		}
		return false;
	}

}
