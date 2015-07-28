package com.tz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.tz_class7_homework.R;
import com.tz.adapter.FileAdapter;
import com.tz.bean.MyFile;

public class MainActivity extends Activity implements OnItemClickListener{

	private ListView lv_show;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter = null;
	private String rootPath = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		lv_show = (ListView) findViewById(R.id.lv_show);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		initData(rootPath);
		
		lv_show.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyFile myFile = (MyFile) adapter.getItem(position);
		File file = new File(myFile.getPath());
		if(file.isDirectory()){
			initData(file.getAbsolutePath());
		}
	}
	private void initData(String path) {
		list.clear();
		File file = new File(path);
		File[] listFiles = file.listFiles();
		
		//创建返回按钮
		MyFile backFile = new MyFile();
		backFile.setName("返回");
		backFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
		backFile.setPath(file.getParentFile().getAbsolutePath());
		backFile.setPhoto(false);
		list.add(backFile);
		
		for (File f : listFiles) {
			MyFile myFile = new MyFile();
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			//判断是否为文件夹
			if(f.isDirectory()){
				 myFile.setPhoto(false);
				 myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			}else{
				//判断是否为图片
				String str = f.getName().toLowerCase();
				if(str.endsWith(".jpg")||str.endsWith(".jpeg")||str.endsWith(".png")||str.endsWith(".gif")){
					myFile.setPhoto(true);
					myFile.setBitmap(null);
				}else{
					myFile.setPhoto(false);
					 myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
			}
			list.add(myFile);
		}
		adapter = new FileAdapter(list, this);
		lv_show.setAdapter(adapter);
	}
}
