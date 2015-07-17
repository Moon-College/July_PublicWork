package com.tz.qqlayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DyncLayoutActivity extends ActionBarActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//定义最外层垂直布局容器
		LinearLayout rootView = new LinearLayout(this);
		rootView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(layoutParams);
        
        //动态代码生成水平布局文件
        View dyncHoriLayout = dyncLayout();
        //使用Xml文件生成水平布局文件
        View xmlHoriLayout = staticLayout();
        
        //添加到根布局容器
        rootView.addView(dyncHoriLayout);
        rootView.addView(xmlHoriLayout);
        setContentView(rootView);
		
	}

	//动态代码生产布局控件
	private View dyncLayout() {
		//定义水平布局容器
        LinearLayout dyncHoriLayout = new LinearLayout(this);
        dyncHoriLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dyncHoriLayout.setLayoutParams(layoutParams);
        
        //定义EditText控件
        EditText editEdt = new EditText(this);
        LinearLayout.LayoutParams edtParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        edtParams.weight = 1.0F;
        editEdt.setLayoutParams(edtParams);
        editEdt.setHint("请输入！");
        
        //定义一个Button显示在EditText控件的右边
        Button clickBtn = new Button(this);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //btnParams.weight = 0.0F;  //默认是0.0F
        clickBtn.setLayoutParams(btnParams);
        clickBtn.setText("点击"); //设置按钮文本属性
       

        dyncHoriLayout.addView(editEdt);
        dyncHoriLayout.addView(clickBtn);
        
        return dyncHoriLayout;
	}

	//使用XML文件布局控件
	private View staticLayout() {
		View view = LayoutInflater.from(this).inflate(R.layout.static_layout, null);
		return view;
	}
	
}
