package com.qingzhu.dail;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText  edtPhone;
    private Button  btnDail;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//找到控件
		edtPhone = (EditText)findViewById(R.id.edtphone);
		//绑定事件
		btnDail = (Button)findViewById(R.id.dail);
		btnDail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//获取编辑框中的内容
				String phone = edtPhone.getText().toString();
				//判断内容是否为空
				if(!phone.isEmpty()) {
					Intent iten = new Intent();
					iten.setAction(Intent.ACTION_DIAL);
					iten.addCategory(Intent.CATEGORY_DEFAULT);
					iten.setData(Uri.parse("tel:"+phone));
					startActivity(iten);
					//最后添加打电话的权限   android.permission.CALL_PHONE
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
