package com.hac.tz_homework5;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hac.tz_homework5.model.Item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView lv_file;

    MyAdapter adapter;
    List<Item> itemList = new ArrayList<Item>();
    String rootPath;

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
            showFiles(rootPath, true);
        }
        else {
            Toast.makeText(getApplicationContext(), "SD卡未插入", Toast.LENGTH_SHORT).show();
        }

        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = itemList.get(position);
                if(item.isDir()) {
                    showFiles(item.getDir(), false);
                }
            }
        });
    }


    /**
     * 显示path下的文件
     * @param path 文件夹路径
     * @param isFirst 是否是第一次加载，第一次加载需要new adapter，不是的话调用notifydatasetchanged
     */
    private void showFiles(String path, boolean isFirst) {
        //清空列表，防止重复添加
        itemList.clear();
        File file = new File(path);
        Item item;

        //如果file不为空
        if(file != null) {
            File []files = file.listFiles();

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
                }
                else {
                    item.setIsDir(false);
                    //如果为图片
                    if(files[i].getName().toLowerCase().endsWith("jpg") || (files[i].getName().toLowerCase().endsWith("png"))) {
                        item.setIsFile(false);
                        item.setIsPic(true);
                    }
                    //如果为文件
                    else
                        item.setIsFile(true);
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
