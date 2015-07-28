package com.example.tz_723_checkbox;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 
 * @author Administrator 作业 checkBox 操作 grawview 移除操作 刷新数据
 */
public class MainActivity extends Activity {
	Context context;
	private EditText et_emails;
	private EditText et_name;
	private EditText et_passward;
	private EditText et_repassward;
	private RadioGroup rg_sex;
	private RadioButton rb_man;
	private RadioButton rb_woman;
	private CheckBox cb_agree;
	private Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		initView();
		initListener();
		// submit();
	}

	private void initView() {
		et_emails = (EditText) findViewById(R.id.et_emails);
		et_name = (EditText) findViewById(R.id.et_name);
		et_passward = (EditText) findViewById(R.id.et_passward);
		et_repassward = (EditText) findViewById(R.id.et_repassward);
		rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
		rb_man = (RadioButton) findViewById(R.id.rb_man);
		rb_woman = (RadioButton) findViewById(R.id.rb_woman);
		cb_agree = (CheckBox) findViewById(R.id.cb_agree);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setEnabled(false);//默认不可点击
		cb_agree.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					//btn_submit.setClickable(true);
					btn_submit.setEnabled(true);
					
				} else {
					//btn_submit.setClickable(false);
					btn_submit.setEnabled(false);
					
				}
			}
		});
		
	}

	private void initListener() {
		// cb_agree.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// if(arg1)
		// {
		// btn_submit.setClickable(true);
		//
		// }else
		// {
		// btn_submit.setClickable(false);
		// }
		// }
		// });
		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				submit();
			}
		});
	}

	/**
	 * 提交
	 */
	private void submit() {
		String sex = "";
		String emails = et_emails.getText().toString();
		String name = et_name.getText().toString();
		String passward = et_passward.getText().toString();
		String rePassward = et_repassward.getText().toString();

		int id = rg_sex.getCheckedRadioButtonId();//得到选中的radiobutton的id
		switch (id) {
		case R.id.rb_man:
			sex = rb_man.getText().toString();
			break;
		case R.id.rb_woman:
			sex = rb_woman.getText().toString();
			break;
		}
		
		if (name == null || name.length() <= 6) {
			Toast.makeText(context, "姓名长度不能为空或者需要超过6位数", 1).show();
			return;
		}
		if (passward != null && passward.length() >=8 && rePassward != null
				&& rePassward.length() >= 8) {
			if (!passward.equals(rePassward)) {
				Toast.makeText(context, "密码不一致", 1).show();
				return;
			}
		} else {
			Toast.makeText(context, "密码不能为空或小于8位", 1).show();
			return;

		}
		if (emails != null && !emails.equals("".trim())) {
			String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			if (!emails.matches(format)) {
				et_emails.setTextColor(Color.RED);
				Toast.makeText(context, "邮箱格式不对！", 1).show();
				return;
			}
			else
			{
				et_emails.setTextColor(Color.BLACK);
			}
		}
		else{
			
			Toast.makeText(context, "邮箱不能为空！", 1).show();
			return;	
		}
      Toast.makeText(context,"性别:"+ sex +"姓名："+name+"密码："+passward+"邮箱："+emails, 1).show();
	}
}
