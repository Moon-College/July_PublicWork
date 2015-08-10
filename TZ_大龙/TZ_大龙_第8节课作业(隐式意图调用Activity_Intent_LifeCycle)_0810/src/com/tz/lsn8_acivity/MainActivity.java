package com.tz.lsn8_acivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String TAG = "log";
	private TextView tv;
	
	/**
	 * ������ʱ��ص��ķ���
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        /**
		 * �ж��Ƿ���ϵͳ�ɵ��˺��ٴ�����Activity
		 * ��øɵ�֮ǰ��������ݣ��ָ�
		 */
        
		if(savedInstanceState!=null){
			int progress = savedInstanceState.getInt("progress");
			tv.setText(progress+"");
			String txt = savedInstanceState.getString("txt");
			tv.setText(txt);
		}
    }
    
    public void btnClick(View view) {
    	
    	switch (view.getId()) {
			case R.id.bt_jump:
		    	startActivity(new Intent(this, SecondActivity.class));
				break;
			case R.id.bt_photo:
		    	startActivity(new Intent(this, PhotoActivity.class));
				break;
			case R.id.bt_callActivity:
				//��ʽ��ͼ���� Activity
				Intent intent = new Intent();
				intent.setAction("ACTION_ABC");
		    	startActivity(intent);
				break;
	
			default:
				break;
		}
    	
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasFocus);
    	Log.i(TAG, "  ============onWindowFocusChanged");
    }

	/**	�����ǵ�activity����ɼ���ʱ��
	 * (���磺��ʼ������ͷ���������ȣ����ݿ����ӣ�gps��λ�򿪵�)
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "  ============onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "  ============onRestart");
	}
	
	/**
	 * �����ý���(ǰ̨)--�������û����н���
	 * for your activity to start interacting with the user. 
	 * This is a good place to begin animations, 
	 * open exclusive-access devices (such as the camera), etc. 
	 * onResume������ԣ���������������һЩϵͳ�豸��������������ֲ��ŵȡ�
	 * (һ��������Ŀ��ʱ�򣬻ָ���onPause���汣�������)
	 * 
	 * Keep in mind that onResume is not the best indicator that your activity is visible to the user; a system window such as the keyguard may be in front. Use onWindowFocusChanged to know for certain that your activity is visible to the user 
	 * (for example, to resume a game). �ָ���Ϸ��ʱ��Ӧ����onWindowFocusChanged����д
	 * ��onResume��api��֪����������ý������onWindowFocusChanged����
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "  ============onResume");
	}
	
	//===============Running ���г�����================
	/**
	 * 	��Activityʧȥ����
	 * 	(һ��������Ŀ��ʱ�򣬱�����Ϸ��Ҫ��ͣ������һЩ��Ϸ���ȵ�����)
	 *	�������ݵ�����Լ���취(�����ļ����桢���ݿ⡢������ѡ��ȵ�)
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "  ============onPause");
	}
	
	/**Activity���ɼ���ʱ��
	 * ��һ������Ŀ�������󲿷�ʱ�򱣴����ݲ��������������onPause���棩
	 * Note that this method may never be called, in low memory situations where the system does not have enough memory to keep your activity's process running after its onPause method is called. 
	 * ʹ�ó���������Activity���ɼ���ʱ�򱣴�һЩ�������ݼ����ղ�����Դ
	 * (���磺�ر�����ͷ���������ȣ����ݿ����ӣ�gps��λ�ر�)
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "  ============onStop");
	}

	/**
	 * ����ȷ��ActivityҪbyebye��
	 * �����ڴ˷��������ͷ�һЩ֮ǰ�����ͷŵ���Դ,�����ڴ�й¶
	 * 1.�ͷ�һЩ����xxobj = null��xxxBlueTooth.close();io.close();
	 * 2.�ر�һЩ�̣߳���Activity�˳��ˣ�������̻߳��ڣ�
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "  ============onStart");
	}
	
	/** ϵͳ�ڸɵ���֮ǰ�����onSaveInstanceState(Bundle bundle)
	 * 	�ڱ��ɵ�֮ǰ��������
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	/**
	 * �⺯����onStart ���� �ص� ���������ݽ��б��浽Bundle����֤�´δ�����ʱ�����ȡ������
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		if(savedInstanceState!=null){
			int progress = savedInstanceState.getInt("progress");
			tv.setText(progress+"");
			String txt = savedInstanceState.getString("txt");
			tv.setText(txt);
		}
	}


}
