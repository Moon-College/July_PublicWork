package com.tz.fileadapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tz.fileadapter.baen.Myfile;
import com.tz.fileadapter.file.fileadpeter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView lv;
	private String rootPath="";
	private List<Myfile> list =new ArrayList<Myfile>();
	
	private fileadpeter adapter;
	/**
	 * getExternalStorageDirectory()�õ��ⲿ�洢Ŀ¼
	 * getAbsolutePath()�õ�����·��
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView)findViewById(R.id.lv);
		//�����ڴ濨
		//�ж��ڴ濨�Ƿ����Environment.getExternalStorageState()
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			
			rootPath =Environment.getExternalStorageDirectory().getAbsolutePath();			
		}else{
			Toast.makeText(this, "SD��������", 0).show();
			
		}
		
		
		initdata(rootPath);
		
		//��Ŀ��������¼�
		
		lv.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 Myfile myfile= (Myfile)adapter.getItem(position);
				 File file =new File(myfile.getPath());
				 if(file.isDirectory()){
					 initdata(file.getAbsolutePath());
					 
				 }
				
			}
		});
	}

	private void initdata(String path) {
		list.clear();
		File file=new File(path);
		File[] listFile=file.listFiles(); 
		Myfile file_back =new Myfile(
				"����", 
				BitmapFactory.decodeResource(getResources(), R.drawable.dirs),
				file.getParentFile().getAbsolutePath(),//·��
				false);//�Ƿ���ͼƬ
		list.add(file_back);
		for (File f : listFile) {
			Myfile myfile = new Myfile();
			myfile.setName(f.getName());
			myfile.setPath(f.getAbsolutePath());
			if(f.isDirectory()){
			myfile.setIcon(false);
			myfile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			}
			else {
		    	 
			   if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".jpeg")){
				   myfile.setIcon(true);
				   myfile.setIcon(null);
				   
			   }else{
				   myfile.setIcon(false);
				   myfile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
			   }
			
		 }
			
			list.add(myfile);
		}
		
		 adapter =new fileadpeter(list, this);
			lv.setAdapter(adapter); 
	}
}
