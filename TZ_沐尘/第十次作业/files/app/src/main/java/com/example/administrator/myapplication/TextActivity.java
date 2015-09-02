package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/8/4 0004.
 */
public class TextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private EditText et;
    private Button cancel;
    private Button save;
    private RelativeLayout rl;
    private boolean isShowTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_item);
        findAllViews();
        if (savedInstanceState!=null){
            recoveryData(savedInstanceState);
        }else{
            initData();
        }
            setListener();
    }

    /**
     * 数据的恢复
     * @param savedInstanceState
     */
    private void recoveryData(Bundle savedInstanceState) {
        isShowTv = savedInstanceState.getBoolean("state");
        String value = savedInstanceState.getString("value");
        Log.i("MEPAI","重新填充数据"+isShowTv);
        if (isShowTv){
            tv.setText(value);
        }else{
            et.setText(value);
            tv.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 添加事件监听
     */
    private void setListener() {
        //长按textview 变可以编辑的editText
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence text = tv.getText();
                et.setText(text);
                rl.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                isShowTv = false;
                return true;
            }
        });
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        String path = data.getPath();
        File file = new File(path);
        try {
            Log.i("MEPAI" , path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            String len = null;
            while((len=bufferedReader.readLine())!=null){
                builder.append(len);
            }
            reader.close();
            tv.setText(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(TextActivity.this, "出错了", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查找到所有的控件
     */
    private void findAllViews() {
        tv = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.et);
        cancel = (Button) findViewById(R.id.cancel);
        save = (Button) findViewById(R.id.save);
        rl = (RelativeLayout) findViewById(R.id.rl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                showText();
                break;
            case R.id.save:
                saveData();
                break ;
        }
    }

    private void showText() {
        et.setText("");
        rl.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
        isShowTv = true;
    }

    /**
     * 保存数据
     */
    private void saveData() {
        Editable text = et.getText();
        tv.setText(text.toString());
        showText();
    }

    /**
     * 做数据的意外保存
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String value = "";
        Log.i("MEPAI","保存数据");
        if (isShowTv){
            CharSequence text = tv.getText();
            value = text.toString();
        }else{
            Editable text = et.getText();
            value = text.toString();
        }
            outState.putString("value",value);
            outState.putBoolean("state",isShowTv);
    }
}
