package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.GridAdapter;
import com.example.administrator.myapplication.bean.SourceBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/7/23.
 */
public class GridActivity extends ActionBarActivity {
    private int[] imags={R.mipmap.face1,R.mipmap.face2,R.mipmap.face3,R.mipmap.face4,R.mipmap.face5,R.mipmap.face6,R.mipmap.face7,R.mipmap.face8};
    private int[] header={R.mipmap.emoji_1,R.mipmap.emoji_2,R.mipmap.emoji_3,R.mipmap.emoji_5,R.mipmap.emoji_6,R.mipmap.emoji_7,R.mipmap.emoji_8,R.mipmap.emoji_9};
    private String[] name={"刘德华","梁朝伟","成龙","林青霞","张曼玉","张学友","小龙女","过儿"};
    private String[] contents = {"好吃到停不下来","还是不错的,能吃下去","屎一样的菜,谁吃谁倒霉","还不错,下次带朋友们一起来","世间怎么会有如此好吃的东西啊","这个东西还可以吧,大家试试",
            "实在是难吃死了,谁也别吃啊","一点都不卫生,看着就恶心"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("adapter", "创建了gv");
        Toast.makeText(this,"chuangjian",Toast.LENGTH_SHORT).show();;
        setContentView(R.layout.gv);
        GridView gv = (GridView) findViewById(R.id.gv);
        List<SourceBean> data = getData();
        GridAdapter adapter = new GridAdapter(data, this);
        Log.i("adapter","创建了gv");
        gv.setAdapter(adapter);
    }

    /**获取数据*/
    private List<SourceBean> getData() {
        List<SourceBean> list = new ArrayList<SourceBean>();
        for (int i = 0; i < 8; i++) {
            int nextInt = new Random().nextInt(8);
            int next = new Random().nextInt(20)+1;
            list.add(new SourceBean(imags[i],header[i],name[i],nextInt*next,contents[nextInt]));
        }
        return list;
    }
}
