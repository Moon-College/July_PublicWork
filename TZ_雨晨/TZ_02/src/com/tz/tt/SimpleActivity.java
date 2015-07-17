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
	// LinearLayout.LayoutParams����ʼ��ΪMATCH_PARENT, MATCH_PARENT
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
	 * @Description: TODO(������һ�仰�����������������)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	private void initView()
	{
		// ��������
		contentView = new LinearLayout(this);
		layoutParamsMM = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		// ��Ӳ�������
		contentView.setLayoutParams(layoutParamsMM);
		// ˮƽ����
		contentView.setOrientation(LinearLayout.HORIZONTAL);

		mEt_init();
		contentView.addView(mEt);
		btn_init();
		contentView.addView(btn);

	}

	/**
	 * 
	 * @Title: mEt_init
	 * @Description: (mEt ��ʼ��)
	 * @return void ��������
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
	 * @Description: (btn ��ʼ��)
	 * @return void ��������
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
