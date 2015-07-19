package com.hacket.homework2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
