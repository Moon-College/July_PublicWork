package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.util.MyLog;

/**
 * 文件浏览器
 * 
 * @author 赵建祥
 * 
 */
public class MainActivity extends Activity {

	private ListView fileListLv;
	private List<MyFile> list = new ArrayList<MyFile>();
	private String sdPath;
	private FileAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG = true;
		setContentView(R.layout.main);

		initView();
		initData(sdPath);
	}

	/**
	 * 初化化控件
	 */
	private void initView() {
		fileListLv = (ListView) findViewById(R.id.lv_filelist);
		// 判断SD是否已挂载
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			sdPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		} else {
			Toast.makeText(this, "SD ERROR", Toast.LENGTH_LONG).show();
		}
		MyLog.i("jzhao", "sdPath-->" + sdPath);

		// 设置监听
		fileListLv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyFile myFile = (MyFile) adapter.getItem(position);
				if (new File(myFile.getPath()).isDirectory()) {
					initData(myFile.getPath());
				}
			}
		});
	}

	/**
	 * 初始化数据
	 */
	public void initData(String path) {
		if (list != null && list.size() > 0) {
			list.clear();
		}

		File file = new File(path);
		if (file.isDirectory()) {
			// 首先一个返回
			if (!path.equalsIgnoreCase(sdPath)) {
				MyFile myFile = new MyFile("返回", BitmapFactory.decodeResource(
						getResources(), R.drawable.dirs), file.getParentFile()
						.getAbsolutePath());// 返回的路径应为父级文件路径
				list.add(myFile);
			}

			// 循环文件夹下的所有文件
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {

					// 如果是文件夹
					if (f.isDirectory()) {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.dirs), f.getAbsolutePath());
						list.add(myFile);
					}
					// 如果是图片，实现异步加载
					else if (f.getPath().toLowerCase().endsWith(".png")
							|| f.getPath().toLowerCase().endsWith(".jpg")
							|| f.getPath().toLowerCase().endsWith(".jpeg")
							|| f.getPath().toLowerCase().endsWith(".gif")) {
						MyFile myFile = new MyFile(f.getName(),
								null, //是图片，先不加载，后台异步加载实现
								f.getAbsolutePath());
						list.add(myFile);
					}
					// 其他文件
					else {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.file), f.getAbsolutePath());
						list.add(myFile);
					}
				}

			}

			// 创建适配器
			adapter = new FileAdapter(list, this);
			fileListLv.setAdapter(adapter);
		}
	}
}