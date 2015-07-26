package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.FileBean;
import com.tz.fileexplorer.utils.SDUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

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

	}
	/**
	 * 此类的描述:加载文件
	 * @author:  studio	
	 */
	private List<FileBean> showFile(String path) {
		List<FileBean> list = new ArrayList<FileBean>();
		File f = new File(path);
		if (f.isDirectory()) {
			if(!f.getAbsolutePath().equals(SDUtils.getSDPath())) {
				FileBean  FileBack=new FileBean("返回", f.getParentFile().getAbsolutePath(), BitmapFactory.decodeResource(getResources(), R.drawable.dirs), true);
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
					if (tag) {//如果是文件，并且是图片文件
						bean.setDir(false);
						bean.setIcon(null);
					} else {//普通文件
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
	 * @author:  studio	
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

	};
	
	
	

}
