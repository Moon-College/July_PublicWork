package com.tz.messagephoneactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tz.messagephoneactivity.R;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_phone_act, btn_message_act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		initView();
	}

	private void initView() {
		btn_message_act = (Button) findViewById(R.id.btn_message_act);
		btn_message_act.setOnClickListener(this);
		btn_phone_act = (Button) findViewById(R.id.btn_phone_act);
		btn_phone_act.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_message_act:
			intent = new Intent();
			intent.setAction(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("smsto:"));
			startActivity(intent);
			break;
		case R.id.btn_phone_act:
			intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
