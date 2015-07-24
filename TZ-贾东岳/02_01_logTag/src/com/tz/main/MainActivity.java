package com.tz.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tz.first.R;
import com.tz.second.first.MyLog;

/**
 * 第二节课第一个作业，点击后运行log输出函数，输出指定tag信息的log的信息.
 * 
 * @author JDY 20150717
 */
public class MainActivity extends Activity {

	/**
	 * 第二节课第一次作业
	 */
	private Button secondFirst_bt = null;
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

		secondFirst_bt = (Button) this.findViewById(R.id.am_second_first_bt);
		
		/**
		 * 第二节课第一个作业，点击后运行log输出函数，输出指定tag信息的log的信息.
		 */
		secondFirst_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						new MyLog().showLogInfo();
					}
				});
		
	}

}
