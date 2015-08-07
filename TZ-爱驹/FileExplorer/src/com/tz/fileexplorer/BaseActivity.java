package com.tz.fileexplorer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {

	private RelativeLayout contentView;
	private TextView title;
	private LinearLayout titleBar;
	private Button backBtn;
	private Button moreBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initComponent();
		setTitleColor(Color.rgb(40, 230, 220));
	}
	
	
	private void initComponent(){
		contentView = (RelativeLayout)findViewById(R.id.content);
		title = (TextView)findViewById(R.id.title);
		titleBar = (LinearLayout)findViewById(R.id.title_bar);
		backBtn = (Button)findViewById(R.id.btn_back);
		moreBtn = (Button)findViewById(R.id.btn_more);
	}
	
	protected void setTitle(String name,int textColor){
		title.setText(name);
		title.setTextColor(textColor);
	}
	
	protected void setMainContentView(int resId){
		View child = View.inflate(this, resId, null);
		contentView.addView(child);
	}
	
	public void setTitleBarColor(int color){
		titleBar.setBackgroundColor(color);
	}
	
	protected void hideBackButton(boolean b){
		if(b){
			backBtn.setVisibility(View.VISIBLE);
		}
	}
	
	protected void hideMoreButton(boolean b){
		if(b){
			moreBtn.setVisibility(View.VISIBLE);
		}
	}
	
	protected Button getBackButton(){
		return backBtn;
	}
	
	protected Button getMoreButton(){
		return moreBtn;
	}
}
