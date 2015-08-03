package com.fantasyado.activitydemo;

 

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

 /**
  * 
  * @author Fantasy.ado
  *
  * 2015年8月3日
  * 隐式意图启动自定义Activity并返回parcelable型数据
  */


public class MainActivity extends ActionBarActivity implements OnClickListener {
   private Button btn_register;
   private TextView tv_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_register = (Button) findViewById(R.id.btn_signIn);
        btn_register.setOnClickListener(this);
        tv_top = (TextView) findViewById(R.id.tv_top);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		case R.id.btn_signIn:
			 Intent intent = new Intent("com.fantasyado.action_register");
			 startActivityForResult(intent, 1);
			break;

		default:
			break;
		}
		
	}
	
   @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(resultCode, requestCode, data);
		if (resultCode==RESULT_OK) {
			switch (requestCode) {
			case 1:
				UserInfo user = data.getParcelableExtra("user");
			 String name = user.getUsername();
			 String password = user.getPassword();
			 tv_top.setText("恭喜注册成功，请牢记您的用户信息：\n"+
			   " 用户名："+name+"\n"+
				"密码：" +password				
					 );
				break;

			default:
				break;
			}
		}
	}

   
}
