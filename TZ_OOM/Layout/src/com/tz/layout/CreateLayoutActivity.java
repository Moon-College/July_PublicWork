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
		
		//初始化文本框
		editText = new EditText(this);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		editText.setHint("请输入一个大于0的数");
		// 初始化布局参数
		LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
		// 设置布局参数
		editText.setLayoutParams(editTextParams);
		// 将EditText 添加到线性布局中
		lineLayout.addView(editText);
				
		// 初始化Button 
		Button button = new Button(this);
		button.setText("创建");
		button.setOnClickListener(this);
		// 初始化布局参数
		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0);
		// 设置布局参数
		button.setLayoutParams(buttonParams);
		lineLayout.addView(button);
		// 将Button 添加到线性布局中
		lineLayout.setLayoutParams(lineParams);
		
		ScrollView scrollView = new ScrollView(this);
		LayoutParams scrollViewParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		scrollView.setLayoutParams(scrollViewParams);
		
		container = new LinearLayout(this);
		container.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		container.setLayoutParams(containerParams);
		// 将线性布局添加到父容器中
		parentLayout.addView(lineLayout);
		
		scrollView.addView(container);
		parentLayout.addView(scrollView);
		
		setContentView(parentLayout);
		
	}

	@Override
	public void onClick(View v) {
		String str = editText.getText().toString().trim();
		if(str.length() == 0) {
			Toast.makeText(this, "请输入一个大于0的数", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		int count = Integer.parseInt(str);
		if(count <= 0) {
			Toast.makeText(this, "请输入一个大于0的数", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		// 清空
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
