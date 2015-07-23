package com.tz.fourth.first;

import com.tz.first.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * 第四节课第一个作业 . 在布局文件中使用相对布局等布局实现手机版qq中的"动态"页面的界面布局.
 * 
 * @author JDY
 * 
 */
public class QQLayoutActivity extends Activity {
	/**
	 * 点击后返回上级界面
	 */
	private Button back_bt = null;
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qq_layout);
		back_bt = (Button)findViewById(R.id.aqql_return_bt);
		back_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
			public void onClick(View v) {
				finish();
			}
		});
	}
	
}
