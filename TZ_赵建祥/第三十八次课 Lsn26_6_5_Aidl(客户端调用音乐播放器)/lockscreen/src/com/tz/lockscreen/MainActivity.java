package com.tz.lockscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private Intent lockservice;
	private Button bt_unlock;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏键盘
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        //锁屏时也显示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        //全屏，去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        //启动常驻服务
        lockservice = new Intent("com.tz.lockscreen");
        startService(lockservice);
    }
    /**
     * 初始化按钮事件
     * 写在XML里，点击事件锁屏显示时无法点击
     */
    private void initView() {
		bt_unlock = (Button) findViewById(R.id.bt_unlock);
		bt_unlock.setOnClickListener(this);
		
	}

	/**
     * 解锁
     */
    public void unlock(View v){
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setMessage("你爱红姝吗？");
    	builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//关闭锁屏
				finish();
			}
		});
    	builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//do nothing
			}
		});
    	builder.create().show();
    }
	@Override
	public void onClick(View v) {
		unlock(v);
	}

}
