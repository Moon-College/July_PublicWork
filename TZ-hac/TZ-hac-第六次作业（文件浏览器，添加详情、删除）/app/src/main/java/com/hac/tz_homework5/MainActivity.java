package com.hac.tz_homework5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.hac.tz_homework5.model.Item;
import com.hac.tz_homework5.util.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView lv_file;

    MyAdapter adapter;
    List<Item> itemList = new ArrayList<Item>();
    String rootPath;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        lv_file = (ListView) findViewById(R.id.lv_file);

        //判断是否有SD卡
        if(checkSDCard()) {
            rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            //显示路径下的文件
            showFiles(rootPath, true);
        }
        else {
            Toast.makeText(getApplicationContext(), "SD卡未插入", Toast.LENGTH_SHORT).show();
        }

        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = itemList.get(position);
                //若点击的项为文件夹，则进入
                if(item.isDir()) {
                    showFiles(item.getDir(), false);
                }
            }
        });

        //设置长按事件
        lv_file.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //显示popupwindow
                showPopup(view, position);
                return true;
            }
        });
    }

    /**
     * 显示popupwindow的方法
     * @param view parent view
     * @param position 长按的item的位置
     */
    private void showPopup(View view, int position) {
        //自定义View
        View contentView = View.inflate(this, R.layout.popup, null);
        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        //得到详情、删除按钮并设置监听事件
        TextView tv_detail = (TextView) contentView.findViewById(R.id.tv_detail);
        TextView tv_delete = (TextView) contentView.findViewById(R.id.tv_delete);
        tv_detail.setOnClickListener(new PopupListener(position));
        tv_delete.setOnClickListener(new PopupListener(position));
        int []location = new int[2];
        view.getLocationInWindow(location);
        popupWindow.showAtLocation(view, Gravity.LEFT|Gravity.TOP, location[0]+150, location[1]);
    }

    class PopupListener implements View.OnClickListener {
        int position;

        public PopupListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            //显示对话框
            mShowDialog(v.getId(), position);
        }
    }

    /**
     * @param id 点击的按钮的id，用于判断点击的按钮是详情还是删除
     * @param position 长按的item的位置
     */
    private void mShowDialog(int id, int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Item item = itemList.get(position);

        switch (id) {
            //点击详情按钮
            case R.id.tv_detail:
                item.setSize(FileUtil.getSize(item.getDir()));
                View view = View.inflate(this, R.layout.dialog_detail, null);
                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                TextView tv_path = (TextView) view.findViewById(R.id.tv_path);
                TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
                tv_name.setText("文件(夹)名：" + item.getName());
                tv_path.setText("路径： " + item.getDir());
                tv_size.setText("大小：" + FileUtil.getFormatedSize(item.getSize(), null));
                builder.setTitle("文件(夹)详情");
                builder.setView(view);
                builder.setPositiveButton("确定", null);
                builder.show();
                break;
            //点击删除按钮
            case R.id.tv_delete:
                builder.setTitle("提示");
                builder.setMessage("确定要删除文件(夹)吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除文件（夹）
                        FileUtil.deleteFile(item.getDir());
                        itemList.remove(item);
                        //通知更新
                        adapter.notifyDataSetChanged();
                        //是popupwindow消失
                        popupWindow.dismiss();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
        }
    }
    /**
     * 显示path下的文件
     * @param path 文件夹路径
     * @param isFirst 是否是第一次加载，第一次加载需要new adapter，不是的话调用notifydatasetchanged
     */
    private void showFiles(String path, boolean isFirst) {
        File file = new File(path);
        Item item;

        //如果file不为空
        if(file != null) {
            File []files = file.listFiles();

            if(files != null) {
                Log.i("MainActivity", "files not null");
                //清空列表，防止重复添加
                itemList.clear();
                if(file.getParentFile() != null) {
                    Item back = new Item(file.getParentFile().getAbsolutePath(), true, false, "返回");
                    back.setIsDir(true);
                    itemList.add(back);
                }

                //遍历该路径下的所有内容（文件、文件夹、图片）
                for(int i=0; i<files.length; i++) {
                    item = new Item();
                    item.setName(files[i].getName());
                    item.setDir(files[i].getAbsolutePath());

                    //如果为文件夹
                    if(files[i].isDirectory()) {
                        item.setIsDir(true);
                        item.setIsFile(false);
                        item.setIsPic(false);
                        //item.setSize(FileUtil.getSize(files[i], true));
                    }
                    else {
                        item.setIsDir(false);
                        //如果为图片
                        //item.setSize(files[i].length() / 1024);
                        if(files[i].getName().toLowerCase().endsWith("jpg") || (files[i].getName().toLowerCase().endsWith("png"))) {
                            item.setIsFile(false);
                            item.setIsPic(true);
                        }
                        //如果为文件
                        else {
                            item.setIsFile(true);
                            item.setIsPic(false);
                        }
                    }
                    //添加到list中
                    itemList.add(item);
                }

                //设置适配器
                if(isFirst) {
                    adapter = new MyAdapter(this, itemList);
                    lv_file.setAdapter(adapter);
                }
                else
                    adapter.notifyDataSetChanged();
            }
        }
    }

     /**
     * @return 是否有SD卡
     */
    private boolean checkSDCard() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
