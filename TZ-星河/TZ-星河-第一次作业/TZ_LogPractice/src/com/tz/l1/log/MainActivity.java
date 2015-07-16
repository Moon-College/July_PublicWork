package com.tz.l1.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btLog;
	private Button btCall;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		Log.i("INFO", "info msg");
	}
	
	private void initView(){
		btLog=(Button) findViewById(R.id.bt_get_log_info);
		btCall=(Button) findViewById(R.id.bt_call);
		btCall.setOnClickListener(this);
		btLog.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bt_call:
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:13750847963"));
			startActivity(intent);
			break;
		case R.id.bt_get_log_info:
			List<String> cmdLine=new ArrayList<String>();
			cmdLine.add("logcat");
			//只采集1次
			cmdLine.add("-d");
			//过滤
			cmdLine.add("-s");
			//tag
			cmdLine.add("INFO");
			Process process=null;
			try {
				process = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
				InputStream in=process.getInputStream();
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				byte[] b=new byte[1024];
				int len=-1;
				while((len=in.read(b))!=-1){
					baos.write(b, 0, len);
				}
				in.close();
				String infoLog=baos.toString();
				Toast.makeText(this, infoLog, Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
			}
		
			break;
		}
	}

}
