package com.tz.userlogin;

import java.lang.reflect.Field;

import com.tz.userlogin.model.User;
import com.tz.userlogin.util.StringUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String USER_NAME = "jzhao";
	public static final String USER_PWD = "6262";
	private EditText username;
	private EditText password;
	private SharedPreferences sp;
	private CheckBox remeberpwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化控件
		initView();
		sp = getSharedPreferences("userlogin", MODE_PRIVATE);

		// 判断sp中是否保存用用户信息
		if (sp.getString("username", null) != null
				&& sp.getString("password", null) != null) {
			if (StringUtil.equals(sp.getString("username", null), USER_NAME)
					&& StringUtil.equals(sp.getString("password", null),
							USER_PWD)) {
				Toast.makeText(this, "正在自动登录", Toast.LENGTH_LONG).show();
				username.setText(sp.getString("username", null));
				password.setText(sp.getString("password", null));
				remeberpwd.setChecked(true);
			}
		}
	}

	/**
     * 
     */
	private void initView() {
		username = (EditText) findViewById(R.id.et_username);
		password = (EditText) findViewById(R.id.et_password);
		remeberpwd = (CheckBox) findViewById(R.id.cb_remeberpwd);
	}

	/**
	 * 登录
	 * 
	 * @param view
	 */
	public void login(View view) {
		String usernameStr = username.getText().toString();
		String passwordStr = password.getText().toString();
		if (StringUtil.equals(usernameStr, USER_NAME)
				&& StringUtil.equals(passwordStr, USER_PWD)) {
			User user = new User(usernameStr, passwordStr);
			Editor editor = sp.edit();
			if (remeberpwd.isChecked()) {
				// 将user保存到sp
				boolean isSave = saveObjectToSp(editor, user);
				if (isSave) {
					editor.commit();
				}
			}
			// 清空sp
			else {
				boolean isRemove = removeObjectFromSp(editor, user);
				if (isRemove) {
					editor.commit();
				}
			}
			Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
		} else{
			// 清空sp
			Editor editor = sp.edit();
			boolean isRemove = removeObjectFromSp(editor, new User(null,null));
			if (isRemove) {
				editor.commit();
			}
		}
	}

	/**
	 * 保存对象到sp中
	 * 
	 * @param editor
	 * @param obj
	 * @return
	 */
	public boolean saveObjectToSp(Editor editor, Object obj) {
		if (editor == null || obj == null) {
			return false;
		}
		try {
			// 获取所有属性
			Field[] fields = obj.getClass().getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.getType().getName()
							.equals(String.class.getName())) {
						editor.putString(field.getName(), field.get(obj)
								.toString());
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 从sp中清除obj
	 * 
	 * @param editor
	 * @param obj
	 * @return
	 */
	public boolean removeObjectFromSp(Editor editor, Object obj) {
		if (editor == null || obj == null) {
			return false;
		}
		try {
			// 获取所有属性
			Field[] fields = obj.getClass().getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.getType().getName()
							.equals(String.class.getName())) {
						editor.remove(field.getName());
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
