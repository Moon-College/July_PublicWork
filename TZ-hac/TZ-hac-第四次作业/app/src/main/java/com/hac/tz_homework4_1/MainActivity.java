//TZ-hac 第四次作业 菜谱 2015.7.23
package com.hac.tz_homework4_1;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    GridView gv;
    List<Item> itemList;
    GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //对控件及数据的初始化
        init();
    }

    private void init() {
        gv = (GridView) findViewById(R.id.gv);
        itemList = new ArrayList<Item>();

        //向list中添加数据
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));
        itemList.add(new Item("小炒牛肉", "hac", 20, BitmapFactory.decodeResource(getResources(), R.drawable.img_photo), BitmapFactory.decodeResource(getResources(), R.drawable.img_beef)));

        adapter = new GridViewAdapter(this, itemList);

        //为gridview设置适配器
        gv.setAdapter(adapter);

    }


}
