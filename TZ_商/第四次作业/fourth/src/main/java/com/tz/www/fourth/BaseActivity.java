package com.tz.www.fourth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * 项目名称：First
 * 类描述：
 * 创建人：Shang Wentong
 * 创建时间：2015/7/28 1:20
 * 修改人：Shang Wentong
 * 修改时间：2015/7/28 1:20
 * 修改备注：
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initLinster();
    }

    public void initData() {
    }

    public  void initLinster(){};

    public abstract void initView();

    @Override
    public void onClick(View v) {

    }
}
