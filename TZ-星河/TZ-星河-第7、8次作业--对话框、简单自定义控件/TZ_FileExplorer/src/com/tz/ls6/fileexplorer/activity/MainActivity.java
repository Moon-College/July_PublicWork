package com.tz.ls6.fileexplorer.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tz_fileexplorer.R;
import com.tz.ls6.fileexplorer.adapter.FileListViewAdapter;
import com.tz.ls6.fileexplorer.entity.MyFile;

public class MainActivity extends Activity{
	private ListView lvFiles;
	private TextView tvBack;
	private String rootPath=null;
	private List<MyFile> files=new ArrayList<MyFile>();
	private FileListViewAdapter adapter=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		lvFiles.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long itemId) {
				MyFile mFile=files.get(position);
				File file=new File(mFile.getPath());
				if(file.isDirectory()){
					getFileList(mFile.getPath());
				}
			}
		});
		
		lvFiles.setOnItemLongClickListener(new OnItemLongClickListener() {
			private LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					final int position, long itemId) {
				MyFile mFile=files.get(position);
				String fileName=mFile.getFileName();
				
				if(fileName.toLowerCase().endsWith(".png")
						||fileName.toLowerCase().endsWith(".jpg")
						||fileName.toLowerCase().endsWith(".jpeg")){
					final File file=new File(mFile.getPath());
					View contentView=inflater.inflate(R.layout.list_item_pop, null);
					final PopupWindow pw=new PopupWindow(contentView,
							LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
					pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
					pw.showAsDropDown(view, 100, 0);
					contentView.findViewById(R.id.bt_view).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent=new Intent();
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.setAction(Intent.ACTION_VIEW);
							intent.setDataAndType(Uri.fromFile(file),"image/*");
							startActivity(intent);
						}
					});
					contentView.findViewById(R.id.bt_delete).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
							final AlertDialog alDlg=builder.create();
							builder.setTitle("警告");
							builder.setMessage("确定删除该文件?");
							builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									file.delete();
									files.remove(position);
									adapter.notifyDataSetChanged();
									alDlg.dismiss();
								}
							});
							
							builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									alDlg.dismiss();
								}
							});
							builder.show();
							
							if(pw.isShowing()){
								pw.dismiss();
							}
						}
					});
				}
				return false;
			}
		});
		
		tvBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String parent=(String) tvBack.getTag();
				getFileList(parent);
			}
		});

		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
			rootPath=sdPath;
			tvBack.setTag(rootPath);
			Log.i("SD PATH", rootPath);
			getFileList(sdPath);
		}else{
			Toast.makeText(this, "没有插入SD卡", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void getFileList(String path) {
		files.clear();
		File root=new File(path);
		System.out.println(path);
		if(!path.equals(rootPath)){
			tvBack.setTag(root.getParent());
		}else{
			tvBack.setTag(path);
		}
		if(root.exists()&&root.isDirectory()){
			File[] childs=root.listFiles();
			for(File child:childs){
				if(!child.exists()){
					continue;
				}
				MyFile mFile=new MyFile();
				String fileName=child.getName();
				mFile.setFileName(fileName);
				mFile.setPath(child.getAbsolutePath());
				if(child.isFile()){
					if(fileName.toLowerCase().endsWith(".png")
							||fileName.toLowerCase().endsWith(".jpg")
							||fileName.toLowerCase().endsWith(".jpeg")){
						mFile.setIcon(null);
					}else{
						Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.file);
						mFile.setIcon(bm);
					}
				}else{
					Bitmap bm=BitmapFactory.decodeResource(getResources(), R.drawable.dir);
					mFile.setIcon(bm);
				}
				files.add(mFile);
			}
			adapter=new FileListViewAdapter(files, this);
			lvFiles.setAdapter(adapter);
		}
	}
	
	private void initView() {
		lvFiles=(ListView) findViewById(R.id.lv_filelist);
		tvBack=(TextView) findViewById(R.id.tv_back);
	}
}
