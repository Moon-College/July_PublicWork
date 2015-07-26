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
 * �ļ������
 * 
 * @author �Խ���
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
	 * �������ؼ�
	 */
	private void initView() {
		fileListLv = (ListView) findViewById(R.id.lv_filelist);
		// �ж�SD�Ƿ��ѹ���
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			sdPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
		} else {
			Toast.makeText(this, "SD ERROR", Toast.LENGTH_LONG).show();
		}
		MyLog.i("jzhao", "sdPath-->" + sdPath);

		// ���ü���
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
	 * ��ʼ������
	 */
	public void initData(String path) {
		if (list != null && list.size() > 0) {
			list.clear();
		}

		File file = new File(path);
		if (file.isDirectory()) {
			// ����һ������
			if (!path.equalsIgnoreCase(sdPath)) {
				MyFile myFile = new MyFile("����", BitmapFactory.decodeResource(
						getResources(), R.drawable.dirs), file.getParentFile()
						.getAbsolutePath());// ���ص�·��ӦΪ�����ļ�·��
				list.add(myFile);
			}

			// ѭ���ļ����µ������ļ�
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {

					// ������ļ���
					if (f.isDirectory()) {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.dirs), f.getAbsolutePath());
						list.add(myFile);
					}
					// �����ͼƬ��ʵ���첽����
					else if (f.getPath().toLowerCase().endsWith(".png")
							|| f.getPath().toLowerCase().endsWith(".jpg")
							|| f.getPath().toLowerCase().endsWith(".jpeg")
							|| f.getPath().toLowerCase().endsWith(".gif")) {
						MyFile myFile = new MyFile(f.getName(),
								null, //��ͼƬ���Ȳ����أ���̨�첽����ʵ��
								f.getAbsolutePath());
						list.add(myFile);
					}
					// �����ļ�
					else {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.file), f.getAbsolutePath());
						list.add(myFile);
					}
				}

			}

			// ����������
			adapter = new FileAdapter(list, this);
			fileListLv.setAdapter(adapter);
		}
	}
}