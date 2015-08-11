package com.tz.lsn6;

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

import com.tz.lsn6.adapter.FileExplorerAdapter;
import com.tz.lsn6.bean.MyFile;

/**
 * �������ļ������ (�������ͼƬ)
 * @author dallon2
 *
 */
public class MainActivity extends Activity {

    private ListView lv;
	private FileExplorerAdapter adapter;
	private String rootPath = null;
	private List<MyFile> list  = new ArrayList<MyFile>();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);

        init();
    }

	private void init() {
		// TODO Auto-generated method stub
		//�ж�SD���Ƿ���������
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			
		} else {
			Toast.makeText(this, "SD Card û����������", Toast.LENGTH_SHORT).show();
		}
		
		initData(rootPath);
	}
	
	// ��ʼ������ ����ı������ļ��нڵ�
	private void initData(String fileNode) {
		
		//���������б�
		list.clear();
		
		//�ļ� �ڵ�
		File pfile = new File(fileNode);
		//���ڵ�������ļ����ļ��У���������Ŀ¼����ģ���������
		File[] listFiles = pfile.listFiles();
		//���ϼ��ڵ㲻��Ҫ�� ���� �˵�
		if(fileNode != null && !fileNode.equals(rootPath)) {
			list.add(new MyFile("����", 
					BitmapFactory.decodeResource(getResources(),R.drawable.back), 
					//��ȡ ��ǰ�ڵ� ���ϼ��ڵ����·��
					pfile.getParentFile().getAbsolutePath(), false));
		}
		
		for (File file:listFiles) {
			
			MyFile myFile = new MyFile();
			
			myFile.setName(file.getName());
			myFile.setPath(file.getAbsolutePath());
			//������ļ���
			if(file.isDirectory()) {
				//����Ϊ�ļ��е�ͼƬ
				myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
				myFile.setImg(false);
			} else {
				
				if(file.getName() != null 
					&& (file.getName().toLowerCase().endsWith(".jpg")
						||file.getName().toLowerCase().endsWith(".jpeg")
						||file.getName().toLowerCase().endsWith(".png"))) {
					myFile.setIcon(BitmapFactory.decodeFile(file.getAbsolutePath()));
					myFile.setImg(true);
				} else {
					myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
					myFile.setImg(false);
				}
						
			}
			
			list.add(myFile);
		}
		
		adapter = new FileExplorerAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				MyFile myFile = list.get(position);
				File file = new File(myFile.getPath());
				if(file.isDirectory()) {
					initData(myFile.getPath());
				} else {
					Toast.makeText(MainActivity.this, "�Ķ���������", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
