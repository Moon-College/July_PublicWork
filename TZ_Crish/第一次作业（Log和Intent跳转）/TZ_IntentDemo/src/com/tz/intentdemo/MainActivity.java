package com.tz.intentdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//������ת����绰����İ�ť�¼�
		((Button) findViewById(R.id.btn_call_phone)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
//				//���Դ�ֵ���¸�Activity��
				//���ڴ˾����贫ֵ������
//				Bundle bundle = new Bundle();
//				bundle.putString("key", "value"); //������Ҫ���ݵ��ַ������͡���Ȼ�����Դ����������͵�(��object��List֮���)
//				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		//������ת�������Ž���İ�ť�¼�
		((Button) findViewById(R.id.btn_send_sms)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				//ϵͳĬ�ϵ�action��������Ĭ�ϵĶ��Ž���
				intent.setAction(Intent.ACTION_SENDTO);
				//��Ҫ����Ϣ�ĺ���
				intent.setData(Uri.parse("smsto:"));
				startActivity(intent);
			}
		});
	}

	
}
