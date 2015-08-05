package com.tz.filesbrowser.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.filesbrowser.R;
import com.tz.filesbrowser.adapter.MyFileAdapter;
import com.tz.filesbrowser.base.BaseActivity;
import com.tz.filesbrowser.bean.MyFile;
import com.tz.filesbrowser.util.Constant;
import com.tz.filesbrowser.util.MyLog;
import com.tz.filesbrowser.util.PopupWindowUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends BaseActivity {
    private List<MyFile> list = new ArrayList<>();
    private ListView lv_file;
    private String pathRoot;
    private MyFileAdapter adapter;
    private TextView titlebarRight;
    private String[] strs = {"设置", "退出"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_main);
        /**
         * 设置父类bar显示
         */
        hideTitlebarLeft();
        setBarTitle("首页");

        /**
         * 初始化内容布局
         */
        initView();
    }


    private void initView() {
        titlebarRight = getTitlebarRight();
        titlebarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> strList = new ArrayList<>();
                Collections.addAll(strList, strs);
                /**
                 * 显示PopupWindow
                 * @param context 上下文
                 * @param v       PopupWindow 内显示的View
                 * @param list    View 中显示的数据  List<String>
                 */
                PopupWindowUtil.showPopupWindow(MainActivity.this, v, strList);
            }
        });

        lv_file = (ListView) findViewById(R.id.lv_file);
        //获取SD卡路径
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            pathRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            Toast.makeText(this, "SD Error", Toast.LENGTH_SHORT).show();
        }

        /**
         * 获取数据
         */
        initData(pathRoot);

        lv_file.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("确认要删除吗？？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyFile myFile = (MyFile) adapter.getItem(position);
                        File file = new File(myFile.getPath());
                        Boolean b = file.delete();
                        if (b) {

                            list.remove(position);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

                return true;
            }
        });

        //ListView点击监听
        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyFile myFile = (MyFile) adapter.getItem(position);
                File file = new File(myFile.getPath());
                if (file.isDirectory()) {
                    /**
                     * 判断是否为SD根目录，为根目录则不能再返回
                     */
                    if (!file.getParentFile().getAbsolutePath().equals("/storage")) {
                        initData(file.getAbsolutePath());
//                        Toast.makeText(MainActivity.this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "已是根目录", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    /**
                     * 分别为图片、视频、音频隐式意图调用系统相册，系统视频播放器，系统音频播放器
                     * 文本 -->使用自定义com.tz.browse.ui.TxtReadActivity 中的textView打开
                     */
                    if (file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg")) {
                        showPicture(file);
                    } else if (file.getName().toLowerCase().endsWith(".txt") || file.getName().toLowerCase().endsWith(".xml")) {
                        showText(file);
                    } else if (file.getName().toLowerCase().endsWith(".mp4") || file.getName().toLowerCase().endsWith(".3gp")) {
                        showVideoMedia(file);
                    } else if (file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".wav") || file.getName().toLowerCase().endsWith(".wma")) {
                        showSoundMedia(file);
                    } else if (file.getName().toLowerCase().endsWith(".apk")) {
                        exeApk(file);
                    }
                }
            }
        });
    }


    /**
     * 安装apk文件
     *
     * @param file
     */
    private void exeApk(File file) {
        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.fromFile(file));
        startActivity(intent);
    }

    /**
     * 使用系统音频播放器打开
     *
     * @param file
     */
    private void showSoundMedia(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "audio/*");
        startActivity(intent);
    }

    /**
     * 使用系统视频播放器打开
     *
     * @param file
     */
    private void showVideoMedia(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "video/*");
        startActivity(intent);
    }

    /**
     * 使用文本浏览器打开 （多个APP）
     *
     * @param file
     */
    private void showText(File file) {
        MyLog.i(Constant.TAG, file.toString());
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri1 = Uri.fromFile(file);
        MyLog.i(Constant.TAG, uri1.toString());
        intent.setDataAndType(uri1, "text/*");
        MyLog.i(Constant.TAG, intent.getData().getPath());
        startActivity(intent);
    }

    /**
     * 使用系统相册打开
     *
     * @param file
     */
    private void showPicture(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        startActivity(intent);

    }

    /**
     * 遍历SD卡获取数据
     *
     * @param path
     */
    private void initData(String path) {
        list.clear();
        //遍历SD卡
        File file = new File(path);
        File[] files = file.listFiles();

        MyFile file_back = new MyFile(
                "返回",
                file.getParentFile().getAbsolutePath(),
                BitmapFactory.decodeResource(getResources(), R.drawable.dirs),
                false
        );
        list.add(file_back);

        for (File f : files) {
            MyFile myFile = new MyFile();
            myFile.setName(f.getName());
            myFile.setPath(f.getAbsolutePath());
            if (f.isDirectory()) {
                myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
                myFile.setIsImage(false);
            } else {
                if (f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".jpeg")) {
                    myFile.setIcon(null);
                    myFile.setIsImage(true);
                } else if (f.getName().toLowerCase().endsWith(".txt") || f.getName().toLowerCase().endsWith(".xml")) {
                    myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.txt));
                    myFile.setIsImage(false);
                } else if (f.getName().toLowerCase().endsWith(".mp4") || f.getName().toLowerCase().endsWith(".3gp")) {
                    /**
                     * 加载视频预览图片
                     */
                    myFile.setIcon(getVideoThumbnail(f.getPath()));
                    myFile.setIsImage(false);
                } else if (f.getName().toLowerCase().endsWith(".apk")) {
                    myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                    myFile.setIsImage(false);
                } else {
                    myFile.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.file));
                    myFile.setIsImage(false);
                }
            }
            list.add(myFile);
        }


        adapter = new MyFileAdapter(list, this);
        lv_file.setAdapter(adapter);

    }

    /**
     * 获取视频预览图片
     *
     * @param filePath
     * @return Bitmap
     */
    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        PopupWindowUtil.closePopupWindow();
        super.onDestroy();
    }
}
