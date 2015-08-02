package com.example.template;



import com.example.mydemoactivity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {
	RelativeLayout rLayout;
	Button leftBtn,rightBtn;
	TextView midtext;
	LinearLayout mainll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initBaseView();
		
		
		setContentView();
		initParams();
		initView();
		regBroadcastReceiver();
		initListener();
	}
	
	public void initBaseView() {
		rLayout=(RelativeLayout)findViewById(R.id.base);
		leftBtn=(Button)findViewById(R.id.leftbtn);
		rightBtn=(Button)findViewById(R.id.rightbtn);
		midtext=(TextView)findViewById(R.id.midtext);
		mainll=(LinearLayout)findViewById(R.id.mainll);
	}
	
	public void setMidContent(String str){
		midtext.setText(str);
	}
	public Button getLeftButton(){
		return leftBtn;
	}
	
	public Button getRightButton(){
		return rightBtn;
	}
	
	public void setLeftBtnText(String str){
		if(leftBtn!=null){
			if(str!=null && !str.equals("")){
				leftBtn.setText(str);
			}else{
				leftBtn.setText("左边按钮");
			}
		}
	}
	
	public void setRightBtnText(String str){
    	if(rightBtn!=null){
			if(str!=null && !str.equals("")){
				rightBtn.setText(str);
			}else{
				rightBtn.setText("右边按钮");
			}
		}
	}
   public void setMain(int content){
	   View v = View.inflate(getApplicationContext(), content, null);
	   LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	   v.setLayoutParams(params);
	   mainll.addView(v);
   }
    
	//初始化布局
    public abstract void  setContentView();
	//初始化成员变量
	public abstract void initParams();
	//初始化view
	public abstract void initView();
	//初始化Listener
	public abstract void initListener();
	//注册广播
	public abstract void regBroadcastReceiver();
	//反注册广播
	public abstract void unregBroadcastReceiver();
	public abstract void beforeExit();
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregBroadcastReceiver();
		beforeExit();
	}
	
	
	
}
