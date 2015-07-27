package com.example.administrator.myapplication;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.myapplication.adapter.StudentAdapter;
import com.example.administrator.myapplication.com.example.administrator.myapplication.bean.StudentBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private ListView lv;
    private List<StudentBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        initData();

        final StudentAdapter adapter = new StudentAdapter(list,this);
        lv.setAdapter(adapter);
        //添加点击条目删除功能
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    /**设置数据*/
    private void initData() {
        list = new ArrayList<StudentBean>();
        String sex = "";
        for (int i = 0; i < 15; i++) {
            sex = getSex();
            if (sex.equals("男"))
            list.add(new StudentBean(getHeader(i), "刘小华" + i, sex, getYan(i, sex), getHobby(i), getResources().getColor(R.color.blue)));
            else
            list.add(new StudentBean(getHeader(i), "范小冰" + i, sex, getYan(i, sex), getHobby(i),getResources().getColor(R.color.red)));
        }
    }
    /**获取头像*/
    private int getHeader(int i) {
        int[] headers = {R.mipmap.face1,R.mipmap.face2,R.mipmap.face3,R.mipmap.face4,R.mipmap.face5,R.mipmap.face6,R.mipmap.face7,R.mipmap.face8};
        int nextInt = new Random().nextInt(8);
        return headers[nextInt];
    }

    /**获取性别*/
    private String getSex() {
        String[] sexs = {"男","女"};
        int nextInt = new Random().nextInt(2);
        return sexs[nextInt];
    }
    /**获取颜值*/
    private String getYan(int i, String sex) {
        String yan = "";
        switch (sex) {
            case "男":
                switch (i){
                    case 0:
                        yan = "男神";
                        break;
                    case 1:
                        yan = "男神经";
                        break;
                    case 2:
                        yan = "衰神";
                        break ;
                }
                break;
            case "女":
                switch (i){
                    case 0:
                        yan = "女神";
                        break;
                    case 1:
                        yan = "女神经";
                        break;
                    case 2:
                        yan = "凤姐";
                        break ;
                }
                break;

        }
        return yan;
    }

    /**获取爱好*/
    private String getHobby(int i) {
        String hobby = "";
        switch (i%3){
            case 1:
                hobby="吃饭";
                break;
            case 2:
                hobby = "睡觉";
                break;
            case 0:
                hobby = "打豆豆";
                break;
        }
        return hobby;
    }


}
