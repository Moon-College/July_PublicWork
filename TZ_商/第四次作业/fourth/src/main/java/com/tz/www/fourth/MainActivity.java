package com.tz.www.fourth;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnLogin;
    private Button mBtnList;
    private Button btn_simple;

    @Override
    public void initLinster() {
        mBtnLogin.setOnClickListener(this);
        btn_simple.setOnClickListener(this);
        mBtnList.setOnClickListener(this);}

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnList = (Button) findViewById(R.id.btn_list);
        btn_simple = (Button) findViewById(R.id.btn_simple);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                intentStart(LoginActivity.class);
                break;
            case R.id.btn_list:
                intentStart(ListViewActivity.class);
                break;

            case R.id.btn_simple:

                intentStart(GridActivity.class);
                break;
        }
    }

    /**
     * 跳转
     *
     * @param clazz
     */
    private void intentStart(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
