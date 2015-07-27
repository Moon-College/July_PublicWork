package com.ta.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Ui_tzActivity extends Activity { 
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        //创建父容器
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);//设置水平方向
        //LinearLayout的布局属性
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);//宽高属性
        ll.setLayoutParams(lp);
        
        //EditText布局
        EditText ed = new EditText(this);
        //EditText在LinearLayout中的布局属性--宽高
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,2);
        ed.setLayoutParams(llp);
        ll.addView(ed, 0);
        
        //Button布局
        Button btn = new Button(this);
        //EditText在LinearLayout中的布局属性
        LinearLayout.LayoutParams lbtn = new LinearLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0);
        btn.setLayoutParams(lbtn);
        btn.setText("搜  索");
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Ui_tzActivity.this, qqshow.class);
				startActivity(intent);
			}
		}); 
        ll.addView(btn, 1);
        
        setContentView(ll);
    }
}