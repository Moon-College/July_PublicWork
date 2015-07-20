package com.tz.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateLayoutActivity extends Activity implements OnClickListener {

	private EditText editText;
	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_create_layout);
		
		LinearLayout parentLayout = new LinearLayout(this);
		parentLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams parentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		parentLayout.setLayoutParams(parentParams);
		
		
		LinearLayout lineLayout = new LinearLayout(this);
		lineLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		//��ʼ���ı���
		editText = new EditText(this);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		editText.setHint("������һ������0����");
		// ��ʼ�����ֲ���
		LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
		// ���ò��ֲ���
		editText.setLayoutParams(editTextParams);
		// ��EditText ��ӵ����Բ�����
		lineLayout.addView(editText);
				
		// ��ʼ��Button 
		Button button = new Button(this);
		button.setText("����");
		button.setOnClickListener(this);
		// ��ʼ�����ֲ���
		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0);
		// ���ò��ֲ���
		button.setLayoutParams(buttonParams);
		lineLayout.addView(button);
		// ��Button ��ӵ����Բ�����
		lineLayout.setLayoutParams(lineParams);
		
		ScrollView scrollView = new ScrollView(this);
		LayoutParams scrollViewParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		scrollView.setLayoutParams(scrollViewParams);
		
		container = new LinearLayout(this);
		container.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		container.setLayoutParams(containerParams);
		// �����Բ�����ӵ���������
		parentLayout.addView(lineLayout);
		
		scrollView.addView(container);
		parentLayout.addView(scrollView);
		
		setContentView(parentLayout);
		
	}

	@Override
	public void onClick(View v) {
		String str = editText.getText().toString().trim();
		if(str.length() == 0) {
			Toast.makeText(this, "������һ������0����", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		int count = Integer.parseInt(str);
		if(count <= 0) {
			Toast.makeText(this, "������һ������0����", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		// ���
		container.removeAllViews();
		
		for (int i = 0; i < count; i++) {
			TextView textView = new TextView(this);
			textView.setGravity(Gravity.CENTER);
			textView.setBackgroundColor(Color.RED);
			textView.setPadding(0, 10, 0, 10);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 5, 0, 5);
			textView.setLayoutParams(params);
			
			textView.setText(String.valueOf(i+1));
			container.addView(textView);
		}
		
	}
}
