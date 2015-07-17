package com.tz.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class CodeLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_code_layout);
		// ��ʼ�����������
		RelativeLayout parentLayout = new RelativeLayout(this);
		LayoutParams parentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		parentParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		parentLayout.setLayoutParams(parentParams);
		
		//��ʼ������
		LinearLayout lineLayout = new LinearLayout(this);
		lineLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		// ��ʼ���ı��� 
		EditText editText = new EditText(this);
		editText.setHint("��������ַ");
		// ��ʼ�����ֲ���
		LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
		// ���ò��ֲ���
		editText.setLayoutParams(editTextParams);
		// ��EditText ��ӵ����Բ�����
		lineLayout.addView(editText);
		
		// ��ʼ��Button 
		Button button = new Button(this);
		button.setText("����");
		// ��ʼ�����ֲ���
		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0);
		// ���ò��ֲ���
		button.setLayoutParams(buttonParams);
		// ��Button ��ӵ����Բ�����
		lineLayout.addView(button);
		lineLayout.setLayoutParams(lineParams);
		// �����Բ�����ӵ���������
		parentLayout.addView(lineLayout);
		
		/*ImageView imageView = new ImageView(this);
		// ����ImageView ���ŷ�ʽ 
		imageView.setScaleType(ScaleType.FIT_XY);
		// ����ͼƬ 
		imageView.setImageResource(R.drawable.ico_01);
		LayoutParams imageViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		imageViewParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		imageView.setLayoutParams(imageViewParams);
		// ����ImageView ��ӵ���������
		parentLayout.addView(imageView);*/
		
		setContentView(parentLayout);
		
	}

}
