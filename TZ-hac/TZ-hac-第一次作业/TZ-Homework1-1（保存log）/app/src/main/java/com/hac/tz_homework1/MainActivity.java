package com.hac.tz_homework1;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    Button bt_collect;

    //日志保存路径
    String savePath = "";
    //日志文件名
    final String fileName = "log_info.txt";
    //指令列表
    StringBuffer commandBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得保存路径
        savePath = Environment.getExternalStorageDirectory().getAbsolutePath();

        bt_collect = (Button)findViewById(R.id.bt_collect);
        bt_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编写指令logcat -d -s test:i
                commandBuffer.append("logcat ");
                commandBuffer.append("-d ");
                commandBuffer.append("-s ");
                commandBuffer.append("test:i");

                //生成一条log
                Log.i("test", "test_warn");

                try {
                    Process process = Runtime.getRuntime().exec(commandBuffer.toString());
                    InputStream inputStream = process.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String str = null;
                    StringBuffer sb = new StringBuffer();
                    while((str = bufferedReader.readLine())!=null){
                        sb.append(str);
                        sb.append("\n");
                    }
                    //将日志保存到文件中
                    FileWriter fw = new FileWriter(savePath+File.separator+fileName);
                    fw.flush();
                    fw.write(sb.toString());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
