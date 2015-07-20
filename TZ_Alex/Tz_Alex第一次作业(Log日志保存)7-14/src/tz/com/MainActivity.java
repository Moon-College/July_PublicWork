package tz.com;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_save, btn_phone, btn_sms;
	private ArrayList<String> logArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ���ؼ�
		init();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void init() {
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_phone = (Button) findViewById(R.id.btn_phone);
		btn_sms = (Button) findViewById(R.id.btn_sms);
		// ���ü����¼�
		btn_save.setOnClickListener(this);
		btn_phone.setOnClickListener(this);
		btn_sms.setOnClickListener(this);
		
		logArray = new ArrayList<String>();
		//����log��־
		Log.i("TAG", "����info");
		Log.e("TAG", "����error");
		Log.v("TAG", "����verbose");
		Log.w("TAG", "����warning");
		Log.d("TAG", "����debug");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//��ȡ��־
		case R.id.btn_save:
			logArray.add("logcat");
			logArray.add("-d");  // ɸѡ�ظ�����ֻ�ռ�һ��
			logArray.add("-s");  //������־��Ϣ
			logArray.add("TAG");  //��������
			try {
				Process process = Runtime.getRuntime().exec(logArray.toArray(new String[logArray.size()]));
				InputStream is = process.getInputStream();
				//���ֽ���ת���ַ���
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String str = null;
				StringBuffer sb = new StringBuffer();
				while((str = br.readLine())!=null){
					sb.append(str);
					sb.append("\n");
				}
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		//��ת����绰
		case R.id.btn_phone:
			Intent intentPhone = new Intent();
			intentPhone.setAction("android.intent.action.CALL_BUTTON");
			startActivity(intentPhone);
			break;
		//��ת������Ϣ
		case R.id.btn_sms:
			Intent intentSms = new Intent(Intent.ACTION_VIEW);  
			intentSms.setType("vnd.android-dir/mms-sms"); 
			startActivity(intentSms);
			break;
		default:
			break;
		}
	}
}
