package com.tz.testlogcat.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import com.tz.testlogcat.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * @author 作者 tz_stiven QQ:114442034 
 * @version 创建时间：2015年7月14日 下午11:03:32 
 * 主界面
 */
public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Context mContext;
	
	private TextView mTv;
	private Button mBtn;
	private Button mBtn2;
	private Button mBtn3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.actiivty_main);
		
		initView();
	}
	
	private void initView(){
		mTv = (TextView)findViewById(R.id.tv_pbonenum);
		mBtn = (Button)findViewById(R.id.btn_getlag);
		mBtn2 = (Button)findViewById(R.id.btn_getphonenum);
		mBtn3 = (Button)findViewById(R.id.btn_takephone);
		
		mBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.i(TAG, "--Button-> " + "获取Log信息");
				ArrayList<String> cmdLine = new ArrayList<String>();
				cmdLine.add("logcat");
				cmdLine.add("-d");
				cmdLine.add("-s");
				cmdLine.add("MainActivity");
				
				String[] strs = new String[cmdLine.size()];
				
				try {
					Process process = Runtime.getRuntime().exec(cmdLine.toArray(strs));
					InputStream is = process.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String str = null;
					StringBuffer sb = new StringBuffer();
					while((str = br.readLine()) != null){
						sb.append(str);
						sb.append("/n");
					}
					
					Toast.makeText(mContext, sb.toString(), Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		mBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, 1);
			}
		});
		
		mBtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mTv.getText().toString().trim().equals("")){
					Toast.makeText(mContext, "请先选择联系人", Toast.LENGTH_SHORT).show();
					return;
				}
				
				String tal = mTv.getText().toString();
				tal = tal.replace(" ", "").replace("-", "");
				Toast.makeText(mContext, tal, Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent();
				intent.setAction("android.intent.action.CALL");
				intent.setData(Uri.parse("tel:" + tal));
//				intent.setData(Uri.parse("tel:18616786698"));
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			if(resultCode == RESULT_OK){
//				if(data == null)
//				{
//					return;
//				}
//				
//				Uri result = data.getData();
//				ContentResolver cr = getContentResolver();
//				Cursor cursor = cr.query(result, null, null, null, null);
//				
//				cursor.moveToFirst();
//				String num = getContactPhone(cursor);
//				mTv.setText("姓名: " + num + "/n" + "手机号： ");
				
				if(data == null){
					return;
				}
				Uri result = data.getData();
				
				ContentResolver cr = getContentResolver();
				Cursor cursor = cr.query(result, null, null, null, null);
				cursor.moveToFirst();
				String userName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
						null, 
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
						null, 
						null);
				while(phone.moveToNext()){
					String userNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					mTv.setText(userNumber);
				}
						
			}
		}
	}
	
	private String getContactPhone(Cursor cursor) {
		// TODO Auto-generated method stub
		int phoneColumn = cursor
				.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
		int phoneNum = cursor.getInt(phoneColumn);
		String result = "";
		if (phoneNum > 0) {
			// 获得联系人的ID号
			int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
			String contactId = cursor.getString(idColumn);
			// 获得联系人电话的cursor
			Cursor phone = getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
							+ contactId, null, null);
			if (phone.moveToFirst()) {
				for (; !phone.isAfterLast(); phone.moveToNext()) {
					int index = phone
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
					int typeindex = phone
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
					int phone_type = phone.getInt(typeindex);
					String phoneNumber = phone.getString(index);
					result = phoneNumber;
				}
				if (!phone.isClosed()) {
					phone.close();
				}
			}
		}
		return result;
	}
	
}
