package com.hac.tz_homework5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hac.tz_homework5.util.FileUtil;
import com.hac.tz_homework5.util.SharedPreferenceImpl;
import com.hac.tz_homework5.util.ToastUtil;

/**
 * Created by hp on 2015/8/4.
 */
public class ReadFileActivity extends Activity {

    EditText et;
    TextView tv_save;
    TextView tv_back;

    String path = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readfile);
        et = (EditText) findViewById(R.id.et);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_back = (TextView) findViewById(R.id.tv_back);

        if(savedInstanceState != null) {
            et.setText((String)savedInstanceState.get("content"));
            path = (String)savedInstanceState.get("path");
            ToastUtil.showToast(getApplicationContext(), "已为您恢复上次编辑内容");
        }
        else {
            Intent intent = getIntent();
            Uri uri = intent.getData();
            path = uri.getPath();
            et.setText(FileUtil.readFile(path));
        }

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FileUtil.writeFile(getApplicationContext(), path, et.getText().toString(),false)) {
                    ToastUtil.showToast(getApplicationContext(), "保存成功");
                    finish();
                }
                else {
                    ToastUtil.showToast(getApplicationContext(), "保存失败");
                }
            }
        });

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferenceImpl sp = new SharedPreferenceImpl(getApplicationContext());
//        sp.set("log", et.getText().toString());
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("content", et.getText().toString());
        outState.putString("path", path);
    }
}
