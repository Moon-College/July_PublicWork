package com.example.administrator.mybaseactivitydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/8/2 0002.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private TextView menu;
    private TextView titles;
    private TextView finish;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finish = (TextView) findViewById(R.id.finish);
        titles = (TextView) findViewById(R.id.title);
        menu = (TextView) findViewById(R.id.menu);
        container = (LinearLayout) findViewById(R.id.container);
        initView();
        setListener();
    }
    public abstract  void initView();
    public abstract  void setListener();
    protected void setLayoutView(View view) {
        container.addView(view);
    }


    public TextView getFinish() {
        return finish;
    }

    public TextView getMenu() {
        return menu;
    }

    public TextView getTitles() {
        return titles;
    }
}
