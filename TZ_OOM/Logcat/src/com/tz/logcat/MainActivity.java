package com.tz.logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText mPhoneNumberEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPhoneNumberEditText = (EditText) findViewById(R.id.phone_number);
		Log.i("MAINACTIVITY_INFO", "MainActivity onCreate");
		
	}
	
	/**
	 * �����ռ���ť����¼� 
	 * @param v 
	 */
	public void saveLog(View v) {
		// �ռ���־ 
		String log = getLog("MAINACTIVITY_INFO");
		// Toast ��ʾ ��־ 
		Toast.makeText(this, log == null ? "��ȡ��־ʧ��" : log, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * ����Ϣ���ͽ��� 
	 * @param v
	 */
	public void openSendMessage(View v) {
		// ��ȡ�绰����
		String phoneNumber = mPhoneNumberEditText.getText().toString();
		// ʹ����ʽ��ͼ��ϵͳ����SMS����
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SENDTO");
		// ����绰����
		intent.setData(Uri.parse("sms:" + phoneNumber));
		startActivity(intent);
	}
	
	/**
	 * ����绰
	 * @param v
	 */
	public void callPhone(View v) {
		// ��ȡ�绰����
		String phoneNumber = mPhoneNumberEditText.getText().toString();
		// ʹ����ʽ��ͼ��ϵͳ����绰����
		Intent intent = new Intent();
		intent.setAction("android.intent.action.CALL");
		// ����绰����
		intent.setData(Uri.parse("tel:" + phoneNumber));
		startActivity(intent);
	}
	
	
	/**
	 * ���ݲ������ǣ���ȡ��־ 
	 * @param filter ���ǲ���  �磺
	 * @return ������־�ַ���
	 */
	private String getLog(String filter) {
		// ��װ�ɼ���־����
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		// ��ȡ��־
		cmdLine.add("-d"); 
		// ���� 
		cmdLine.add("-s");
		// ���ǲ���
		cmdLine.add(filter);
		// ִ�вɼ�����
		try {
			Process process = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
			InputStream inputStream = process.getInputStream();
			
			Reader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			
			// ����־���浽StringBuffer
			StringBuffer buffer = new StringBuffer();
			// ��ȡһ�У������ȡ��Ϊnull ������ȡ
			while((line = bufferedReader.readLine()) != null) {
				// ����ȡ�����ݱ��浽StringBuffer ��
				buffer.append(line).append("\n");
			}
			// ������־
			return buffer.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
