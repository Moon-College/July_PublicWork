package asynctask.tz.com.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 采集日志
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button mBtnLog;
    private Button mBtnCal;
    private Button mBtnSms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
        Log.i(TAG, "********Test:++++++IIIIIII");
        Log.d(TAG, "********Test:++++++DDDDDDD");
        Log.e(TAG, "********Test:++++++EEEEEEE");
    }

    /*
    * 初始化视图
    * */
    private void initViews() {
        mBtnLog = (Button) findViewById(R.id.btn_log);
        mBtnCal = (Button) findViewById(R.id.btn_cal);
        mBtnSms = (Button) findViewById(R.id.btn_sms);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        mBtnLog.setOnClickListener(this);
        mBtnCal.setOnClickListener(this);
        mBtnSms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_log:
                showLog();
                break;
            case R.id.btn_cal:
                intent = new Intent();
                intent.setAction("android.intent.action.CALL_BUTTON");
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
                break;
        }
    }

    /**
     * 显示LOG
     */
    private void showLog() {
        List<String> logList = new ArrayList<String>();
        //adb logcat -help 查看logcat的命令
        //adb logcat -s INFO 过滤Tag标签为INFO的日志
        logList.add("logcat");
        // 筛选重复数据只收集一次
        logList.add("-d");
        //过滤日志信息
        logList.add("-s");
        //过滤条件
        logList.add("TAG");
        try {
            Process process = Runtime.getRuntime().exec(logList.toArray(new String[logList.size()]));
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            String len = null;
            while((len = br.readLine()) != null) {
                    sb.append(len);
                    sb.append("\n");
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
