package com.tz;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tz.adapter.MyAdapter;
import com.tz.bean.MyFile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{

	private ListView lv_file;
	private String rootPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_file = (ListView) findViewById(R.id.lv_List); // 初始化控件

		// 获取SD卡根路径
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			rootPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		} else {
			Toast.makeText(this, "SD卡出错请检查", Toast.LENGTH_LONG).show();
		}
		init(rootPath);
		
		lv_file.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyFile myFile = (MyFile) adapter.getItem(position);
		File file = new File(myFile.getPath());
		if(file.isDirectory()){
			init(file.getAbsolutePath());
		}
	}
	

	private void init(String path) {
		list.clear();
		// 获取SD卡文件集合
		File file = new File(path);
		File[] listFiles = file.listFiles();
		// 设置返回键功能
		MyFile backFile = new MyFile();
		backFile.setIcon(BitmapFactory.decodeResource(getResources(),
				R.drawable.dirs));
		backFile.setInfo("返回");
		backFile.setPath(file.getParentFile().getAbsolutePath());
		backFile.setPhoto(false);
		
		list.add(backFile);
		
		for (File f : listFiles) {
			MyFile myFile = new MyFile();
			myFile.setInfo(f.getName());
			myFile.setPath(f.getAbsolutePath());
			
			//判断是否为文件夹
			if(f.isDirectory()){
				myFile.setPhoto(false);
				myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			}else{
				//判断是否为图片
				String str = f.getName().toLowerCase();
//				if(str.endsWith(".jpg")||str.endsWith(".gif")||str.endsWith(".png")||str.endsWith(".jpeg")){
				if(f.getName().toLowerCase().endsWith(".png")){
					myFile.setPhoto(true);
					myFile.setIcon(null);
				}else{
					myFile.setPhoto(false);
					myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
			}
			list.add(myFile);
		}
		adapter = new MyAdapter(list, this);
		lv_file.setAdapter(adapter);
	}

}
