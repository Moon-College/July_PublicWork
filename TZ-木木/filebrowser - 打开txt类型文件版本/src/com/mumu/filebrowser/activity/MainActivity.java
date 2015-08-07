package com.mumu.filebrowser.activity;

import java.util.ArrayList;
import java.util.List;

import com.mumu.filebrowser.R;
import com.mumu.filebrowser.R.layout;
import com.mumu.filebrowser.adapter.FileAdapter;
import com.mumu.filebrowser.bean.FileInfo;
import com.mumu.filebrowser.utils.FileHelper;
import com.mumu.filebrowser.utils.IntentHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.sax.RootElement;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,OnItemClickListener, OnItemLongClickListener{
	
	final String ROOT_PATH = Environment.getExternalStorageDirectory().toString();

	private ArrayList<FileInfo> mFileInfoList;
	private ImageView mBackButton;
	private TextView mFilePath;
	private ListView mListView;
	private FileAdapter adapter;
	private ImageView mBackSignBitmap;

	private AlertDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {		// 透明状态栏
			getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		setContentView(R.layout.activity_main);
		init();
	}
	
	void init(){
		mBackSignBitmap = (ImageView) findViewById(R.id.back_sign_img);
		mFilePath = (TextView) findViewById(R.id.file_path);
		mBackButton = (ImageView)findViewById(R.id.back_btn);
		mListView = (ListView) findViewById(R.id.file_list);

		mBackSignBitmap.setVisibility(View.INVISIBLE);
		mFilePath.setText(ROOT_PATH);
		mBackButton.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mFileInfoList = new ArrayList<FileInfo>();
		FileHelper.getDirectionFileList(this, ROOT_PATH, mFileInfoList);
		adapter = new FileAdapter(getApplicationContext(), mFileInfoList);
		mListView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.dialog_delete:
			if(dialog != null){
				dialog.dismiss();
				Toast.makeText(MainActivity.this, "delete", 1).show();
			}
			break;
			
		case R.id.dialog_copy:
			if(dialog != null){
				dialog.dismiss();
				Toast.makeText(MainActivity.this, "copy", 1).show();
			}
			break;

		case R.id.dialog_move:
			if(dialog != null){
				dialog.dismiss();
				Toast.makeText(MainActivity.this, "move", 1).show();
			}
			break;
			
		default:
			String backpath = (String) mBackSignBitmap.getTag();
			if (backpath != null && backpath.length() > 0) {
				FileHelper.getDirectionFileList(getApplicationContext(), backpath, mFileInfoList);
				mBackSignBitmap.setVisibility(View.VISIBLE);
				if (backpath != null && !backpath.equals(ROOT_PATH)) {
					mBackSignBitmap.setTag(backpath.substring(0, backpath.lastIndexOf("/")));
				} else {
					mBackSignBitmap.setTag(ROOT_PATH);
				}

				mFilePath.setText(backpath);
				adapter.notifyDataSetChanged();
			}

			if (mFilePath.getText().toString().equals(ROOT_PATH)) {
				mBackSignBitmap.setVisibility(View.INVISIBLE);
			}
			
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		
		FileInfo fi = mFileInfoList.get(position);
		
		switch (fi.getType()) {
		case FileInfo.FILE_TYPE_DIR:
			// 点击文件夹条目，进入该文件夹，同时设置路径及返回按钮
			FileHelper.getDirectionFileList(getApplicationContext(), fi.getFilePath(), mFileInfoList);
			mBackSignBitmap.setVisibility(View.VISIBLE);
			mBackSignBitmap.setTag(fi.getFilePath().substring(0, fi.getFilePath().lastIndexOf("/")));
			mFilePath.setText(fi.getFilePath());
			adapter.notifyDataSetChanged();
			break;
			
		case FileInfo.FILE_TYPE_DOC:
		case FileInfo.FILE_TYPE_PDF:
		case FileInfo.FILE_TYPE_IMAGE:
			try {
				//可能因为手机没有安装对应的软件而打开报错
				Intent intent = IntentHelper.openFile(fi.getFilePath());
				startActivity(intent);
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "打开文件失败", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			Toast.makeText(getApplicationContext(), "不识别的文件类型", Toast.LENGTH_SHORT).show();
			break;
		}

		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//		 长按弹出修改删除等对话框
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		View tv = inflater.inflate(R.layout.dialogview, null);
		TextView tv1 = (TextView) tv.findViewById(R.id.dialog_delete);
		TextView tv2 = (TextView) tv.findViewById(R.id.dialog_copy);
		TextView tv3 = (TextView) tv.findViewById(R.id.dialog_move);
		
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		
		//popupwindow功能，先注销
		//PopupWindow pp = new PopupWindow(MainActivity.this);
		//pp.setContentView(tv);
		//pp.setBackgroundDrawable(getApplication().getResources().getDrawable(R.drawable.local_popup_bg));
		//pp.setWidth(LayoutParams.WRAP_CONTENT);
		//pp.setHeight(LayoutParams.WRAP_CONTENT);
		//pp.setFocusable(true);
		//pp.showAsDropDown(view, view.getWidth() / 3, 0);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setView(tv);
		dialog = builder.create();
		
		dialog.show();
		
		return true;
		
	}
	
}
