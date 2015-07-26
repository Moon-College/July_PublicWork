package com.example.administrator.myapplication;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.MyAdapter;
import com.example.administrator.myapplication.adapter.utils.ImageLoader;
import com.example.administrator.myapplication.bean.FilesBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyView;
    private ProgressBar mProgressBar;
    private String externalStorageState;
    private List<FilesBean> list = new ArrayList<FilesBean>();
    private File[] listFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAllViews();

        //设置manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置recyclerView的方向
        recyView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //不设定固定大小
        recyView.setHasFixedSize(false);

        //准备数据
        getData();

        final MyAdapter myAdapter = new MyAdapter(list);
        recyView.setAdapter(myAdapter);
        //添加条目的点击事件
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FilesBean bean = list.get(position);
                if (bean.path.isDirectory()){
                getFilePath(bean.path);}
                //通知适配器更新数据
                myAdapter.notifyDataSetChanged();;
            }
        });
    }

    /**
     * 获取数据
     */
    private void getData() {
        //获取系统的外部存储卡
        externalStorageState = Environment.getExternalStorageState();
        Log.i("MEPAI", "去获取存储卡" + externalStorageState);
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            File directory = Environment.getExternalStorageDirectory();

            getFilePath(directory);

        }


        return;
    }

    /**
     * 获取文件
     */
    private void getFilePath(File file) {
        File[] listFiles = file.listFiles();
        list.clear();
        FilesBean bean = new FilesBean();
        bean.path = file.getParentFile();
        bean.bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bean.name = "返回";
        list.add(bean);
        for (File files : listFiles) {
//            Log.i("MEPAI",files.getName());
            FilesBean filesBean = new FilesBean();
            filesBean.name = files.getName();
            filesBean.path = files;
            if (files.isDirectory()){
                filesBean.bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.dirs);
            }else{
                String name = files.getName();
                if (name.endsWith(".jpeg")||name.endsWith(".png")||name.endsWith(".jpg")){
                    filesBean.bitmap = null;
                }else{
                filesBean.bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.file);
                }
            }
            list.add(filesBean);
        }
    }

    private void initAllViews() {
        recyView = (RecyclerView) findViewById(R.id.recyView);
        recyView.setVisibility(View.VISIBLE);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
        mProgressBar.setVisibility(View.GONE);
    }


}
