package com.tz.lsn6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.lsn6.Util.FunctionHelper;
import com.tz.lsn6.Util.SDCardUtils;
import com.tz.lsn6.adapter.FileExplorerAdapter;
import com.tz.lsn6.bean.MyFile;
import com.tz.lsn6.fileexplorer.R;
import com.tz.lsn6.listener.OnItemButtonClickListener;

/**
 * 高性能文件浏览器 (侧重浏览图片)
 * @author dallon2
 *
 */
public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener, OnItemButtonClickListener {
	/* ListView */
    private ListView lv;
    /* 文件列表适配器 */ 
	private FileExplorerAdapter fileListAdapter;
	/* SD Card 根目录 绝对路径  */
	private String rootPath = null;
	/* 数据源 */
	private List<MyFile> mData  = new ArrayList<MyFile>();
	
	public static final String FILE_TYPE_IMG = "img";
	public static final String FILE_TYPE_TXT = "txt";
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }
	
	private void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.lv);
	}

	private void initData() {
		// TODO Auto-generated method stub
		rootPath = SDCardUtils.getRootFilePath(this);
		
		//初始化路径为rootPath的目录
		initFileData(rootPath);
		
	}
	
	/**
	 * 清空数据
	 */
	private void clearData() {
		// TODO Auto-generated method stub
		if(mData != null && mData.size() != 0) {
			mData.removeAll(mData);
			mData.clear();
		}
	}
	
	/**
	 * 初始化数据 传入的必须是文件夹节点
	 * @param fileNode
	 */
	private void initFileData(String fileNode) {
		
		//清理数据列表
		clearData();
		
		try {
			//文件 节点
			File pfile = new File(fileNode);
			//将节点下面的文件及文件夹（不包含子目录下面的）生成数组
			File[] listFiles = pfile.listFiles();
			//添加返回目录
			mData.add(0, new MyFile("返回", 
					BitmapFactory.decodeResource(getResources(),R.drawable.back), 
					//获取 当前节点 的上级节点绝对路径
					pfile.getParentFile().getAbsolutePath(), false));
			
			MyFile myFile = null;
			for (File file:listFiles) {
				myFile = new MyFile();
				//文件名
				myFile.setName(file.getName());
				//文件绝对路径
				myFile.setPath(file.getAbsolutePath());
				
				//文件最后修改时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				myFile.setModifyTime(sdf.format(new Date(file.lastModified())));

				//如果是文件夹
				if(file.isDirectory()) {
					//设置为文件夹的图片
					myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
					//设置为非图片
					myFile.setIsImg(false);
				} else {
					if(file.getName() != null 
						&& (file.getName().toLowerCase().endsWith(".jpg")
							||file.getName().toLowerCase().endsWith(".jpeg")
							||file.getName().toLowerCase().endsWith(".png"))) {
						//使用缓存技术,这里不加载图片
						//myFile.setIcon(BitmapFactory.decodeFile(file.getAbsolutePath()));
						myFile.setIcon(null);
						//设置为图片
						myFile.setIsImg(true);
					} else {
						myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
						//设置为非图片
						myFile.setIsImg(false);
					}
				}
				
				mData.add(myFile);
			}
		} catch(Exception e) {
			// TODO: handle exception
		}
		
		//创建文件列表适配器adapter
		fileListAdapter = new FileExplorerAdapter(this, mData);

		//适配器加载视图
		lv.setAdapter(fileListAdapter);
		//设置条目单击监听
		lv.setOnItemClickListener(this);
		//设置条目长按监听
		lv.setOnItemLongClickListener(this);

		//设置条目长按监听
		fileListAdapter.setOnItemButtonClickListener(this);
		
	}

	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>onItemClick->position:" + position + " id:" + id);
		MyFile myFile = mData.get(position);
		File file = new File(myFile.getPath());
		if(file.isDirectory()) {
			//如果是文件夹节点，则进入下一层，并初始化数据
			initFileData(myFile.getPath());
		} else if(file.getParentFile() == null){
			Toast.makeText(MainActivity.this, "无上级目录", Toast.LENGTH_SHORT).show();
			return;
		} else {
			//Toast.makeText(MainActivity.this, "阅读器待开发", Toast.LENGTH_SHORT).show();
//			Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
//          startActivityForResult(intent, 1);
			
			//如果是图片则读取图片
			if(myFile.isImg()) {
				Intent intent = new Intent();
				intent.setAction("ACTION_PICK");
				intent.putExtra("path", myFile.getPath());
				intent.putExtra("type", FILE_TYPE_IMG);
				startActivity(intent);
			} else {
				//如果是txt/html/xml 类型的文件则打开
				String path = myFile.getPath().toLowerCase();
				if(path.endsWith(".txt") || path.endsWith(".html") || path.endsWith(".xml")|| path.endsWith(".cfg")) {
					Intent intent = new Intent();
					intent.setAction("ACTION_PICK");
					intent.putExtra("path", myFile.getPath());
					intent.putExtra("type", FILE_TYPE_TXT);
					startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this, "未知文件", Toast.LENGTH_SHORT).show();
				}
			}
		}
		
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>onItemLongClick->position:" + position + " id:" + id);
		final int location = position;
		MyFile myFile = mData.get(location);
		
		//如果是路径 暂时先不处理
		File file = new File(myFile.getPath());
		if(file.isDirectory() || "返回".equals(myFile.getName())) {
			Toast.makeText(this, "当前是文件夹或返回", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("确定要删除" + myFile.getName() + (file.isDirectory()?"文件夹":"文件"));
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mData.remove(location);
				fileListAdapter.notifyDataSetChanged();
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.show();
		
		return false;
	}

	@Override
	public void onClick(View view, int position, long id) {
		// TODO Auto-generated method stub
		MyFile myFile = mData.get(position);
		File file = new File(myFile.getPath());
		if(file.isDirectory()) {
			//如果是文件夹节点，则进入下一层，并初始化数据
			initFileData(myFile.getPath());
		} else {
			//Toast.makeText(MainActivity.this, "阅读器待开发", Toast.LENGTH_SHORT).show();
			LayoutInflater layoutInflater = LayoutInflater.from(this);
			View v = layoutInflater.inflate(R.layout.popup_item, null);
			
			PopupWindow pw = new PopupWindow(v, LayoutParams.MATCH_PARENT, 400, true);
			pw.setBackgroundDrawable(getResources().getDrawable(R.drawable.local_popup_bg));
			
			TextView tv_fileName = (TextView)v.findViewById(R.id.tv_fileName);
			tv_fileName.setText(myFile.getName());
			
			TextView tv_filePath = (TextView)v.findViewById(R.id.tv_filePath);
			tv_filePath.setText(myFile.getPath());
			
			TextView tv_modifyTime = (TextView)v.findViewById(R.id.tv_modifyTime);
			tv_modifyTime.setText(myFile.getModifyTime());
			
			//获得Item在屏蔽上的坐标位置
			int[] location = new int[2];
			view.getLocationInWindow(location);
			
			int windowWidth = FunctionHelper.getWindowWidth(this);
			pw.showAtLocation(view, Gravity.TOP | Gravity.LEFT, location[0] + windowWidth/8, location[1] - 100);
		}
	}



}
