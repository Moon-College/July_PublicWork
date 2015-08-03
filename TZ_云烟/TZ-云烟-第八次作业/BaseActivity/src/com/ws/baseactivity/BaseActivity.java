package com.ws.baseactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


public class BaseActivity extends Activity {

	private Button btn_left;
	private Button btn_rigth;
	private TextView tv_bar;
	private LinearLayout layout;
	private View contentview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseactivity);
		btn_left = (Button)findViewById(R.id.btn_left);
		btn_rigth = (Button)findViewById(R.id.btn_right);
		tv_bar = (TextView)findViewById(R.id.tv_bar);
		layout = (LinearLayout) findViewById(R.id.layout);
		//点击退出
		btn_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		btn_rigth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(BaseActivity.this, "哈哈哈！！！", 1).show();
			}
		});
		
		
	}
	protected Button getBtnLeft(){
		return btn_left;
	} 
	protected Button getBtnRigth(){
		return btn_rigth;
	} 
	protected void setContentLayout(int resId){
		View contentView = View.inflate(getApplicationContext(), resId, null); 
		setContentLayout(contentView);
	}
	protected void setContentLayout(View contentview){
		this.contentview = contentview;
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		contentview.setLayoutParams(lp);
		if (layout!=null) {
			layout.addView(contentview);
		}
		
	}
	//设置布局背景颜色
	protected View getMyContentView(){
		return contentview;
	}
	
	protected void hideLeftBtn(){
		if(btn_left!=null){
		btn_left.setVisibility(Button.INVISIBLE);
		}
	}
	protected void hideRigthBtn(){
		if(btn_rigth!=null){
			btn_rigth.setVisibility(Button.INVISIBLE);
		}
	}
	protected void setTitle(String text){
		if(tv_bar!=null){
			tv_bar.setText(text);
		}
	}
	protected void setBtnLeftBtnImg(int img) {
		if (btn_left!=null){
			btn_left.setBackgroundResource(img);
		}
	}
	protected void setBtnRigthImg(int img){
		if(btn_rigth!=null){
			btn_rigth.setBackgroundResource(img);
		}
	}
}
