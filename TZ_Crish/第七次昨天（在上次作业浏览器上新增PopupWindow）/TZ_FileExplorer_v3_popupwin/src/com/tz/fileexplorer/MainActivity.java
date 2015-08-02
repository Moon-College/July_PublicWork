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
 *高性能文件浏览器 
 *(侧重浏览图片)
 */
public class MainActivity extends Activity implements PopWindosOnclickInterface {

	private ListView lv;
	private String rootPath;
	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	
	private MyPopupWindow mPopupWindow;
	private int mScreenHeight;
	private View currentItemView; //记录ListView中点击item的View
	private int currentPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.lv_file);
		//判断SD卡是否正常挂在
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//得到sd卡根目录
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD Error", 0).show();
		}
		
		initData(rootPath);
		// 获取屏幕实际像素
		mScreenHeight = getScreenHeight();
		mPopupWindow = new MyPopupWindow(this);

		setOnListener();
	}
	
	//设置监听事件
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
		
		// 列表项长按事件
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
									int position, long id) {
						
				// 获取被点击项所在位置
				int[] a = new int[2];
				view.getLocationOnScreen(a);
				view.setBackgroundColor(Color.BLUE); 
				currentItemView = view; //标记触发那个ListView的条目View
				currentPosition = position; //标记触发那个ListView的位置

				// 在指定位置显示弹窗, 以底部中间为基准点
				mPopupWindow.showAtLocation(lv, Gravity.BOTTOM
								| Gravity.RIGHT, 0, mScreenHeight - a[1]);
						
				return false;
			}
		});
			
		//设置PopupWindow消失事件
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
		adapter = new FileAdapter(list, this);
		lv.setAdapter(adapter);
	}

	//获取屏幕高度像素
	private int getScreenHeight() {
		// 获取屏幕实际像素
		DisplayMetrics displayMetrics = new DisplayMetrics();
		Display display = MainActivity.this.getWindowManager()
				.getDefaultDisplay();
		display.getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}
	

	@Override
	public void detailOnclick() {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "你查看了第" + (currentPosition+1) + "项",
				Toast.LENGTH_SHORT).show();
		if(mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	public void reNameOnclick() {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "你重命名了第" + (currentPosition+1) + "项",
				Toast.LENGTH_SHORT).show();
		if(mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	public void delOnclick() {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "你删除了第" + (currentPosition+1) + "项",
				Toast.LENGTH_SHORT).show();
		if(mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

}

