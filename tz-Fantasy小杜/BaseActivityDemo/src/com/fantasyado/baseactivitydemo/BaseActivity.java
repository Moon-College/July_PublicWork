package com.fantasyado.baseactivitydemo;

import com.fantasyado.baseactivitydemo.view.ScrollView;

import android.R.integer;
import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

/**
 * created at:2015年8月2日 上午7:49:59 project name:BaseActivityDemo
 * File name:BaseActivity.java
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 
 * @Description  加载自己的布局调用addMylayout()方法
 *        移除标题栏调用hideHead()方法 获取根容器调用getRootView()方法
 */

public class BaseActivity extends Activity implements OnClickListener {

	private ImageButton btn_more;
	private ImageButton btn_back;
	private TextView title;
	private ViewGroup container;
	private ViewGroup head;
	private ScrollView defaultContentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_layout);
		container = (ViewGroup) findViewById(R.id.container);
		defaultContentView = (ScrollView) container
				.findViewById(R.id.scrollView);
		head = (ViewGroup) container.findViewById(R.id.head);
		btn_back = (ImageButton) container.findViewById(R.id.back);
		btn_more = (ImageButton) container.findViewById(R.id.more);
		title = (TextView) container.findViewById(R.id.title);
		btn_back.setOnClickListener(this);
		btn_more.setOnClickListener(this);

	}

	protected void removeDefaultContent() {
		if (defaultContentView!=null) {
			container.removeView(defaultContentView);
		}
	}

	protected void addMylayout(View v) {
		container.addView(v );
	}

	public View getRootView() {
		return container;
	}

	public void hideHead() {
		if (head != null) {
			head.setVisibility(View.INVISIBLE);
		}
	}

	public void showHead() {
		if (head != null) {
			head.setVisibility(View.VISIBLE);
		}
	}

	protected void setTitle(String mtitle) {
		title.setText(mtitle);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.more:

			break;

		default:
			break;
		}

	}

}
