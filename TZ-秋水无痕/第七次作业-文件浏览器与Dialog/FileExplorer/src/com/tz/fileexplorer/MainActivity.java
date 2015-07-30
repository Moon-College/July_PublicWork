
package com.tz.fileexplorer;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView lv;
    private String rootPath;
    private List<MyFile> list = new ArrayList<MyFile>();
    private FileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv =(ListView) findViewById(R.id.lv);
		 //判断SD卡是否正常挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //得到sd卡根目录
            rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }else {
            Toast.makeText(this, "SD Error", 0).show();
        }
        
        initData(rootPath);
    
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                MyFile myFile = (MyFile) adapter.getItem(position);
                File file = new File(myFile.getPath());
                if (file.isDirectory()) {
                    initData(file.getAbsolutePath());
                }
            }
        });
       
	   }

    private void initData(String path) {
        list.clear();
        // 遍历SD卡
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
            if (f.isDirectory()) {//是文件夹
                myFile.setPic(false);
                myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
            }else {//文件，图片
                if (f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".jpeg")) {
                    myFile.setPic(true);
                    myFile.setIcon(null);
                }else {//其他文件
                    myFile.setPic(false);
                    myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
                }
            }
            list.add(myFile);
        }
        adapter = new FileAdapter(list,this);
        lv.setAdapter(adapter);
    }

   
}
