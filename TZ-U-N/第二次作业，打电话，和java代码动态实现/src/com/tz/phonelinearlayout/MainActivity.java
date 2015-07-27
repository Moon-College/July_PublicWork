package com.tz.phonelinearlayout;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.UriPermission;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private Button bt_call;
	private EditText et_son;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
//		bt_call=(Button)findViewById(R.id.bt_call);
		et_son=(EditText)findViewById(R.id.et_son);
		//��̬���벼��
		LinearLayout ll = new LinearLayout(this);//new һ�����Բ���		
		ll.setOrientation(LinearLayout.VERTICAL);//������ֱ����ˮƽ��		
		LayoutParams pp =new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);//���ó���
		ll.setLayoutParams(pp);//���ó���Ĳ���
		
		
		EditText et = new EditText(this);//newһ���ı���ؼ�
		LinearLayout.LayoutParams et_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);		
		et.setLayoutParams(et_lp);//���ó���Ĳ���

		Button bt = new Button(this);//new ��ť�ؼ�
		LinearLayout.LayoutParams bt_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		et.setLayoutParams(bt_lp);//���ó���Ĳ���
		
		ll.addView(bt);
		ll.addView(et);//��android�У�addView(ViewGroup view, index)��ָ����index�����һ��view�����ַ�ʽ��һ�����ƣ����������޷�������λ�����view.����ֻ��˳����ӡ�
		//������ǵ�����Ҫ����һ����˳��������ʱ������ֻ���ȶԸ����������Ȼ����һ��һ�����ӡ�
		
		setContentView(ll);
		

/**	setContentView(ll);//R.layout.main�Ǹ������ļ����ؼ�������ΰڷ������ʾ�ģ�setContentView��������һ��Activity����ʾ���棬
 * ��仰�������������仰���ٵ�Activity����R.layout�µ�main�����ļ����в���
 * 
 * ll.addView(et);//��android�У�addView(ViewGroup view, index)��ָ����index�����һ��view�����ַ�ʽ��һ�����ƣ�
 * ���������޷�������λ�����view.����ֻ��˳����ӡ�������ǵ�����Ҫ����һ����˳��������ʱ������ֻ���ȶԸ����������Ȼ����һ��һ�����ӡ�
 * 
 * ll.addView(et);//��android�У�addView(ViewGroup view, index)��ָ����index�����һ��view�����ַ�ʽ��һ�����ƣ����������޷�������λ�����view.����ֻ��˳����ӡ�
	������ǵ�����Ҫ����һ����˳��������ʱ������ֻ���ȶԸ����������Ȼ����һ��һ�����ӡ�
 * 
 * */
			
		
	}

	public void callphone(View v){
		/**
		 * ����ϵͳ�����绰
		 * 	Intent intent = new Intent();����һ�����󣬣���ͼ��
		 * Uri��Դ��ͳһ��λ�������˿���ָ��������Դ��������ָ��һЩ�ļ���������ͼƬ���ļ�����������ָ�������ݿ�ĳ�������ĳ���ֶ��������
		 * startActivity(intent); ����ͼ�����������ҵ�����ִ�������ͼ�����
		 * 
		 * <uses-permission android:name="android.permission.CALL_PHONE"/>
//		<!-- ��Ҫ������绰Ȩ�� -->
		 * */
		Intent intent = new Intent();
		//intent.setAction(Intent.ACTION_CALL);
		intent.setAction(Intent.ACTION_DIAL);//��ת�����ż���
		String n =et_son.getText().toString();
		intent.setData(Uri.parse("tel:"+n.trim()));
		startActivity(intent);
		
		
	}
	
		public void etSms(View v){
			
			Intent intents = new Intent();
			intents.setAction(Intent.ACTION_CAMERA_BUTTON);
			
			startActivity(intents);
			
		}
	
}
