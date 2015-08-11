package com.house.baseactivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.house.baseactivity.common.BaseActivity;

public class MainActivity extends BaseActivity {

	private TextView tv_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentLayout(R.layout.activity_main);

		initView();

		
	}

	/**
	 * ��ʼ��UI�ؼ�
	 */
	private void initView() {
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_content.setText("�ı����ݳɹ�");
		
		setTitleForString("���Ա�����");
		
		setLeftButtonText("����");
		setRightButtonText("�˵�");
	}

	@Override
	protected void setLeftButtonOnClickListener() {
		super.setLeftButtonOnClickListener();
		finish();
	}

	@Override
	protected void setRightButtonOnClickListener() {
		super.setRightButtonOnClickListener();
		Toast.makeText(context, "������Ҳఴť", 0).show();
	}
}
