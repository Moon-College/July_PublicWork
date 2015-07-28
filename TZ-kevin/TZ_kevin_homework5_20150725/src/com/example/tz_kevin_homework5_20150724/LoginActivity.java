package com.example.tz_kevin_homework5_20150724;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author kevin.li
 * @version 1.0.0
 * @create 20150724
 * @function 登陆页面的实现 --- 判断邮箱格式 --- 按钮的失效
 */
public class LoginActivity extends BaseActivity {
	// 用户
	private EditText edtUser;
	// 密码
	private EditText edtPwd;
	// 用户名输入框是否为空 true 空 false 不空
	private boolean isUserEmty = true;
	// 密码输入框是否为空 true 空 false 不空
	private boolean isPwdEmty = true;
	// 学生信息按钮
	private Button btnStudents;
	// 菜单信息按钮
	private Button btnMenus;
	// emails 文本
	private String user;
	// 正则邮箱 kevinli.@qq.com ----- kevinli.@foxmial.com.cn 这两种格式都要匹配
	// 注 \\w 匹配任意字符
	// + 表示一次或者措辞
	// \\. 表示 . 注意和直接写.的区别
	// * 表示 0次或者多次
	private String regularExpression = "\\w+@\\w+(\\.\\w+)*\\.\\w+";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 初始化控件
		initViews();
		// 初始化数据
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		setupTopBaseView("登录", false);
		edtUser = (EditText) findViewById(R.id.edt_user);
		edtPwd = (EditText) findViewById(R.id.edt_pwd);
		btnStudents = (Button) findViewById(R.id.btn_students);
		btnMenus = (Button) findViewById(R.id.btn_menu);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// TODO Auto-generated method stub
		edtUser.addTextChangedListener(new MyEtTextWatch(R.id.edt_user));
		edtPwd.addTextChangedListener(new MyEtTextWatch(R.id.edt_pwd));
	}

	/**
	 * 点击学生按钮
	 * 
	 * @param view
	 */
	public void onclickStudents(View view) {
		user = edtUser.getText().toString().trim().replace(" ", "");
		if (!user.matches(regularExpression)) {
			Toast.makeText(this, "请输入合法的邮箱！", 0).show();
			return;
		}

		goIntent(StudentsActivity.class);
	}

	/**
	 * 点击菜單按钮
	 * 
	 * @param view
	 */
	public void onclickMenu(View view) {
		user = edtUser.getText().toString().trim().replace(" ", "");
		if (!user.matches(regularExpression)) {
			Toast.makeText(this, "请输入合法的邮箱！", 0).show();
			return;
		}
		goIntent(MenuActivity.class);
	}

	/**
	 * @author kevin.li
	 * @version 1.0.0
	 * @create 20150725
	 * @function 自定义文本监听器
	 */
	private class MyEtTextWatch implements TextWatcher {

		private int id;

		public MyEtTextWatch(int id) {
			this.id = id;
		}

		/**
		 * 文本改变之后的监听
		 */
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			// 改变按钮的状态
			changeBtnState(btnStudents, btnMenus);
		}

		/**
		 * 文本改变之前的监听
		 */
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		/**
		 * 文本改变之时的监听
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			switch (id) {
			case R.id.edt_user:
				if (TextUtils.isEmpty(s)) {
					isUserEmty = true;
				} else {
					isUserEmty = false;
				}
				break;

			case R.id.edt_pwd:
				if (TextUtils.isEmpty(s)) {
					isPwdEmty = true;
				} else {
					isPwdEmty = false;
				}

				break;
			}

		}

	}

	/**
	 * 改变button的使能状态
	 */
	private void changeBtnState(Button... btn) {
		for (Button button : btn) {
			if (null != button) {
				if (!isUserEmty && !isPwdEmty) {
					button.setEnabled(true);
				} else {
					button.setEnabled(false);
				}
			}
		}

	}
}
