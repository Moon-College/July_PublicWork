package com.tz.logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	public static final String DEBUG_TAG = "TZ_logcat_MainActivity";
	
	private Button mFilterDebugLogBtn; //触发获取日志按钮
	private TextView mShowDebugLogTv; //显示日志信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.i(DEBUG_TAG, "onCreate方法被执行了");
        mFilterDebugLogBtn = (Button) findViewById(R.id.btn_debuglog);
        mShowDebugLogTv = (TextView) findViewById(R.id.tv_show_loginfo);
        
        //设置点击事件
        mFilterDebugLogBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(DEBUG_TAG, "Button被点击了.....");
				try {
					getDebugInfoByTagName();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.i(DEBUG_TAG, "获取日志信息出错：" + e.getMessage());
					e.printStackTrace();
				}
			}
		});
    }

    //获取Debug日志信息, 并显示出来
    private void getDebugInfoByTagName() throws IOException {
        	Log.i(DEBUG_TAG, "hello");
        	StringBuffer sb = new StringBuffer();
        	ArrayList<String> cmdLine = new ArrayList<String>();
        	cmdLine.add("logcat");
        	cmdLine.add("-d");
        	cmdLine.add("-s");
        	cmdLine.add(DEBUG_TAG);
        	Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
        	InputStream inputStream = exec.getInputStream();
        	InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        	String str = null;
        	while((str = bufferedReader.readLine())!=null){
        		sb.append(str);
        		sb.append("\n");
        	}
        	//Toast.makeText(this, sb.toString(), 1000).show();
        	
        	mShowDebugLogTv.setText(sb.toString());
    }
    
}
