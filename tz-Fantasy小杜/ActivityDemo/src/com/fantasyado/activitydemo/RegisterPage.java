package com.fantasyado.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

 

/**  
 * created at:2015年8月3日 下午2:06:21  
 * project name:ActivityDemo  
 * @author Fantasy ado  
 * @version 1.0  
 * @since JDK 1.7  
 * File name:RegisterPage.java  
 * description:  
 */

public class RegisterPage extends Activity implements OnClickListener {
    private Button btn_ok;
    private EditText et_name;
    private EditText et_password;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		et_name = (EditText) findViewById(R.id.et_name);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_ok.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok:
			 String name = et_name.getText().toString();
			 String password = et_password.getText().toString();
			 UserInfo user = new UserInfo(name, password);
			 Intent intent = new Intent();
			 intent.putExtra("user", user);
			 setResult(RESULT_OK, intent);
			 finish();
			
			
			break;

		default:
			break;
		}
	}
	
	
}
