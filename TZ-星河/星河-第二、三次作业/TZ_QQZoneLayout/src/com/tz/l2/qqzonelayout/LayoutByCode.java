package com.tz.l2.qqzonelayout;

import com.example.tz_qqzonelayout.R;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class LayoutByCode extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��Բ���
		RelativeLayout rl=new RelativeLayout(this);
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		rl.setBackgroundColor(color.white);
		rl.setLayoutParams(params);
		
		
		//�����
		EditText et=new EditText(this);
		RelativeLayout.LayoutParams etParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		etParams.addRule(RelativeLayout.LEFT_OF,R.id.bt);
		et.setLayoutParams(etParams);
		
		//��ť
		Button bt=new Button(this);
		RelativeLayout.LayoutParams btParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		btParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		bt.setId(R.id.bt);
		bt.setLayoutParams(btParams);
		bt.setText("����");
		
		rl.addView(et);
		rl.addView(bt);
		
		setContentView(rl);
	}

}
