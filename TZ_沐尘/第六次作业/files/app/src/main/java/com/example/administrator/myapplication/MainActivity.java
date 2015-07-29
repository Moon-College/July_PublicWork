package com.example.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.MyAdapter;
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
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(MainActivity.this);
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

                showPopupWindow(myAdapter,view,position);
               /* FilesBean bean = list.get(position);
                if (bean.path.isDirectory()){
                getFilePath(bean.path);}
                //通知适配器更新数据
                myAdapter.notifyDataSetChanged();;*/
            }
        });
        myAdapter.setOnLongClickListener(new MyAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                showDialog(myAdapter,position);
            }
        });
    }
    /**创建popupwindow
     * @param myAdapter
     * @param view
     * @param position */
    private void showPopupWindow(MyAdapter myAdapter, View view, int position) {
        //获取到图片的宽度
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        //创建popup要显示的view样子
        View inflate = inflater.inflate(R.layout.item_popup, null, false);
        //创建popupwindow
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT
                ,height);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        int[] mLocation = new int[2];
        view.getLocationInWindow(mLocation);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.TOP, mLocation[0] + width, mLocation[1]);
        //给popupwindow添加动画
        ScaleAnimation animation = new ScaleAnimation(0.2f,1.0f,0.2f,1.0f,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0.5f
        );
        animation.setDuration(1000);
        inflate.startAnimation(animation);
        //给popuw的子控件设置功能
        setOnClick(myAdapter, inflate, position, popupWindow);
    }
    /**给popupwindow设置点击事件*/
    private void setOnClick(final MyAdapter myAdapter, View inflate, final int position, final PopupWindow popupWindow) {
        ImageView image1 = (ImageView) inflate.findViewById(R.id.img1);
        ImageView image2 = (ImageView) inflate.findViewById(R.id.img2);
        final ImageView image3 = (ImageView) inflate.findViewById(R.id.img3);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"第三个图标被隐藏了",Toast.LENGTH_SHORT).show();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "第三个图标出现了", Toast.LENGTH_SHORT).show();
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilesBean bean = list.get(position);
                if (bean.path.isDirectory()){
                    getFilePath(bean.path);}
                //通知适配器更新数据
                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"进入下个界面了",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }

    private void showDialog(final MyAdapter myAdapter, final int position) {
//        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
         AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.create();
        builder.setTitle("提示");
        builder.setMessage("您是否要删除该条目");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                myAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//        alertDialog.show();
        builder.show();

//        dialog.show();
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
