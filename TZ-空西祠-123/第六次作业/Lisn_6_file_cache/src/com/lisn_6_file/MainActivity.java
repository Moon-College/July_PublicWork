package com.lisn_6_file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lisn_6_file.adapter.Fileadapter;
import com.lisn_6_file.bean.MyFile;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 
 * ������ͼƬ�����
 *
 */
public class MainActivity extends Activity {
private List<MyFile> list=new ArrayList<MyFile>();
private ListView lv;
private String roopath;
private Fileadapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lv);
		//�ж�sd���Ƿ���������
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//�õ�sd����Ŀ¼
			roopath=Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD ����",Toast.LENGTH_SHORT).show();
		}
		initData(roopath);
	
		//����sd��
	    lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//�ж��Ƿ�Ϊ�ļ���
				MyFile myfile=(MyFile) adapter.getItem(position);
				File file=new File(myfile.getPath());//��ȡ�ļ���·��
				if(file.isDirectory()){
					initData(myfile.getPath());
					//adapter.notifyDataSetChanged();
				}
			}
		});

	}
	private void initData(String roopath) {
		list.clear();
		// TODO Auto-generated method stub
		File file=new File(roopath);//��ȡ
		File[] listFiles=file.listFiles();//��ȡ��ǰ·���µ������ļ�
		MyFile fileback=new MyFile("����", BitmapFactory.decodeResource(this.getResources(), R.drawable.dirs), file.getParentFile().getAbsolutePath(), false);//����ͼƬ����λͼ���󣬸����ظ�һ���ļ���·��
		list.add(fileback);
		for(File f:listFiles){
			MyFile myfile=new MyFile();
			myfile.setName(f.getName());
			myfile.setPath(f.getAbsolutePath());
			if(f.isDirectory()){//���ļ���
				myfile.setBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dirs));
				myfile.setIcon(false);
			}else{
				//�ļ�
				//ͼƬ
				if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".jpeg")||f.getName().toLowerCase().endsWith(".ico")){
					myfile.setIcon(true);
					myfile.setBitmap(null);
				}else{
					myfile.setBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.file));
					myfile.setIcon(false);
				}
			}
			list.add(myfile);
		}
		adapter=new Fileadapter(list, this);
	    lv.setAdapter(adapter);
	}

}
