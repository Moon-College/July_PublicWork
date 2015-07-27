package com.tz.tt;

import com.tz.tt.util.ViewUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_simple, btn_qq;

	private LinearLayout contentView;
	// LinearLayout.LayoutParams，初始化为MATCH_PARENT, MATCH_PARENT
	public LinearLayout.LayoutParams layoutParamsMM = null;
	// LinearLayout.LayoutParams，初始化为WRAP_CONTENT, WRAP_CONTENT
	public LinearLayout.LayoutParams layoutParamsWW = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initView();

	}

	private void initView()
	{

		layoutParamsMM = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		layoutParamsWW = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		// 容器布局
		contentView = new LinearLayout(this);
		// 添加布局属性
		contentView.setLayoutParams(layoutParamsMM);
		// 垂直布局
		contentView.setOrientation(LinearLayout.VERTICAL);
		// 内容布局居中
		contentView.setGravity(Gravity.CENTER);

		// 创建按钮simple
		btn_simple = new Button(this);
		// 设置(layout_width = 100dp)
		layoutParamsWW.width = (int) ViewUtil.dip2px(this, 100f);
		// 添加布局属性
		btn_simple.setLayoutParams(layoutParamsWW);
		// 添加显示内容
		btn_simple.setText(R.string.tt_main_simple);
		// 内容布局居中
		btn_simple.setGravity(Gravity.CENTER);
		// 设置id
		btn_simple.setId(R.id.btn_main_simple);
		// 设置点击事件
		btn_simple.setOnClickListener(this);
		// 添加到容器
		contentView.addView(btn_simple);

		// 创建按钮QQ
		btn_qq = new Button(this);
		// 设置(layout_marginTop = 20dp)
		layoutParamsWW.topMargin = (int) ViewUtil.dip2px(this, 20f);
		// 设置(layout_width = 100dp)
		layoutParamsWW.width = (int) ViewUtil.dip2px(this, 100f);
		// 添加布局属性
		btn_qq.setLayoutParams(layoutParamsWW);
		// 添加显示内容
		btn_qq.setText(R.string.tt_main_qq);
		// 内容布局居中
		btn_qq.setGravity(Gravity.CENTER);
		// 设置id
		btn_qq.setId(R.id.btn_main_qq);
		// 设置点击事件
		btn_qq.setOnClickListener(this);
		// 添加到容器
		contentView.addView(btn_qq);

		// 添加布局到Activity
		setContentView(contentView);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.btn_main_simple:
			startActivity(new Intent(this, SimpleActivity.class));
			break;
		case R.id.btn_main_qq:
			startActivity(new Intent(this, QQActivity.class));
			break;

		default:
			break;
		}
	}

}
