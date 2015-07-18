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
 * 一个用于日志收集、发送短息、拔打电话的活动
 * 
 * @author 赵建祥
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	
	public static final String MY_TAG="INFO";
	
	private Button collect_btn;// 收集日志按钮
	private Button message_btn;// 发送信息按钮
	private Button tel_btn;// 拔打电话按钮

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG=true;//开启日志记录
		setContentView(R.layout.main);
		initView();
		int i=MyLog.i(MY_TAG, "welcome to android!");
	}

	/**
	 * 初始化控件，添加点击事件
	 */
	private void initView() {
		collect_btn = (Button) findViewById(R.id.collect_btn);
		message_btn = (Button) findViewById(R.id.message_btn);
		tel_btn = (Button) findViewById(R.id.tel_btn);
		
		//设置点击事件
		collect_btn.setOnClickListener(this);
		message_btn.setOnClickListener(this);
		tel_btn.setOnClickListener(this);
	}

	/**
	 * 活动的点击接口事件方法
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
	 * 拔打电话
	 */
	private void tel() {
		Intent intent=new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:"));
		startActivity(intent);
	}

	/**
	 * 发送短息
	 */
	private void sendMessage() {
		Intent intent=new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("smsto:"));
		startActivity(intent);
	}

	/**
	 * 收集日志
	 */
	private void collectLog() {
		ArrayList<String> cmds=new ArrayList<String>();
		cmds.add("logcat");
		cmds.add("-d");//收集一次
		cmds.add("-s");//过滤
		//cmds.add("*:i");
		cmds.add(MY_TAG);//收
		
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
				sb.append("\n");//每读一行，换一次行
			}
			Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
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