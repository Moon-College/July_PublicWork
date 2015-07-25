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
 * @function ��½ҳ���ʵ�� --- �ж������ʽ --- ��ť��ʧЧ
 */
public class LoginActivity extends BaseActivity {
	// �û�
	private EditText edtUser;
	// ����
	private EditText edtPwd;
	// �û���������Ƿ�Ϊ�� true �� false ����
	private boolean isUserEmty = true;
	// ����������Ƿ�Ϊ�� true �� false ����
	private boolean isPwdEmty = true;
	// ѧ����Ϣ��ť
	private Button btnStudents;
	// �˵���Ϣ��ť
	private Button btnMenus;
	// emails �ı�
	private String user;
	// �������� kevinli.@qq.com ----- kevinli.@foxmial.com.cn �����ָ�ʽ��Ҫƥ��
	// ע \\w ƥ�������ַ�
	// + ��ʾһ�λ��ߴ��
	// \\. ��ʾ . ע���ֱ��д.������
	// * ��ʾ 0�λ��߶��
	private String regularExpression = "\\w+@\\w+(\\.\\w+)*\\.\\w+";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// ��ʼ���ؼ�
		initViews();
		// ��ʼ������
		initData();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		setupTopBaseView("��¼", false);
		edtUser = (EditText) findViewById(R.id.edt_user);
		edtPwd = (EditText) findViewById(R.id.edt_pwd);
		btnStudents = (Button) findViewById(R.id.btn_students);
		btnMenus = (Button) findViewById(R.id.btn_menu);

	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		// TODO Auto-generated method stub
		edtUser.addTextChangedListener(new MyEtTextWatch(R.id.edt_user));
		edtPwd.addTextChangedListener(new MyEtTextWatch(R.id.edt_pwd));
	}

	/**
	 * ���ѧ����ť
	 * 
	 * @param view
	 */
	public void onclickStudents(View view) {
		user = edtUser.getText().toString().trim().replace(" ", "");
		if (!user.matches(regularExpression)) {
			Toast.makeText(this, "������Ϸ������䣡", 0).show();
			return;
		}

		goIntent(StudentsActivity.class);
	}

	/**
	 * ����ˆΰ�ť
	 * 
	 * @param view
	 */
	public void onclickMenu(View view) {
		user = edtUser.getText().toString().trim().replace(" ", "");
		if (!user.matches(regularExpression)) {
			Toast.makeText(this, "������Ϸ������䣡", 0).show();
			return;
		}
		goIntent(MenuActivity.class);
	}

	/**
	 * @author kevin.li
	 * @version 1.0.0
	 * @create 20150725
	 * @function �Զ����ı�������
	 */
	private class MyEtTextWatch implements TextWatcher {

		private int id;

		public MyEtTextWatch(int id) {
			this.id = id;
		}

		/**
		 * �ı��ı�֮��ļ���
		 */
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			// �ı䰴ť��״̬
			changeBtnState(btnStudents, btnMenus);
		}

		/**
		 * �ı��ı�֮ǰ�ļ���
		 */
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		/**
		 * �ı��ı�֮ʱ�ļ���
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
	 * �ı�button��ʹ��״̬
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
