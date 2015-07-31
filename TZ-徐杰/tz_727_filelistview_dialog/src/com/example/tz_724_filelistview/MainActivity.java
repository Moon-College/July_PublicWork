package com.example.tz_724_filelistview;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.tz_724_filelistview.adapter.MyAdapter;
import com.example.tz_724_filelistview.bean.MyFile;

public class MainActivity extends Activity {

	ListView lv_file;
	String rootpath;
	List<MyFile> list = new ArrayList<MyFile>();
	private MyAdapter adapter;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_file = (ListView) findViewById(R.id.lv_file);
		if (getRootPath() != null) {
			rootpath = getRootPath();
			initData(rootpath);
			Log.e("rootpath", rootpath);
		} else {
			Toast.makeText(this, "sd卡不存在", 1).show();
			return;
		}

		adapter = new MyAdapter(list, this);
		lv_file.setAdapter(adapter);
//		lv_file.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				MyFile myFile = list.get(arg2);
//				if (new File(myFile.getPath()).isDirectory()) {
//					initData(myFile.getPath());
//					adapter.notifyDataSetChanged();
//				}
//
//			}
//		});

		// lv_file.setOnItemLongClickListener(new OnItemLongClickListener() {
		// int position;
		// public AlertDialog dialog;
		// @Override
		// public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		// int arg2, long arg3) {
		//
		// final Handler handler = new Handler(){
		// @Override
		// public void handleMessage(Message msg) {
		// if (msg.what ==0) {
		// dialog.dismiss();
		//
		// }
		// }
		//
		// };
		// position = arg2;
		// AlertDialog.Builder builder = new
		// AlertDialog.Builder(MainActivity.this);
		// View view =
		// LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_view,
		// null);
		// Button tv_delete = (Button) view.findViewById(R.id.tv_delete);
		// Button tv_detail = (Button) view.findViewById(R.id.tv_detail);
		// builder.setView(view);
		// dialog = builder.show();
		// tv_delete.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// list.remove(position);
		// adapter.notifyDataSetChanged();
		// handler.sendEmptyMessage(0);
		// }
		// });
		// tv_detail.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// File file = new File(list.get(position).getPath());
		// Toast.makeText(getApplicationContext(), file.getName(), 1).show();
		// handler.sendEmptyMessage(0);
		// }
		// });
		//
		// return true;
		// }
		// });

		lv_file.setOnItemLongClickListener(new OnItemLongClickListener() {
           int position;
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				position =arg2;
				View view = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.dialog_view, null);
				final PopupWindow pw = new PopupWindow(view,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						true);
                pw.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
                pw.showAsDropDown(arg1, 100, 0);
              Button tv_delete = (Button) view.findViewById(R.id.tv_delete);
        		 Button tv_detail = (Button) view.findViewById(R.id.tv_detail);
        		
        		 tv_delete.setOnClickListener(new OnClickListener() {
        		
        		 @Override
        		 public void onClick(View v) {
        		 list.remove(position);
        		 adapter.notifyDataSetChanged();
        		 pw.dismiss();
        		 }
        		 });
        		 tv_detail.setOnClickListener(new OnClickListener() {
        		
        		 @Override
        		 public void onClick(View v) {
        		 File file = new File(list.get(position).getPath());
        		 Toast.makeText(getApplicationContext(), file.getName(), 1).show();
        		 
        		 pw.dismiss();}
        		 });
				return false;
			}
		});

	}

	/**
	 * 判断sd卡是否存在，获取根目录
	 * 
	 * @return
	 */
	private String getRootPath() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {

			Toast.makeText(this, "sd卡不存在", 1).show();
			return null;
		}

	}

	private void initData(String path) {

		list.clear();

		if (!path.equals(rootpath)) {
			File file = new File(path);
			Bitmap icon = BitmapFactory.decodeResource(getResources(),
					R.drawable.qun);
			MyFile myFile = new MyFile("返回", file.getParent(), icon, false);
			list.add(myFile);
		}
		File rootFile = new File(path);
		File[] childFiles = rootFile.listFiles();
		List<File> fileList = new ArrayList<File>();
		Collections.addAll(fileList, childFiles);
		for (File file : fileList) {
			MyFile mFile = new MyFile();
			if (file.isDirectory()) {
				mFile.setName(file.getName());
				Bitmap icon = BitmapFactory.decodeResource(getResources(),
						R.drawable.qun);
				mFile.setIcon(icon);
				mFile.setIsIcon(false);
				mFile.setPath(file.getAbsolutePath());
			} else {
				if (file.getName().endsWith(".jpg")
						|| file.getName().endsWith(".png")) {
					mFile.setName(file.getName());
					mFile.setIcon(null);
					mFile.setIsIcon(true);
					mFile.setPath(file.getPath());
				} else {
					Bitmap icon = BitmapFactory.decodeResource(getResources(),
							R.drawable.kus);
					mFile.setName(file.getName());
					mFile.setPath(file.getPath());
					mFile.setIsIcon(false);
					mFile.setIcon(icon);
				}

			}
			list.add(mFile);
			// adapter = new MyAdapter(list, this);
			// lv_file.setAdapter(adapter);
		}

	}

}
