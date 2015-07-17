package com.tz.tt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SimpleActivity extends Activity {

	private EditText mEt;
	private Button btn;
	private LinearLayout contentView;
	// LinearLayout.LayoutParams，初始化为MATCH_PARENT, MATCH_PARENT
	public LinearLayout.LayoutParams layoutParamsMM = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initView();
		setContentView(contentView);
	}

	/**
	 * 
	 * @Title: initView
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void initView()
	{
		// 容器布局
		contentView = new LinearLayout(this);
		layoutParamsMM = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		// 添加布局属性
		contentView.setLayoutParams(layoutParamsMM);
		// 水平布局
		contentView.setOrientation(LinearLayout.HORIZONTAL);

		mEt_init();
		contentView.addView(mEt);
		btn_init();
		contentView.addView(btn);

	}

	/**
	 * 
	 * @Title: mEt_init
	 * @Description: (mEt 初始化)
	 * @return void 返回类型
	 */
	void mEt_init()
	{
		// EditText
		mEt = new EditText(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		lp.weight = 1;
		mEt.setLayoutParams(lp);
		mEt.setHint(R.string.tt_simple_et_h);
	}

	/**
	 * 
	 * @Title: btn_init
	 * @Description: (btn 初始化)
	 * @return void 返回类型
	 */
	void btn_init()
	{

		// btn
		btn = new Button(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		lp.weight = 1;
		btn.setLayoutParams(lp);
		btn.setText(R.string.tt_simple_btn);

	}
}
