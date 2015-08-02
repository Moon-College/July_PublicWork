package com.tz.baseactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * �Լ���BaseActivity
 * @author �Խ���
 *
 */
public class MainActivity extends BaseActivity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�Զ���Activity");
		// ��������
		setBaseContentView(R.layout.main);
		// ���ñ���ɫ
		setBaseContentBackGroundColor(Color.RED);

		// ��ȡ���ذ�ť�����õ���¼�
		Button back = getBt_back();
		Button more = getBt_more();

		back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("����");
				builder.setMessage("ȷ��Ҫ�˳���");
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						});
				builder.setNegativeButton("ȡ��",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
			}
		});

		more.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "���ڻ�ȡ������Ϣ...",
						Toast.LENGTH_LONG).show();
			}
		});
		
		//��ȡ����״̬
		int netState=this.netState;
		Toast.makeText(this, NET_STATE[netState], Toast.LENGTH_LONG).show();
	}
}