package com.tz.lsn3;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Homework1Activity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//LinearLayout ����
		LinearLayout ll = new LinearLayout(this);
		//LinearLayout  ����Ϊˮƽ���򲼾�
		ll.setOrientation(LinearLayout.HORIZONTAL);
		
		//����LinearLayout ����
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		//�ı��༭��
		EditText et_rearch = new EditText(this);
		//LinearLayout ��������� EditText ������
		LinearLayout.LayoutParams lp_et = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		//�������Ե�Ȩ�� Ϊ1 
		lp_et.weight = 1;
		//������ֵ�ӵ� EditText�ؼ�
		et_rearch.setLayoutParams(lp_et);
		//��EditTextĬ����ʾΪ "��������ؼ���"
		et_rearch.setHint("��������ؼ���");
		//�� EditText ���� �� LinearLayout ��
		ll.addView(et_rearch);
		
		
		//��ť  ע�ͻ���ͬEditText ����
		Button btn_rearch = new Button(this);
		LinearLayout.LayoutParams lp_btn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp_btn.weight = 0;
		lp_btn.width =  120;
		btn_rearch.setLayoutParams(lp_btn);
		btn_rearch.setText("����");
		ll.addView(btn_rearch);
		
		
		//���ض�̬ LinearLayout ����
		setContentView(ll);
		
	}
}
