package com.tz.lsn8_acivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class BaseActivity extends Activity {

    private LinearLayout ly_content;

	private Button btn_left;
	private Button btn_right;
	private TextView tv_title;

	private View contentView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //去掉主题 也可以在清单中设置主题theme="xxx.notitlebar"
      	requestWindowFeature(Window.FEATURE_NO_TITLE);
      	
        setContentView(R.layout.activity_common_title);
        ly_content = (LinearLayout)findViewById(R.id.ll_parent);
        
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_right = (Button)findViewById(R.id.btn_right);
        tv_title = (TextView)findViewById(R.id.tv_title);
    }
	
	//获取标题栏左边按钮控件
	protected Button getLeftBtn() {
		return btn_left;
	}
	
	//获取标题栏右边按钮控件
	protected Button getRightBtn() {
		return btn_right;
	}
	
	//设置layout资源文件
	protected void setContentLayout(int resId) {
		View contentView = View.inflate(getApplicationContext(), resId, null);
		setContentLayout(contentView);
	}
	
	//设置layout view
	protected void setContentLayout(View contentView) {
		this.contentView = contentView;
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		contentView.setLayoutParams(lp);
		if(ly_content != null) {
			ly_content.addView(contentView);
		}
	}
	
	//隐藏标题栏左边的图片
	protected void hideLeftBtn() {
		if(btn_left != null) {
			btn_left.setVisibility(View.INVISIBLE);
		}
	}
	
	//隐藏标题栏右边的图片
	protected void hideRightBtn() {
		if(btn_right != null) {
			btn_right.setVisibility(View.INVISIBLE);
		}
	}
	
	//设置标题栏标题
	protected void setTitle(String title) {
		if(tv_title != null) {
			tv_title.setText(title);
		}
	}
	
	//获取内容的视图资源
	protected View getContenView () {
		return contentView;
	}
	
	//设置标题栏左边的图片
	protected void setLeftBtnImage(int imgId){
		if(btn_left != null) {
			btn_left.setBackgroundResource(imgId);
		}
	}
	
	//设置标题栏右边的图片
	protected void setRightBtnImage(int imgId){
		if(btn_right != null) {
			btn_right.setBackgroundResource(imgId);
		}
	}

}
