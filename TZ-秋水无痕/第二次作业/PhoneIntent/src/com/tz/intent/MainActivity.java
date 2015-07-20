package com.tz.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener{
	private EditText et;
	private Button btn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        //代码动态布局
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        
        //edittext  button
        et = new EditText(this);
        LinearLayout.LayoutParams et_llp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT,1);
        et.setHint("请输入电话号码");
        et.setLayoutParams(et_llp);
        btn = new Button(this);
        LinearLayout.LayoutParams btn_llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0);
        btn.setText("打电话");
        btn.setLayoutParams(btn_llp);
        btn.setOnClickListener(this);
        
        ll.addView(et,0);
        ll.addView(btn,1);
        setContentView(ll);
        
        
    }

	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_DIAL);//拨号盘
		String number = et.getText().toString();
		intent.setData(Uri.parse("tel:"+number.trim()));
		startActivity(intent);
	}
    
    
    
}