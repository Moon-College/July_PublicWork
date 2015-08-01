package com.tz.lsn3;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Homework1Activity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//LinearLayout 布局
		LinearLayout ll = new LinearLayout(this);
		//LinearLayout  设置为水平方向布局
		ll.setOrientation(LinearLayout.HORIZONTAL);
		
		//设置LinearLayout 属性
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		//文本编辑器
		EditText et_rearch = new EditText(this);
		//LinearLayout 布局下面的 EditText 的属性
		LinearLayout.LayoutParams lp_et = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		//设置属性的权重 为1 
		lp_et.weight = 1;
		//将属性值加到 EditText控件
		et_rearch.setLayoutParams(lp_et);
		//将EditText默认提示为 "请输入键关键字"
		et_rearch.setHint("请输入键关键字");
		//将 EditText 加载 到 LinearLayout 中
		ll.addView(et_rearch);
		
		
		//按钮  注释基本同EditText 类似
		Button btn_rearch = new Button(this);
		LinearLayout.LayoutParams lp_btn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp_btn.weight = 0;
		lp_btn.width =  120;
		btn_rearch.setLayoutParams(lp_btn);
		btn_rearch.setText("搜索");
		ll.addView(btn_rearch);
		
		
		//加载动态 LinearLayout 布局
		setContentView(ll);
		
	}
}
