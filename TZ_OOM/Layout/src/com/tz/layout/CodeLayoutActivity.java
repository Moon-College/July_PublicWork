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
		// 初始化最外层容器
		RelativeLayout parentLayout = new RelativeLayout(this);
		LayoutParams parentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		parentParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		parentLayout.setLayoutParams(parentParams);
		
		//初始化容器
		LinearLayout lineLayout = new LinearLayout(this);
		lineLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		// 初始化文本框 
		EditText editText = new EditText(this);
		editText.setHint("请输入网址");
		// 初始化布局参数
		LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
		// 设置布局参数
		editText.setLayoutParams(editTextParams);
		// 将EditText 添加到线性布局中
		lineLayout.addView(editText);
		
		// 初始化Button 
		Button button = new Button(this);
		button.setText("搜索");
		// 初始化布局参数
		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0);
		// 设置布局参数
		button.setLayoutParams(buttonParams);
		// 将Button 添加到线性布局中
		lineLayout.addView(button);
		lineLayout.setLayoutParams(lineParams);
		// 将线性布局添加到父容器中
		parentLayout.addView(lineLayout);
		
		/*ImageView imageView = new ImageView(this);
		// 设置ImageView 缩放方式 
		imageView.setScaleType(ScaleType.FIT_XY);
		// 设置图片 
		imageView.setImageResource(R.drawable.ico_01);
		LayoutParams imageViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		imageViewParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		imageView.setLayoutParams(imageViewParams);
		// 将线ImageView 添加到父容器中
		parentLayout.addView(imageView);*/
		
		setContentView(parentLayout);
		
	}

}
