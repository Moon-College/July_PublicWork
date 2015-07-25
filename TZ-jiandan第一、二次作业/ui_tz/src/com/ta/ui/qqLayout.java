package com.ta.ui;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class qqLayout extends Activity {
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	     
	        //创建父容器---线性布局LinearLayout  默认是水平方向
	        LinearLayout ll = new LinearLayout(this);
	        ll.setOrientation(LinearLayout.VERTICAL);//设置垂直方向
	        ll.setBackgroundColor(color.white);
	        //LinearLayout的布局属性
	        LayoutParams lp = new LayoutParams(
	        		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);//宽高属性
	        ll.setLayoutParams(lp);
	        
	       /*
	        *第一个布局
	        *父控件的属性可放在子控件中
	        */
	       // LayoutParams.LinearLayout l1 = new LayoutParams.LinearLayout(this);
	        
	        
	        
	        
	        
	        
	        
	        
	        
	         
	       
	        
	        setContentView(ll);
	    }
}
