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
	     
	        //����������---���Բ���LinearLayout  Ĭ����ˮƽ����
	        LinearLayout ll = new LinearLayout(this);
	        ll.setOrientation(LinearLayout.VERTICAL);//���ô�ֱ����
	        ll.setBackgroundColor(color.white);
	        //LinearLayout�Ĳ�������
	        LayoutParams lp = new LayoutParams(
	        		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);//�������
	        ll.setLayoutParams(lp);
	        
	       /*
	        *��һ������
	        *���ؼ������Կɷ����ӿؼ���
	        */
	       // LayoutParams.LinearLayout l1 = new LayoutParams.LinearLayout(this);
	        
	        
	        
	        
	        
	        
	        
	        
	        
	         
	       
	        
	        setContentView(ll);
	    }
}
