package com.tz.lsn5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_name;
	private EditText et_psd;
	private EditText et_confirmPsd;
	private EditText et_email;
	private RadioButton rb_man;
	private RadioButton rb_wman;
	private CheckBox cb_term;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		et_name = (EditText)findViewById(R.id.et_name);
		et_psd = (EditText)findViewById(R.id.et_psd);
		et_confirmPsd = (EditText)findViewById(R.id.et_confirmPsd);
		et_email = (EditText)findViewById(R.id.et_email);
		rb_man = (RadioButton)findViewById(R.id.rb_man);
		rb_wman = (RadioButton)findViewById(R.id.rb_wman);
		cb_term = (CheckBox)findViewById(R.id.cb_term);
		
	}
	

	

	public void submit(View v) {
		
		String name_reGular = "^([a-zA-Z])([a-zA-Z0-9_]){5,29}";
		String password_reGular = "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,}";
		String email_reGular = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		
		//校验用户名
		String name = getETString(et_name);
		Pattern p_name = Pattern.compile(name_reGular);  
	    Matcher m_name = p_name.matcher(name); 
	    
	    if(! m_name.matches()) {
	    	Toast.makeText(this, "用户名不合法", Toast.LENGTH_LONG).show();
	    	et_name.findFocus();
	    	return;
	    }
	    
	    //校验密码		
	    String password = getETString(et_psd);
	    String confirmPassword = getETString(et_confirmPsd);
	    
	    if(password == null || confirmPassword == null) {
	    	Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
	    } else {
	    	if(!password.equals(confirmPassword)) {
	    		Toast.makeText(this, "2次密码输入不一致 password:" + password + "confirmPassword:" + confirmPassword, Toast.LENGTH_LONG).show();
	    		return;
	    	} else {
			    Pattern p_psd = Pattern.compile(password_reGular);  
			    Matcher m_psd = p_psd.matcher(password);
			    if(! m_psd.matches()) {
			    	Toast.makeText(this, "密码不合法", Toast.LENGTH_LONG).show();
			    	et_psd.findFocus();
			    	return;
			    }
		    }
	    }
	    
	    //校验邮箱	
		String email = getETString(et_email);
		Pattern p_email = Pattern.compile(email_reGular);  
	    Matcher m_email = p_email.matcher(email); 
	    
	    if(! m_email.matches()) {
	    	Toast.makeText(this, "邮箱不合法", Toast.LENGTH_LONG).show();
	    	et_email.findFocus();
	    	return;
	    }
	    
	    //校验服务条款
	    if(! cb_term.isChecked()) {
	    	Toast.makeText(this, "您还未同意服务条款，无法成为VIP会员", Toast.LENGTH_LONG).show();
	    	return;
	    }
	    
	    String msg = "恭喜您 " + name + " 下式成功潭州学院VIP会员！\n 密码：" + password + " 邮箱：" + email;
	    //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	    
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("温馨提示");
	    builder.setMessage(msg);
	    builder.setPositiveButton("我知道了", null);
	    builder.show();
	}

	private String getETString(EditText et) {
		// TODO Auto-generated method stub
		if(et != null && !TextUtils.isEmpty(et.getText())) {
			return et.getText().toString();
		}
		
		return null;
	}

}
