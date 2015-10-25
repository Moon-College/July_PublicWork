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
        //ȥ��������
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //���ؼ���
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        //����ʱҲ��ʾ
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        //ȫ����ȥ��״̬��
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        //������פ����
        lockservice = new Intent("com.tz.lockscreen");
        startService(lockservice);
    }
    /**
     * ��ʼ����ť�¼�
     * д��XML�����¼�������ʾʱ�޷����
     */
    private void initView() {
		bt_unlock = (Button) findViewById(R.id.bt_unlock);
		bt_unlock.setOnClickListener(this);
		
	}

	/**
     * ����
     */
    public void unlock(View v){
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setMessage("�㰮�����");
    	builder.setPositiveButton("��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//�ر�����
				finish();
			}
		});
    	builder.setNegativeButton("��", new DialogInterface.OnClickListener() {
			
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
