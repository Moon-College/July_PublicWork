package com.tz.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tz.first.R;
import com.tz.second.second.IntentSample;

/**
 * 第二次课第二个作业，intent的使用.
 * 
 * @author JDY 20150717
 */
public class MainActivity extends Activity {

	/**
	 * 第二节课第二次作业
	 */
	private Button secondSecond_bt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	/**
	 * 初始化函数，对界面要用到的图片进行大小初始化，并将其绑定到对应的控件上
	 */

	private void init() {

		secondSecond_bt = (Button) this.findViewById(R.id.am_second_second_bt);

		/**
		 * 第二节课第二个作业，点击后通过intent调用拨号程序拨打传入的电话号码
		 */
		secondSecond_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						new IntentSample().callDialPanel("10086");
					}
				});
	}

	
}
