package com.tz.dallon.lsn20_fragment.FragmentDynamic;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.tz.dallon.lsn20_fragment.R;

public class DynamicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);


        SharedPreferences sp = this.getSharedPreferences("main", Context.MODE_PRIVATE);
        String userName = sp.getString("username", "");

        //登陆 Fragment管理器
        FragmentManager fm = getFragmentManager();
        //开启事务-在事务里面可以处理批量fragment
        FragmentTransaction ft = fm.beginTransaction();

        if (TextUtils.isEmpty(userName)) {

            //实例化一个fragment
            LoginFragment lf = new LoginFragment();


            ft.replace(R.id.fl_content, lf);
            ft.commit();

        } else {
            //加入购物车

            //实例化一个fragment
            ShoppingFragment sf = new ShoppingFragment();


            ft.replace(R.id.fl_content, sf);
            ft.commit();
        }

    }

}
