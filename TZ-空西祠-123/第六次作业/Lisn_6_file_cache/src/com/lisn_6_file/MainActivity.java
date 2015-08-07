package com.lisn_6_file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lisn_6_file.adapter.Fileadapter;
import com.lisn_6_file.bean.MyFile;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 
 * 高性能图片浏览器
 *
 */
public class MainActivity extends Activity {
private List<MyFile> list=new ArrayList<MyFile>();
private ListView lv;
private String roopath;
private Fileadapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lv);
		//判断sd卡是否正常挂载
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//得到sd卡根目录
			roopath=Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Toast.makeText(this, "SD 错误",Toast.LENGTH_SHORT).show();
		}
		initData(roopath);
	
		//遍历sd卡
	    lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//判断是否为文件夹
				MyFile myfile=(MyFile) adapter.getItem(position);
				File file=new File(myfile.getPath());//获取文件夹路径
				if(file.isDirectory()){
					initData(myfile.getPath());
					//adapter.notifyDataSetChanged();
				}
			}
		});

	}
	private void initData(String roopath) {
		list.clear();
		// TODO Auto-generated method stub
		File file=new File(roopath);//读取
		File[] listFiles=file.listFiles();//获取当前路径下的所有文件
		MyFile fileback=new MyFile("返回", BitmapFactory.decodeResource(this.getResources(), R.drawable.dirs), file.getParentFile().getAbsolutePath(), false);//根据图片生成位图对象，给返回赋一个文件夹路径
		list.add(fileback);
		for(File f:listFiles){
			MyFile myfile=new MyFile();
			myfile.setName(f.getName());
			myfile.setPath(f.getAbsolutePath());
			if(f.isDirectory()){//是文件夹
				myfile.setBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.dirs));
				myfile.setIcon(false);
			}else{
				//文件
				//图片
				if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".jpeg")||f.getName().toLowerCase().endsWith(".ico")){
					myfile.setIcon(true);
					myfile.setBitmap(null);
				}else{
					myfile.setBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.file));
					myfile.setIcon(false);
				}
			}
			list.add(myfile);
		}
		adapter=new Fileadapter(list, this);
	    lv.setAdapter(adapter);
	}

}
