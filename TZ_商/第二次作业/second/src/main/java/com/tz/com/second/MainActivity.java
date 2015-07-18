package com.tz.com.second;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button mBtnText;
    private Button mBtnQQ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
    }
    /*
    * 初始化视图
    * */
    private void initViews() {
        mBtnText = (Button) findViewById(R.id.btn_text);
        mBtnQQ = (Button) findViewById(R.id.btn_qq);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        mBtnText.setOnClickListener(this);
        mBtnQQ.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_text:
                Intent intent = new Intent(this,JavaCodeActivity.class);
                startActivity(intent);
            break;
            case R.id.btn_qq:
                Intent intent2 = new Intent(this,QQActivity.class);
                startActivity(intent2);
            break;
        }
    }
}
