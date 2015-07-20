
package com.phone.oneceishi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{
	private Button btn1,btn3,btn4;
	private EditText editText1;
	private TextView textview1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE );
		setContentView(R.layout.activity_main);
		
		Log.d("debug", "������Կ���ѡ����������ӡ��ɫ");
		Log.i("info", "��ӡ��Ϣ��ɫ");
		
		init();
	}
	/**
	 * ��ʼ���ؼ�
	 */
	private void init() {
		// TODO Auto-generated method stub
		btn1=(Button) findViewById(R.id.button1);//����绰
		btn3=(Button) findViewById(R.id.button3);//��־����
		btn4=(Button) findViewById(R.id.button4);//��ӡ��־
		editText1=(EditText) findViewById(R.id.editText1);
		textview1=(TextView) findViewById(R.id.textView1);
		btn1.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		editText1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			/**
			 * ����绰
			 */
			String phone=editText1.getText().toString();
			if(phone.equals("")||phone==null){
				Toast.makeText(this, "�绰���벻��Ϊ��", Toast.LENGTH_SHORT).show();
			}else{
				phone(phone);
			}
			
			break;
		case R.id.button3:
			/**
			 * ������־����
			 */
			if(btn3.getText().equals("��־ ��")){
				btn3.setText("��־ ��");
			}else{
				btn3.setText("��־ ��");
			}
			break;
		case R.id.button4:
			/**
			 * ��ӡ��־��Ϣ
			 */
			if(btn3.getText().equals("��־ ��")){
				textview1.setText("");
				Toast.makeText(this, "���ܴ�ӡ��־�������־����", Toast.LENGTH_SHORT).show();
			}else if(btn3.getText().equals("��־ ��")){
				
				myLog();
			}
		
			break;
			
		default:
			break;
		}
		
	}
	private void myLog() {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		list.add("logcat");
		//ֻ�ɼ�һ����־
		list.add("-d");
		//����
		list.add("-s");
		//����
		list.add("debug");
		//list.add("*:e");
		InputStream in = null;
		BufferedReader br = null;
		try {
		
			Process process=Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
		   in=process.getInputStream();//������
		   br=new BufferedReader(new InputStreamReader(in));//�ֽ���
			StringBuffer sb=new StringBuffer();
			String str1=null;
			while((str1=br.readLine())!=null){
				sb.append(str1);
				sb.append("\n");
			}
			textview1.setText(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void phone(String phone){
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+phone));
		startActivity(intent);
	}
}