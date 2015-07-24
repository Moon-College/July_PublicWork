package com.tz.main;

import android.app.Activity;
import android.os.Bundle;

import com.tz.first.R;

/**
 * 第四节课第一个作业，弹出在xml文件中使用相对布局等布局实现手机版qq中的"动态"页面的界面. 
 * 
 * @author JDY 20150717
 */
public class MainActivity extends Activity {

	/**
	 * 第四节课第一个作业，点击后弹出在xml文件中使用相对布局等布局实现手机版qq中的"动态"页面的界面. 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qq_layout);
	}

}
