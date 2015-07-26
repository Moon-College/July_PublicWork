package com.tz.file;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tz.file.adapter.FileAdapter;
import com.tz.file.bean.AFile;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    private String path;
    private List<AFile> mDatas = new ArrayList<AFile>();

    private FileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.lv_listview);
        //判断sd卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //得到sd卡路径
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            Toast.makeText(this, "SD 不存在", Toast.LENGTH_SHORT).show();
        }

        initData(path);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AFile mFile = (AFile) mAdapter.getItem(position);
                File file = new File(mFile.getPath());
                if (file.isDirectory()) {
                    initData(file.getAbsolutePath());
                }
            }
        });

    }

    private void initData(String path) {

        mDatas.clear();//清空数据
        File file = new File(path);

        File[] listFiles = file.listFiles();
        if (!file.getParentFile().getAbsolutePath().equals("/")){

            AFile file_up = new AFile(
                    "上一级目录",
                    file.getParentFile().getAbsolutePath(),
                    BitmapFactory.decodeResource(getResources(), R.mipmap.dirs),
                    false
            );
            mDatas.add(file_up);
        }

        for (File f : listFiles) {
            AFile mFile = new AFile();

            mFile.setName(f.getName());
            mFile.setPath(f.getAbsolutePath());
            if (f.isDirectory()) {
                //是文件夹
                mFile.setIsImage(false);
                mFile.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.dirs));
            } else {
                //文件或者图片
                if (f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".jpeg")) {
                    mFile.setIcon(null);
                    mFile.setIsImage(false);
                } else {
                    mFile.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.file));
                    mFile.setIsImage(false);
                }
            }
            mDatas.add(mFile);
        }
        mAdapter = new FileAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

    }

}
