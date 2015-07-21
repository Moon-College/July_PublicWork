package com.tz.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tz.first.R;
import com.tz.fourth.first.QQLayoutActivity;
import com.tz.fourth.second.SimulateTableActivity;
import com.tz.second.first.MyLog;
import com.tz.second.second.IntentSample;
import com.tz.third.first.QQLineraLayoutActivity;

/**
 * 第二次作业,用来显示一个类似于安卓版qq的"空间"Tab页对应的界面.
 * 
 * @author JDY 20150717
 */
public class MainActivity extends Activity {

	/**
	 * 第二节课第一次作业
	 */
	private Button secondFirst_bt = null;
	/**
	 * 第二节课第二次作业
	 */
	private Button secondSecond_bt = null;
	/**
	 * 第三节课第一次作业
	 */
	private Button thrirdFirst_bt = null;
	/**
	 * 第四节课第一次作业
	 */
	private Button fourthFirst_bt = null;
	/**
	 * 第四节课第二次作业
	 */
	private Button fourthSecond_bt = null;

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
		secondSecond_bt = (Button) this.findViewById(R.id.am_second_second_bt);
		thrirdFirst_bt = (Button) this.findViewById(R.id.am_third_first_bt);
		fourthFirst_bt = (Button) this.findViewById(R.id.am_fourth_first_bt);
		fourthSecond_bt = (Button) this.findViewById(R.id.am_fourth_second_bt);
		
		/**
		 * 第二节课第一个作业，点击后运行log输出函数，输出指定tag信息的log的信息》
		 */
		secondFirst_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						new MyLog().showLogInfo();
					}
				});
		/**
		 * 第二节课第二个作业，点击后通过intent调用拨号程序拨打传入的电话号码
		 */
		secondSecond_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
//						new IntentSample().makeAPhoneCall("10086");
						new IntentSample().callDialPanel("10086");
					}
				});
		/**
		 * 第三节课第一个作业，点击后弹出在xml文件中只使用线性布局实现手机版qq中的"动态"页面的界面布局. 
		 */
		thrirdFirst_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
								QQLineraLayoutActivity.class);
						startActivity(intent);
					}
				});
		/**
		 * 第四节课第一个作业，点击后弹出在xml文件中使用相对布局等布局实现手机版qq中的"动态"页面的界面. 
		 */
		fourthFirst_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
								QQLayoutActivity.class);
						startActivity(intent);
					}
				});
		/**
		 * 第四节课第二个作业，模拟一个tableLayout，并给其每列不同的背景颜色. 
		 */
		fourthSecond_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
								SimulateTableActivity.class);
						startActivity(intent);
					}
				});
//		fourthSecond_bt.setClickable(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
