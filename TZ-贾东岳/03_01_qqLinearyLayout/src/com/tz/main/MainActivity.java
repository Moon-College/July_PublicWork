package com.tz.main;

import android.app.Activity;
import android.os.Bundle;

import com.tz.first.R;

/**
 * 第三节课第一个作业，在xml文件中只使用线性布局实现手机版qq中的"动态"页面的界面布局.
 * 
 * @author JDY
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qq_lineralayout);
	}

}
