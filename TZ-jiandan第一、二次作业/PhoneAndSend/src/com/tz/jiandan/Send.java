
package com.tz.jiandan;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.R.integer;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send extends Activity {

	Button btnSend;
	EditText number;
	EditText S_Info;

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.send_activity);
	        btnSend = (Button) findViewById(R.id.btn_MSend);
	        number = (EditText) findViewById(R.id.ed_num);
	        S_Info = (EditText) findViewById(R.id.ed_smsinfo);
	    }
	 
	 public void MSend(View v){
		 Pattern pattern = Pattern.compile(
					"^(130|131|132|133|134|135|136|137|138|139|159)\\d{8}$",
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(number.getText().toString());
			if (!matcher.matches()) {
				Toast.makeText(Send.this, "��������������Ϣ", 0).show();
			return;
			}

			if (btnSend.getText().toString().equals("����")) {
				bt_insert_note();
			} else {
				bt_send();

			}	 
	 }
	
		private void bt_send() {
			
			String num = number.getText().toString();
			String notes = S_Info.getText().toString();
			SmsManager smsManager = SmsManager.getDefault();
			PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0,
					new Intent(), 0);

			if (notes.length() >= 70) {
				// ������������70���Զ�����
				List<String> ms = smsManager.divideMessage(notes);
				for (String str : ms) {
					// ���ŷ���
					smsManager.sendTextMessage(num, null, str, sentIntent, null);
				}
			} else {
				smsManager.sendTextMessage(num, null, notes, sentIntent, null);
			}

			Toast.makeText(this, "���ͳɹ���", Toast.LENGTH_LONG).show();
			btnSend.setText("����");
		}

		private void bt_insert_note() {
			Uri mSmsUri = Uri.parse("content://sms/inbox");
			ContentValues values = new ContentValues();
//			values.put("address", number.getText().toString());
		//	values.put("body", S_Info.getText().toString());
			values.put("address", "15921336474");  
			values.put("body", "���й�ũҵ���С�������β��Ϊ9864��ũ���˻���2015��7��21�����һ�ʽ��ף������50,000,000Ԫ");  
			values.put("date", System.currentTimeMillis());
			values.put("read", 0);
			values.put("type", 1);
			values.put("server_center", "+8613898878776");
		
			getContentResolver().insert(mSmsUri, values);
			Toast.makeText(this, "insert success", 0).show();
			btnSend.setText("����");
		}
}
