package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
public class TextActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_item);
        TextView tv = (TextView) findViewById(R.id.tv);
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
}
