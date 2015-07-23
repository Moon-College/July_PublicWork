package com.tz.jiandan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PhoneAndSendActivity extends Activity {
    /** Called when the activity is first created. */
	EditText ed_num;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ed_num = (EditText) findViewById(R.id.edit_num);
    }
    
  public void Call(View v){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		String number  = ed_num.getText().toString();
		intent.setData(Uri.parse("tel:"+number.trim()));
		startActivity(intent);
  }
  
  public void Send(View v){
	  Intent intent = new Intent();
	  intent.setClass(this, Send.class);
	//  intent.putExtra("btnsend", "¶ÌÐÅ");
	  startActivity(intent);
  
  }
  
  
}