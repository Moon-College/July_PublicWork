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
	// LinearLayout.LayoutParams����ʼ��ΪMATCH_PARENT, MATCH_PARENT
	public LinearLayout.LayoutParams layoutParamsMM = null;
	// LinearLayout.LayoutParams����ʼ��ΪWRAP_CONTENT, WRAP_CONTENT
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

		// ��������
		contentView = new LinearLayout(this);
		// ��Ӳ�������
		contentView.setLayoutParams(layoutParamsMM);
		// ��ֱ����
		contentView.setOrientation(LinearLayout.VERTICAL);
		// ���ݲ��־���
		contentView.setGravity(Gravity.CENTER);

		// ������ťsimple
		btn_simple = new Button(this);
		// ����(layout_width = 100dp)
		layoutParamsWW.width = (int) ViewUtil.dip2px(this, 100f);
		// ��Ӳ�������
		btn_simple.setLayoutParams(layoutParamsWW);
		// �����ʾ����
		btn_simple.setText(R.string.tt_main_simple);
		// ���ݲ��־���
		btn_simple.setGravity(Gravity.CENTER);
		// ����id
		btn_simple.setId(R.id.btn_main_simple);
		// ���õ���¼�
		btn_simple.setOnClickListener(this);
		// ��ӵ�����
		contentView.addView(btn_simple);

		// ������ťQQ
		btn_qq = new Button(this);
		// ����(layout_marginTop = 20dp)
		layoutParamsWW.topMargin = (int) ViewUtil.dip2px(this, 20f);
		// ����(layout_width = 100dp)
		layoutParamsWW.width = (int) ViewUtil.dip2px(this, 100f);
		// ��Ӳ�������
		btn_qq.setLayoutParams(layoutParamsWW);
		// �����ʾ����
		btn_qq.setText(R.string.tt_main_qq);
		// ���ݲ��־���
		btn_qq.setGravity(Gravity.CENTER);
		// ����id
		btn_qq.setId(R.id.btn_main_qq);
		// ���õ���¼�
		btn_qq.setOnClickListener(this);
		// ��ӵ�����
		contentView.addView(btn_qq);

		// ��Ӳ��ֵ�Activity
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
