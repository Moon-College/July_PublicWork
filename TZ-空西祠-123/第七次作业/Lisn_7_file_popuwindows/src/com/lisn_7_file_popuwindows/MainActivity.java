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
 *高性能文件浏览器 
 *(侧重浏览图片)
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
		//判断SD卡是否正常挂在
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//得到sd卡根目录
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD错误", 0).show();
		}
		
	
		initData(rootPath);
		adapter = new FileAdapter(list, this);
		lv.setAdapter(adapter);
		/**
		 * 长按按钮查看或者删除
		 */
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
                 MainActivity.this.postion=position;
				/**
				 * contentView 自定义view
				 * , width, 宽度
				 * height, 高度
				 * focusable 焦点
				 */
				View popuview=View.inflate(MainActivity.this, R.layout.popu_item, null);
				popu=new PopupWindow(popuview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);//(可以自定义布局，可以设置弹出的指定位置)
				popu.setBackgroundDrawable(new ColorDrawable());
				//在某个控件下面下拉出泡泡窗体
				//popu.showAsDropDown(view, 0, 0);//x,y的偏移量
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
		//遍历SD卡
		File file = new File(path);
		File[] listFiles = file.listFiles();
		
		MyFile file_back = new MyFile(
				"返回",
				BitmapFactory.decodeResource(getResources(), R.drawable.dirs), 
				file.getParentFile().getAbsolutePath(),
				false);
		list.add(file_back);
		for (File f : listFiles) {
			MyFile myFile = new MyFile();
			myFile.setName(f.getName());
			myFile.setPath(f.getAbsolutePath());
			if(f.isDirectory()){//是文件夹
				myFile.setIcon(false);
				myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			}else{
				//文件
				//图片
				if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".jpeg")){
					myFile.setIcon(true);
					myFile.setIcon(null);
//					myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}else{//其他文件
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
			Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
			AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

			builder.setTitle("提示");
			builder.setMessage("是否删除");
			builder.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					popu.dismiss();
					list.remove(postion);

					adapter.notifyDataSetChanged();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				
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
