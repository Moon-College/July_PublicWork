package com.tz.intentdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneActivity extends ActionBarActivity {
	
	private TextView mShowPhoneNumTv; //显示点击数字键盘的电话号码
	private Button mCallPhoneBtn; //拨打电话按钮
	private Button mCancelPhoneNumBtn; //清空电话号码
	private GridView mNumberKeyboardGv; //数字键盘
	private RelativeLayout mHasShowPhonenumRl; //显示或隐藏输入的电话号码
	
	//数字键盘数组
	private String[] phoneNumber = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};
	private List<Map<String, String>> numberList;
	
	private StringBuffer sb = null;
	private String phonenum = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);
		
		//初始化控件
		mShowPhoneNumTv = (TextView) findViewById(R.id.tv_show_phonenum);
		mCallPhoneBtn = (Button) findViewById(R.id.btn_call);
		mNumberKeyboardGv = (GridView) findViewById(R.id.gv_keyboard);
		mHasShowPhonenumRl = (RelativeLayout) findViewById(R.id.rl_show_phonenum);
		mCancelPhoneNumBtn = (Button) findViewById(R.id.btn_cancel);
		
		//设置控件的监听事件
		setOnClickListener();
		
		//设置GridView适配器
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, getPhoneNum(), R.layout.phone_gridview_item, new String[] {"number"}, new int[]{R.id.btn_number});
		mNumberKeyboardGv.setAdapter(simpleAdapter);
	}
	
	//设置监听事件
	private void setOnClickListener() {
		
		mNumberKeyboardGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//显示号码
				mHasShowPhonenumRl.setVisibility(View.VISIBLE);
				phonenum = mShowPhoneNumTv.getText().toString().trim();
				sb = new  StringBuffer(phonenum);
				sb.append(numberList.get(position).get("number"));
				mShowPhoneNumTv.setText(sb.toString());
				//Toast.makeText(PhoneActivity.this, numberList.get(position).get("number"), 0).show();
			}
		});
		
		mCancelPhoneNumBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(mShowPhoneNumTv != null) {
					mShowPhoneNumTv.setText("");
					//隐藏号码
					mHasShowPhonenumRl.setVisibility(View.GONE);
				}
			}
		});
		
		mCallPhoneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(mShowPhoneNumTv != null) {
					String phonenum = mShowPhoneNumTv.getText().toString().trim();
					//验证只要有号码就拨打
					if(phonenum.length() > 0) {
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenum));
						startActivity(intent);
					} else {
						Toast.makeText(PhoneActivity.this, "请输入电话号码", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
	}
	
	//获取数据
	private List<Map<String, String>> getPhoneNum() {
		numberList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(int i = 0; i < phoneNumber.length; i++) {
			map = new HashMap<String, String>();
			map.put("number", phoneNumber[i]);
			numberList.add(map);
		}
		return numberList;
	}
		
}
