package com.tz.lsn6;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.lsn6.adapter.FileExplorerAdapter;
import com.tz.lsn6.bean.MyFile;

/**
 * 高性能文件浏览器 (侧重浏览图片)
 * @author dallon2
 *
 */
public class MainActivity extends Activity {

    private ListView lv;
	private FileExplorerAdapter adapter;
	/* SD Card 根目录 绝对路径  */
	private String rootPath = null;
	private List<MyFile> list  = new ArrayList<MyFile>();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);
    }
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		//判断SD卡是否正常挂在
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			
		} else {
			Toast.makeText(this, "SD Card 没有正常挂载 rootPath:" + rootPath, Toast.LENGTH_SHORT).show();
			return;
		}
		
		initData(rootPath);
	}
	
	// 初始化数据 传入的必须是文件夹节点
	private void initData(String fileNode) {
		
		//清理数据列表
		list.clear();
		
		//文件 节点
		File pfile = new File(fileNode);
		//将节点下面的文件及文件夹（不包含子目录下面的）生成数组
		File[] listFiles = pfile.listFiles();
		//最上级节点不需要有 返回 菜单
		if(fileNode != null && !fileNode.equals(rootPath)) {
			list.add(new MyFile("返回", 
					BitmapFactory.decodeResource(getResources(),R.drawable.back), 
					//获取 当前节点 的上级节点绝对路径
					pfile.getParentFile().getAbsolutePath(), false));
		}
		
		for (File file:listFiles) {
			
			MyFile myFile = new MyFile();
			
			myFile.setName(file.getName());
			myFile.setPath(file.getAbsolutePath());
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
			
			list.add(myFile);
		}
		//创建文件列表适配器adapter
		adapter = new FileExplorerAdapter(this, list);
		//适配器加载视图
		lv.setAdapter(adapter);
		//视图设置条目监听
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				MyFile myFile = list.get(position);
				File file = new File(myFile.getPath());
				if(file.isDirectory()) {
					//如果是文件夹节点，则进入下一层，并初始化数据
					initData(myFile.getPath());
				} else {
					Toast.makeText(MainActivity.this, "阅读器待开发", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
