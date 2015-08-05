package com.tz.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes.Name;

import android.R.anim;
import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
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
 * 
 * @author wztscau
 * @lastedit 2015.08.04
 */
public class MainActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	private List<MyFile> list = new ArrayList<MyFile>();
	private FileAdapter adapter;
	private ListView listView;
	private String currPath;
	private Button btnDelete;
	private File currFile;
	private MyFile mFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String root;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			root = Environment.getExternalStorageDirectory().getAbsolutePath();
			initData(root);
			listView.setOnItemClickListener(this);
			listView.setOnItemLongClickListener(this);
			
		} else {
			Toast.makeText(this, "sdcard is not exist", 0).show();
		}
	}

	private void initData(String path) {
		list.clear();
		File dirs = new File(path);
		final File[] files = dirs.listFiles();
		Arrays.sort(files);
		for (File f : files) {
			MyFile mFile = new MyFile();

			mFile.setName(f.getName());
			if (f.getName().trim().length() > 24) {
				String front = f.getName().substring(0, 16);
				String back = f.getName().substring(f.getName().length() - 5);
				mFile.setName(front + "..." + back);
			}
			mFile.setPath(f.getAbsolutePath());
			setFileIcon(f, mFile);
			list.add(mFile);
		}
		currPath = path;
		adapter = new FileAdapter(this, list);
		listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = list.get(position).getPath();
		File file = new File(path);
		String fileName = file.getName();
		if (file.isDirectory()) {
			initData(path);
		} else if(fileName.endsWith("txt")||fileName.endsWith("log")||fileName.endsWith("xml")){
			openTextFile(file);
		}

	}

	private void openTextFile(File file) {
		//finishing
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		View contentView = LayoutInflater.from(this).inflate(
				R.layout.popup_window, null);
		mFile = list.get(position);
		currFile = new File(mFile.getPath());
		btnDelete = (Button) contentView.findViewById(R.id.btn_del);
		btnDelete.setOnClickListener(delete);
		//创建泡泡窗口
		PopupWindow ppWindow = new PopupWindow(contentView,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT, true);
		//设置泡泡窗口透明背景
		ppWindow.setBackgroundDrawable(new ColorDrawable());
		int x = view.getWidth()/4*3,y;
		if(position!=list.size()-1){
			y = 0;
		}else {
			y = -view.getHeight();
		}
		//泡泡窗口的位置为所选item的下方,靠右侧的位置
		ppWindow.showAsDropDown(view,x,y);
		return true;
	}

	/**
	 * 
	 * @param f 真实文件/文件夹
	 * @param mFile 自定义文件
	 */
	private void setFileIcon(File f, MyFile mFile) {
		if (f.isDirectory()) {
			mFile.setIcon(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			mFile.setIcon(true);
		} else if (f.isFile()) {
			String name = f.getName().toLowerCase(Locale.US);
			if (name.endsWith("png") || name.endsWith("jpg")
					|| name.endsWith("jpeg")) {
				mFile.setIcon(null);
				mFile.setIcon(false);
			} else {
				mFile.setIcon(BitmapFactory.decodeResource(getResources(),
						R.drawable.file));
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		File file = new File(currPath);
		if (file.getParent() != null) {
			initData(file.getParent());
		} else {
			finish();
		}
	}

	/**
	 * 删除按钮的监听事件
	 */
	private OnClickListener delete = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			showDialog(currFile);
		}
	};
	
	
	/**
	 * 弹出对话框
	 * @param mFile 自定义文件
	 */
	public void showDialog(final File mFile){
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("Warning")
		.setMessage("Do you wish to delete file\n["+mFile.getName()+"]?")
		.setPositiveButton("yes sure", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteMyFile(mFile.getPath());
			}
			
		}).setNegativeButton("no thanks", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//do nothing
			}
		});
		builder.create().show();
		btnDelete.setVisibility(View.INVISIBLE);
	}
	
	/**
	 * 并非真的删除文件，而是删除列表中的项目
	 * @param path
	 */
	private void deleteMyFile(String path) {
		list.remove(mFile);
		adapter.notifyDataSetChanged();
	}
}
