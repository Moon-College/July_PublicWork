package com.mumu.filebrowser.activity;

import java.util.ArrayList;
import java.util.List;

import com.mumu.filebrowser.R;
import com.mumu.filebrowser.R.layout;
import com.mumu.filebrowser.adapter.FileAdapter;
import com.mumu.filebrowser.bean.FileInfo;
import com.mumu.filebrowser.utils.FileHelper;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.sax.RootElement;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,OnItemClickListener{
	
	final String ROOT_PATH = Environment.getExternalStorageDirectory().toString();

	private ArrayList<FileInfo> mFileInfoList;
	private ImageView mBackButton;
	private TextView mFilePath;
	private ListView mListView;
	private FileAdapter adapter;
	private ImageView mBackSignBitmap;
	
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
		mFileInfoList = new ArrayList<FileInfo>();
		FileHelper.getDirectionFileList(this, ROOT_PATH, mFileInfoList);
		adapter = new FileAdapter(getApplicationContext(), mFileInfoList);
		mListView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		String backpath = (String)mBackSignBitmap.getTag();
		if(backpath != null && backpath.length() > 0){
			FileHelper.getDirectionFileList(getApplicationContext(), backpath, mFileInfoList);
			mBackSignBitmap.setVisibility(View.VISIBLE);
			if(backpath != null && !backpath.equals(ROOT_PATH)){
				mBackSignBitmap.setTag(backpath.substring(0, backpath.lastIndexOf("/")));
			}else{
				mBackSignBitmap.setTag(ROOT_PATH);
			}
			
			mFilePath.setText(backpath);
			adapter.notifyDataSetChanged();
		}
		
		if(mFilePath.getText().toString().equals(ROOT_PATH)){
			mBackSignBitmap.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// 点击文件夹条目，进入该文件夹，同时设置路径及返回按钮
		FileInfo fi = mFileInfoList.get(position);
		if(fi.getType() == FileInfo.FILE_TYPE_DIR){
			FileHelper.getDirectionFileList(getApplicationContext(), fi.getFilePath(), mFileInfoList);
			mBackSignBitmap.setVisibility(View.VISIBLE);
			mBackSignBitmap.setTag(fi.getFilePath().substring(0, fi.getFilePath().lastIndexOf("/")));
			mFilePath.setText(fi.getFilePath());
			adapter.notifyDataSetChanged();
		}else{
			Toast.makeText(getApplicationContext(), "不是一个文件夹， position = " + position, Toast.LENGTH_SHORT).show();
		}
		
	}
	
}
