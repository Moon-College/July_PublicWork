
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
		
		Log.d("debug", "当你调试可以选择这个级别打印蓝色");
		Log.i("info", "打印信息绿色");
		
		init();
	}
	/**
	 * 初始化控件
	 */
	private void init() {
		// TODO Auto-generated method stub
		btn1=(Button) findViewById(R.id.button1);//拨打电话
		btn3=(Button) findViewById(R.id.button3);//日志开关
		btn4=(Button) findViewById(R.id.button4);//打印日志
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
			 * 拨打电话
			 */
			String phone=editText1.getText().toString();
			if(phone.equals("")||phone==null){
				Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
			}else{
				phone(phone);
			}
			
			break;
		case R.id.button3:
			/**
			 * 设置日志开关
			 */
			if(btn3.getText().equals("日志 关")){
				btn3.setText("日志 开");
			}else{
				btn3.setText("日志 关");
			}
			break;
		case R.id.button4:
			/**
			 * 打印日志信息
			 */
			if(btn3.getText().equals("日志 关")){
				textview1.setText("");
				Toast.makeText(this, "不能打印日志，请打开日志开关", Toast.LENGTH_SHORT).show();
			}else if(btn3.getText().equals("日志 开")){
				
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
		//只采集一次日志
		list.add("-d");
		//过滤
		list.add("-s");
		//条件
		list.add("debug");
		//list.add("*:e");
		InputStream in = null;
		BufferedReader br = null;
		try {
		
			Process process=Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
		   in=process.getInputStream();//返回流
		   br=new BufferedReader(new InputStreamReader(in));//字节流
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
			//关闭流
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