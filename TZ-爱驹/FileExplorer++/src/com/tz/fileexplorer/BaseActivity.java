package com.tz.fileexplorer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {

	private ViewGroup contentView;
	private TextView title;
	private LinearLayout titleBar;
	private Button backBtn;
	private Button moreBtn;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initComponent();
		setTitleColor(Color.rgb(40, 230, 220));
	}

	private void initComponent() {
		contentView = (ViewGroup) findViewById(R.id.container);
		title = (TextView) findViewById(R.id.title);
		titleBar = (LinearLayout) findViewById(R.id.title_bar);
		backBtn = (Button) findViewById(R.id.btn_back);
		moreBtn = (Button) findViewById(R.id.btn_more);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		radioGroup = (RadioGroup) findViewById(R.id.group);
	}

	protected ViewGroup getContentView() {
		return contentView;
	}

	protected void setTitle(String name, int textColor) {
		title.setText(name);
		title.setTextColor(textColor);
	}

	protected void setMainContentView(int resId) {
		View child = View.inflate(this, resId, null);
		contentView.addView(child);
	}

	public void setTitleBarColor(int color) {
		titleBar.setBackgroundColor(color);
	}

	protected void hideBackButton(boolean b) {
		if (b) {
			backBtn.setVisibility(View.INVISIBLE);
		} else {
			backBtn.setVisibility(View.VISIBLE);
		}
	}

	protected void hideMoreButton(boolean b) {
		if (b) {
			moreBtn.setVisibility(View.INVISIBLE);
		} else {
			moreBtn.setVisibility(View.VISIBLE);
		}
	}

	public Button getBackButton() {
		return backBtn;
	}

	public Button getMoreButton() {
		return moreBtn;
	}

	/**
	 * 
	 * @param loca
	 *            Ö»ÄÜÊÇ1~4
	 * @return
	 */
	public RadioButton getRadioButton(int loca) {
		switch (loca) {
		case 1:
			return rb1;
		case 2:
			return rb2;
		case 3:
			return rb3;
		case 4:
			return rb4;
		default:
			throw new IllegalArgumentException(
					"parameter can not be smaller than 1 and bigger than 4");
		}
	}

	protected void setTabText(String t1, String t2, String t3, String t4) {
		rb1.setText(t1);
		rb2.setText(t2);
		rb3.setText(t3);
		rb4.setText(t4);
	}

	protected void setTabTextColor(int color) {
		rb1.setTextColor(color);
		rb2.setTextColor(color);
		rb3.setTextColor(color);
		rb4.setTextColor(color);
	}

	protected void setTabColor(int color) {
		radioGroup.setBackgroundColor(color);
	}

	public RadioGroup getRadioGroup() {
		return radioGroup;
	}
}
