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
		// ��ʼ���ؼ�
		initView();
		sp = getSharedPreferences("userlogin", MODE_PRIVATE);

		// �ж�sp���Ƿ񱣴����û���Ϣ
		if (sp.getString("username", null) != null
				&& sp.getString("password", null) != null) {
			if (StringUtil.equals(sp.getString("username", null), USER_NAME)
					&& StringUtil.equals(sp.getString("password", null),
							USER_PWD)) {
				Toast.makeText(this, "�����Զ���¼", Toast.LENGTH_LONG).show();
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
	 * ��¼
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
				// ��user���浽sp
				boolean isSave = saveObjectToSp(editor, user);
				if (isSave) {
					editor.commit();
				}
			}
			// ���sp
			else {
				boolean isRemove = removeObjectFromSp(editor, user);
				if (isRemove) {
					editor.commit();
				}
			}
			Toast.makeText(this, "��¼�ɹ�", Toast.LENGTH_LONG).show();
		} else{
			// ���sp
			Editor editor = sp.edit();
			boolean isRemove = removeObjectFromSp(editor, new User(null,null));
			if (isRemove) {
				editor.commit();
			}
		}
	}

	/**
	 * �������sp��
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
			// ��ȡ��������
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
	 * ��sp�����obj
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
			// ��ȡ��������
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
