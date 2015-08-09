package com.lisn_7_file_popuwindows;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;


import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;
/**
 *�������ļ������ 
 *(�������ͼƬ)
 */
public class MainActivity extends Activity implements android.view.View.OnClickListener{
    private Button del,check;
	private ListView lv;
	private String rootPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
    private PopupWindow popu;
    private int postion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.listview);
		//�ж�SD���Ƿ���������
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//�õ�sd����Ŀ¼
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD����", 0).show();
		}
		
	
		initData(rootPath);
		adapter = new FileAdapter(list, this);
		lv.setAdapter(adapter);
		/**
		 * ������ť�鿴����ɾ��
		 */
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
                 MainActivity.this.postion=position;
				/**
				 * contentView �Զ���view
				 * , width, ���
				 * height, �߶�
				 * focusable ����
				 */
				View popuview=View.inflate(MainActivity.this, R.layout.popu_item, null);
				popu=new PopupWindow(popuview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);//(�����Զ��岼�֣��������õ�����ָ��λ��)
				popu.setBackgroundDrawable(new ColorDrawable());
				//��ĳ���ؼ��������������ݴ���
				//popu.showAsDropDown(view, 0, 0);//x,y��ƫ����
				int[] location = new int[2];
				view.getLocationInWindow(location);
				popu.showAtLocation(view, Gravity.TOP|Gravity.LEFT, location[0]+200, location[1]);
				del=(Button) popuview.findViewById(R.id.del);
				check=(Button) popuview.findViewById(R.id.check);
				
				del.setOnClickListener(MainActivity.this);
				check.setOnClickListener(MainActivity.this);
				return false;
			}
			
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 
				MyFile myFile = (MyFile) adapter.getItem(position);
				File file = new File(myFile.getPath());
				if(file.isDirectory()){
					initData(file.getAbsolutePath());
					adapter.notifyDataSetChanged();
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
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.del:
			Toast.makeText(this, "ɾ��", Toast.LENGTH_SHORT).show();
			AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

			builder.setTitle("��ʾ");
			builder.setMessage("�Ƿ�ɾ��");
			builder.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					popu.dismiss();
					list.remove(postion);

					adapter.notifyDataSetChanged();
				}
			});
			builder.setNegativeButton("ȡ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					popu.dismiss();
				}
			});
			builder.show();	
			break;
		case R.id.check:
			popu.dismiss();
			MyFile myFile = (MyFile) adapter.getItem(postion);
			String path=myFile.getPath();
			String name=myFile.getName();
			Intent intent=new Intent();
			System.out.println(path+"--------------"+name);
			intent.putExtra("path", path);
			intent.putExtra("name", name);
			intent.setClass(MainActivity.this, CheckActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}



}
