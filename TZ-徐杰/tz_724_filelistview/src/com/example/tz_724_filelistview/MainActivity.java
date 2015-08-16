package com.example.tz_724_filelistview;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tz_724_filelistview.adapter.MyAdapter;
import com.example.tz_724_filelistview.bean.MyFile;

public class MainActivity extends Activity {

	ListView lv_file;
	String rootpath;
	List<MyFile> list = new ArrayList<MyFile>();
	private MyAdapter adapter;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_file = (ListView) findViewById(R.id.lv_file);
		if(getRootPath()!=null)
		{
		rootpath = getRootPath();
		initData(rootpath);
		Log.e("rootpath", rootpath);
		}else
		{
			Toast.makeText(this, "sd卡不存在", 1).show();
			return;
		}
		
		adapter = new MyAdapter(list, this);
		lv_file.setAdapter(adapter);
		lv_file.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
             MyFile myFile = list.get(arg2);
             if(new File(myFile.getPath()).isDirectory())
             {
            	  initData(myFile.getPath());
                  adapter.notifyDataSetChanged();
             }
           
			}
		});
	}

	/**
	 * 判断sd卡是否存在，获取根目录
	 * @return 
	 */
	private String getRootPath() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			 return  Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		}else{
			
			Toast.makeText(this, "sd卡不存在", 1).show();
			return null;
		}
     
	}

	private void initData(String path) {
		
		list.clear();
		
		if (!path.equals(rootpath)) {
			File file = new File(path);
			Bitmap icon = BitmapFactory.decodeResource(getResources(),
					R.drawable.qun);
			MyFile myFile = new MyFile("返回", file.getParent(), icon,
					false);
			list.add(myFile);
		}
		File rootFile = new File(path);
		File[] childFiles = rootFile.listFiles();
		List<File> fileList = new ArrayList<File>();
		Collections.addAll(fileList, childFiles);
		for (File file : fileList) {
			MyFile mFile = new MyFile();
			if (file.isDirectory()) {
				mFile.setName(file.getName());
				Bitmap icon = BitmapFactory.decodeResource(getResources(),
						R.drawable.qun);
				mFile.setIcon(icon);
				mFile.setIsIcon(false);
				mFile.setPath(file.getAbsolutePath());
			} else {
				if (file.getName().endsWith(".jpg")
						|| file.getName().endsWith(".png")) {
					mFile.setName(file.getName());
					mFile.setIcon(null);
					mFile.setIsIcon(true);
					mFile.setPath(file.getPath());
				} else {
					Bitmap icon = BitmapFactory.decodeResource(getResources(),
							R.drawable.kus);
					mFile.setName(file.getName());
					mFile.setPath(file.getPath());
					mFile.setIsIcon(false);
					mFile.setIcon(icon);
				}

			}
			list.add(mFile);
//			adapter = new MyAdapter(list, this);
//			lv_file.setAdapter(adapter);
		}
		
	}

}
