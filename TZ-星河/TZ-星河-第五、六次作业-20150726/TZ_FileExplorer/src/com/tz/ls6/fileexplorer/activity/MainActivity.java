package com.tz.ls6.fileexplorer.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tz_fileexplorer.R;
import com.tz.ls6.fileexplorer.adapter.FileListViewAdapter;
import com.tz.ls6.fileexplorer.entity.MyFile;

public class MainActivity extends Activity {
	private ListView lvFiles;
	private TextView tvBack;
	private String rootPath=null;
	private List<MyFile> files=new ArrayList<MyFile>();
	private FileListViewAdapter adapter=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		lvFiles.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long itemId) {
				MyFile mFile=files.get(position);
				File file=new File(mFile.getPath());
				if(file.isDirectory()){
					getFileList(mFile.getPath());
				}
			}
		});
		
		tvBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String parent=(String) tvBack.getTag();
				getFileList(parent);
			}
		});

		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
			rootPath=sdPath;
			tvBack.setTag(rootPath);
			Log.i("SD PATH", rootPath);
			getFileList(sdPath);
		}else{
			Toast.makeText(this, "√ª”–≤Â»ÎSDø®", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void getFileList(String path) {
		files.clear();
		File root=new File(path);
		System.out.println(path);
		if(!path.equals(rootPath)){
			tvBack.setTag(root.getParent());
		}else{
			tvBack.setTag(path);
		}
		if(root.exists()&&root.isDirectory()){
			File[] childs=root.listFiles();
			for(File child:childs){
				if(!child.exists()){
					continue;
				}
				MyFile mFile=new MyFile();
				String fileName=child.getName();
				mFile.setFileName(fileName);
				mFile.setPath(child.getAbsolutePath());
				if(child.isFile()){
					if(fileName.toLowerCase().endsWith(".png")
							||fileName.toLowerCase().endsWith(".jpg")
							||fileName.toLowerCase().endsWith(".jpeg")){
						mFile.setIcon(null);
					}else{
						Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.file);
						mFile.setIcon(bm);
					}
				}else{
					Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.dir);
					mFile.setIcon(bm);
				}
				files.add(mFile);
			}
			adapter=new FileListViewAdapter(files, this);
			lvFiles.setAdapter(adapter);
		}
	}
	private void initView() {
		lvFiles=(ListView) findViewById(R.id.lv_filelist);
		tvBack=(TextView) findViewById(R.id.tv_back);
	}
	
	
	
	
}
