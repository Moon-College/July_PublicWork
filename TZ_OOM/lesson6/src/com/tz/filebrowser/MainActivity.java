package com.tz.filebrowser;

import java.io.File;
import java.lang.ref.SoftReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.filebrowser.adapter.FileAdapter;
import com.tz.filebrowser.bean.SDFile;
import com.tz.filebrowser.utils.FileUtil;

public class MainActivity extends FragmentActivity implements OnItemClickListener {
	
	// SD卡根目录
	private File mRootFile = Environment.getExternalStorageDirectory();
	
	private ListView mListView;
	private FileAdapter adapter;

	private List<SDFile> sdFileList;
	private DecimalFormat  format;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		format = new DecimalFormat("###.00");
		sdFileList = new ArrayList<SDFile>();
		adapter = new FileAdapter(this, sdFileList);
		
		mListView = (ListView) findViewById(R.id.lv);
		mListView.setOnItemClickListener(this);
		mListView.setAdapter(adapter);
		
		if (FileUtil.isExistSDCard()) {
			new LoadSDCardFileAsyncTask().execute(mRootFile);
			
		}else {
			Toast.makeText(this, "SD卡不存在", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	/**
	 * 加载SD卡
	 * @param path
	 */
	private List<SDFile> getSDCardFile(File file) {
		List<SDFile> sdFileList = new ArrayList<SDFile>();
		
		if(file.isDirectory()) {
			// 判断是否返回到SD卡根目录，如果没有到SD卡根目录，就添加返回项
			if(!file.getAbsolutePath().equals(mRootFile.getAbsolutePath())) {
				SDFile back = new SDFile();
				back.setName("返回");
				back.setFile(file.getParentFile());
				back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
				sdFileList.add(back);
				
			}
			
			File[] files = file.listFiles();
			if(files != null) {
				for(File f : files) {
					SDFile sdFile = new SDFile();
					sdFile.setName(f.getName());
					sdFile.setFile(f);
					
					if(f.isDirectory()) {
						sdFile.setCount(f.listFiles() == null ? 0 : f.listFiles().length);
						sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
						sdFile.setPicture(false);
					}
					else if(f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith("jpg") || 
							f.getName().toLowerCase().endsWith(".jpeg")) {
						sdFile.setBitmap(null);
						sdFile.setFileSize(getFileSize(f));
						sdFile.setPicture(true);
					}
					
					else {
						sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
						sdFile.setFileSize(getFileSize(f));
						sdFile.setPicture(false);
					}
					sdFileList.add(sdFile);
				}
			}
			
			
		}
		return sdFileList;
	}
	
	
	private String getFileSize(File file) {
		if(file == null){
			return "0B";
		}
		else {
			long size = file.length();
			if(size / 1024 < 1) {
				return size + "B";
			}else if(size / (1024 * 1024) < 1) {
				return format.format((size / (1024 * 1.0))) + "KB";
			}else {
				
				return format.format(size / (1024 * 1024 * 1.0)) + "M";
			}
		}
		
	}
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SDFile sdFile = (SDFile) adapter.getItem(position);
		if(sdFile.getFile().isDirectory()) {
			new LoadSDCardFileAsyncTask().execute(sdFile.getFile());
		}
	}
	
	
	class LoadSDCardFileAsyncTask extends AsyncTask<File, Void, List<SDFile>> {
		private ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this,R.style.loading_dialog);
			dialog.setMessage("正在拼命加载SD卡数据....");
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		}
		
		@Override
		protected List<SDFile> doInBackground(File... params) {
			return getSDCardFile(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<SDFile> result) {
			if(dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			if(result != null) {
				sdFileList.clear();
				sdFileList.addAll(result);
				adapter.notifyDataSetChanged();
			}
		}
	}
	
}
