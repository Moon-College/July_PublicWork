package com.ws.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CopyActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.copyactivity);
		TextView tv = (TextView) findViewById(R.id.tv);
		Intent intent = getIntent();
//		String str = intent.getStringExtra("name");
//		Bundle bundle = intent.getBundleExtra("bundle");
//		String a = bundle.getString("name");
//		String b = bundle.getString("age");
//		String c = bundle.getString("weight");
//		String d = bundle.getString("height");
//		String str = "������"+ a +"\n"+ "���䣺" + b+"\n" + "���أ�" + c +"\n"+ "��ߣ�" + c;
		MyInFo info = intent.getParcelableExtra("info");
		tv.setText(info.toString());
	}
}
