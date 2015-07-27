package com.example.administrator.tz_muchen;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getLog;
    private Button callPhone;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLog = (Button) findViewById(R.id.getLog);
        callPhone = (Button) findViewById(R.id.callPhone);
        et = (EditText) findViewById(R.id.et);
        Log.i("Info","启动了activity");
        getLog.setOnClickListener(this);
        callPhone.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getLog:
                getLogs(  );
                break;
            case R.id.callPhone:
                call();
                break;

        }
    }
    /**跳转到打电话*/
    private void call() {
        String phoneNum = et.getText().toString();
        if (TextUtils.isEmpty(phoneNum)){
            Toast.makeText(MainActivity.this, "请输入要拨打的电话号码", Toast.LENGTH_SHORT).show();
            return ;
        }
        Intent it = new Intent();
        it.setAction("android.intent.action.CALL");
       // 传入电话号码
        it.setData(Uri.parse("tel:" + phoneNum));
        startActivity(it);
    }

    private void getLogs() {
        List<String> list = new ArrayList<String>();
        list.add("logcat");
        list.add("-d");
        list.add("-s");
        list.add("Info");
        try {
            Process process = Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuilder sb = new StringBuilder(  );
            while (((line)=reader.readLine())!=null){
                sb.append(line+"\n");
            }
            if (TextUtils.isEmpty(sb.toString())){
                Toast.makeText(MainActivity.this, "未获取到日志内容", Toast.LENGTH_SHORT).show();
            }else{
            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
