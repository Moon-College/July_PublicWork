package com.tz.collectlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * һ��������־�ռ������Ͷ�Ϣ���δ�绰�Ļ
 * 
 * @author �Խ���
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	
	public static final String MY_TAG="INFO";
	
	private Button collect_btn;// �ռ���־��ť
	private Button message_btn;// ������Ϣ��ť
	private Button tel_btn;// �δ�绰��ť

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG=true;//������־��¼
		setContentView(R.layout.main);
		initView();
		int i=MyLog.i(MY_TAG, "welcome to android!");
	}

	/**
	 * ��ʼ���ؼ�����ӵ���¼�
	 */
	private void initView() {
		collect_btn = (Button) findViewById(R.id.collect_btn);
		message_btn = (Button) findViewById(R.id.message_btn);
		tel_btn = (Button) findViewById(R.id.tel_btn);
		
		//���õ���¼�
		collect_btn.setOnClickListener(this);
		message_btn.setOnClickListener(this);
		tel_btn.setOnClickListener(this);
	}

	/**
	 * ��ĵ���ӿ��¼�����
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.collect_btn:
			collectLog();
			break;
		case R.id.message_btn:
			sendMessage();
			break;
		default:
			tel();
			break;
		}
	}

	/**
	 * �δ�绰
	 */
	private void tel() {
		Intent intent=new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:"));
		startActivity(intent);
	}

	/**
	 * ���Ͷ�Ϣ
	 */
	private void sendMessage() {
		Intent intent=new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("smsto:"));
		startActivity(intent);
	}

	/**
	 * �ռ���־
	 */
	private void collectLog() {
		ArrayList<String> cmds=new ArrayList<String>();
		cmds.add("logcat");
		cmds.add("-d");//�ռ�һ��
		cmds.add("-s");//����
		//cmds.add("*:i");
		cmds.add(MY_TAG);//��
		
		InputStream is=null;
		BufferedReader br=null;
		try {
			Process process=Runtime.getRuntime().exec(cmds.toArray(new String[cmds.size()]));
			is=process.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			String str=null;
			StringBuffer sb=new StringBuffer();
			while((str=br.readLine())!=null){
				sb.append(str);
				sb.append("\n");//ÿ��һ�У���һ����
			}
			Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//�ر���
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}