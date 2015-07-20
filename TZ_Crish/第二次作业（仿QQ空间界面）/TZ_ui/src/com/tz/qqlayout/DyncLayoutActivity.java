package com.tz.qqlayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DyncLayoutActivity extends ActionBarActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//��������㴹ֱ��������
		LinearLayout rootView = new LinearLayout(this);
		rootView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(layoutParams);
        
        //��̬��������ˮƽ�����ļ�
        View dyncHoriLayout = dyncLayout();
        //ʹ��Xml�ļ�����ˮƽ�����ļ�
        View xmlHoriLayout = staticLayout();
        
        //��ӵ�����������
        rootView.addView(dyncHoriLayout);
        rootView.addView(xmlHoriLayout);
        setContentView(rootView);
		
	}

	//��̬�����������ֿؼ�
	private View dyncLayout() {
		//����ˮƽ��������
        LinearLayout dyncHoriLayout = new LinearLayout(this);
        dyncHoriLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dyncHoriLayout.setLayoutParams(layoutParams);
        
        //����EditText�ؼ�
        EditText editEdt = new EditText(this);
        LinearLayout.LayoutParams edtParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        edtParams.weight = 1.0F;
        editEdt.setLayoutParams(edtParams);
        editEdt.setHint("�����룡");
        
        //����һ��Button��ʾ��EditText�ؼ����ұ�
        Button clickBtn = new Button(this);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //btnParams.weight = 0.0F;  //Ĭ����0.0F
        clickBtn.setLayoutParams(btnParams);
        clickBtn.setText("���"); //���ð�ť�ı�����
       

        dyncHoriLayout.addView(editEdt);
        dyncHoriLayout.addView(clickBtn);
        
        return dyncHoriLayout;
	}

	//ʹ��XML�ļ����ֿؼ�
	private View staticLayout() {
		View view = LayoutInflater.from(this).inflate(R.layout.static_layout, null);
		return view;
	}
	
}
