package com.example.autotipphonenumber;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;

import com.study.contactadapter.ContactAdapter;
import com.study.remind.Contact;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		final AutoCompleteTextView autoTip=(AutoCompleteTextView) this.findViewById(R.id.autoTip);
		autoTip.setThreshold(1);
		
		//��ʼ������
		ArrayList<Contact> Contacts=Contact.generateContact();	
		
		//�Զ���adapter
		ContactAdapter adapter=new ContactAdapter(this,Contacts);
		autoTip.setAdapter(adapter);		
		
		//���õ���¼���ȡ�ı�		
		autoTip.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Contact contact=(Contact) parent.getItemAtPosition(position);
				autoTip.setText(contact.getPhoneNum());
	
			}
		});

	}
}
