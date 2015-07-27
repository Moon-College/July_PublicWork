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
/**
 *�������ļ������ 
 *(�������ͼƬ)
 */
public class MainActivity extends Activity {

	private ListView lv;
	private String rootPath,innerPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.lv);
		//�ж�SD���Ƿ���������
		innerPath=Environment.getRootDirectory().getAbsolutePath();

		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//�õ�sd����Ŀ¼
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD Error", 0).show();
		}
		
	
		initData(rootPath);
	//	initData(innerPath);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 
				MyFile myFile = (MyFile) adapter.getItem(position);
				File file = new File(myFile.getPath());
				if(file.isDirectory()){
					initData(file.getAbsolutePath());
				}
			}
		});
	}

	private void initData(String path) {
		list.clear();
		//����SD��
		File file = new File(path);
		File[] listFiles = file.listFiles();
		
		MyFile file_back = new MyFile(
				"����",
				BitmapFactory.decodeResource(getResources(), R.drawable.dirs), 
				file.getParentFile().getAbsolutePath(),
				false);
		list.add(file_back);
		for (File f : listFiles) {
			MyFile myFile = new MyFile();
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			if(f.isDirectory()){//���ļ���
				myFile.setIcon(false);
				myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			}else{
				//�ļ�
				//ͼƬ
				if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||
						f.getName().toLowerCase().endsWith(".jpeg")||f.getName().toLowerCase().endsWith(".gif")){
					myFile.setIcon(true);
					myFile.setIcon(null);
//					myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}else{//�����ļ�
					myFile.setIcon(false);
					myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
			}
			list.add(myFile);
		}
		adapter = new FileAdapter(list, this);
		lv.setAdapter(adapter);
	}

}
