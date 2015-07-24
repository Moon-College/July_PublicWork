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
     
        //����������
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);//����ˮƽ����
        //LinearLayout�Ĳ�������
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);//�������
        ll.setLayoutParams(lp);
        
        //EditText����
        EditText ed = new EditText(this);
        //EditText��LinearLayout�еĲ�������--���
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,2);
        ed.setLayoutParams(llp);
        ll.addView(ed, 0);
        
        //Button����
        Button btn = new Button(this);
        //EditText��LinearLayout�еĲ�������
        LinearLayout.LayoutParams lbtn = new LinearLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0);
        btn.setLayoutParams(lbtn);
        btn.setText("��  ��");
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