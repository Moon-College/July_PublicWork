package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.beans.MyFile;
import com.tz.fileexplorer.view.MyPopupWindow;
import com.tz.fileexplorer.view.MyPopupWindow.PopWindosOnclickInterface;


/**
 *�������ļ������ 
 *(�������ͼƬ)
 */
public class MainActivity extends Activity implements PopWindosOnclickInterface {

	private ListView lv;
	private String rootPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	
	private MyPopupWindow mPopupWindow;
	private int mScreenHeight;
	private View currentItemView; //��¼ListView�е��item��View
	private int currentPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.lv_file);
		//�ж�SD���Ƿ���������
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//�õ�sd����Ŀ¼
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD Error", 0).show();
		}
		
		initData(rootPath);
		// ��ȡ��Ļʵ������
		mScreenHeight = getScreenHeight();
		mPopupWindow = new MyPopupWindow(this);

		setOnListener();
	}
	
	//���ü����¼�
	private void setOnListener() {
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
		
		// �б�����¼�
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
									int position, long id) {
						
				// ��ȡ�����������λ��
				int[] a = new int[2];
				view.getLocationOnScreen(a);
				view.setBackgroundColor(Color.BLUE); 
				currentItemView = view; //��Ǵ����Ǹ�ListView����ĿView
				currentPosition = position; //��Ǵ����Ǹ�ListView��λ��

				// ��ָ��λ����ʾ����, �Եײ��м�Ϊ��׼��
				mPopupWindow.showAtLocation(lv, Gravity.BOTTOM
								| Gravity.RIGHT, 0, mScreenHeight - a[1]);
						
				return false;
			}
		});
			
		//����PopupWindow��ʧ�¼�
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
					
			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				if(currentItemView != null) {
					currentItemView.setBackgroundColor(Color.parseColor("#00ffffff")); 
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
				if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".jpeg")){
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

	//��ȡ��Ļ�߶�����
	private int getScreenHeight() {
		// ��ȡ��Ļʵ������
		DisplayMetrics displayMetrics = new DisplayMetrics();
		Display display = MainActivity.this.getWindowManager()
				.getDefaultDisplay();
		display.getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}
	

	@Override
	public void detailOnclick() {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "��鿴�˵�" + (currentPosition+1) + "��",
				Toast.LENGTH_SHORT).show();
		if(mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	public void reNameOnclick() {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "���������˵�" + (currentPosition+1) + "��",
				Toast.LENGTH_SHORT).show();
		if(mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	public void delOnclick() {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "��ɾ���˵�" + (currentPosition+1) + "��",
				Toast.LENGTH_SHORT).show();
		if(mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

}

