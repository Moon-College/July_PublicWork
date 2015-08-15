package com.tz.lsn6.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tz.lsn6.fileexplorer.R;

public class EditTextActivity extends Activity {
	private EditText et;
	private String textContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		Intent intent = getIntent();
		textContent = intent.getExtras().getString("content");
		System.out.println(">>>>>>>>>content :" + textContent);
		et = (EditText)findViewById(R.id.et);
		et.setText(textContent);
	}
	
	public void btnClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
			case R.id.bt_save:
				String content = et.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("content", content);
				setResult(ShowActivity.RESULT_CODE, intent);
				
				finish();
				break;
			case R.id.bt_recover:
				et.setText(textContent);
				break;
			case R.id.bt_back:
				finish();
				break;	
			default:
				break;
		}
	}
}
