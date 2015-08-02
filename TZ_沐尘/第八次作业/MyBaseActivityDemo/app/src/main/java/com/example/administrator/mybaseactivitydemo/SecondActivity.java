package com.example.administrator.mybaseactivitydemo;

import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2015/8/2 0002.
 */
public class SecondActivity extends BaseActivity {

    private Button button;

    @Override
    public void initView() {
        button = new Button(this);
        button.setText("点击关闭界面");
        setLayoutView(button);
    }

    @Override
    public void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
