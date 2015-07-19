package com.lx.eidttext.main;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.print.PrintAttributes.Margins;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button btn;
	private EditText ll_edt;
	private LinearLayout ll;
	private int number;
	//定义一个颜色的数组s
	private int  [] colors = new int[]{
			Color.BLACK,
			Color.BLUE,
			Color.DKGRAY,
			Color.GRAY,
			Color.GREEN
	};
	private  List<Integer>  color = new ArrayList<Integer>();
   
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ll = new  LinearLayout(this);
     LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	 ll.setBackgroundColor(Color.BLUE);
	 ll.setLayoutParams(params);
 	 
	 ll_edt = new EditText(this);
	 LinearLayout.LayoutParams ll1 = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 2);
	 ll_edt.setLayoutParams(ll1);
	 ll_edt.setId(1);
	 ll_edt.setHint("请输入你的账号:");
	 
	 btn = new  Button(this);
     LinearLayout.LayoutParams   btn_params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
     btn.setLayoutParams(btn_params);
     btn.setText("确定");
     btn.setOnClickListener(  new MyOnClickListener());
     
     
     
	 ll.addView(ll_edt);
	 ll.addView(btn);
	 setContentView(ll);
	 //动态的添加到颜色数组中
	 for (int i = 0; i < number; i++) {
         color.add(colors[i]);
       if(i>colors.length){
    	   i = 0;
       }
	 }
	 
	 
	} 
   /**
    * 定义一个渐渐事件
    * */
   public class  MyOnClickListener implements OnClickListener{

	private TextView txt;
	

	@Override
	public void onClick(View v) {
	   number = Integer.valueOf(ll_edt.getText().toString().trim());
	   Log.i("INFO", String.valueOf(number));
	   for (int i = 0; i <= number; i++) {
		  //LinearLayout btn_ll = new LinearLayout(MainActivity.this);
		 // btn_ll.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		  //btn_ll.setOrientation(LinearLayout.VERTICAL);
          ll.setOrientation(LinearLayout.VERTICAL);
		  txt = new TextView(MainActivity.this);
          txt.setLayoutParams( new LinearLayout.LayoutParams(50, 50));
          txt.setGravity(Gravity.CENTER);
          txt.setText("换印尼 我的爱人");
          txt.setBackgroundColor(colors[3]);
          txt.setTextColor(Color.WHITE);
         // MarginLayoutParams  margin = new MarginLayoutParams(0, 6);
          //txt.setLayoutParams(margin);
           //txt.setLayoutParams()
          //btn_ll.addView(txt);
          ll.addView(txt);
	   }
	}
	   
   } 

}
