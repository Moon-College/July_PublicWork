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
	 * 初始化UI控件
	 */
	private void initView() {
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_content.setText("改变数据成功");
		
		setTitleForString("测试标题栏");
		
		setLeftButtonText("返回");
		setRightButtonText("菜单");
	}

	@Override
	protected void setLeftButtonOnClickListener() {
		super.setLeftButtonOnClickListener();
		finish();
	}

	@Override
	protected void setRightButtonOnClickListener() {
		super.setRightButtonOnClickListener();
		Toast.makeText(context, "点击了右侧按钮", 0).show();
	}
}
