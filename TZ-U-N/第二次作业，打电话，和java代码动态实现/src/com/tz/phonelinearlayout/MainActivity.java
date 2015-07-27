package com.tz.phonelinearlayout;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.UriPermission;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private Button bt_call;
	private EditText et_son;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
//		bt_call=(Button)findViewById(R.id.bt_call);
		et_son=(EditText)findViewById(R.id.et_son);
		//动态代码布局
		LinearLayout ll = new LinearLayout(this);//new 一个线性布局		
		ll.setOrientation(LinearLayout.VERTICAL);//声明垂直或者水平的		
		LayoutParams pp =new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);//设置长宽
		ll.setLayoutParams(pp);//设置长宽的参数
		
		
		EditText et = new EditText(this);//new一个文本框控件
		LinearLayout.LayoutParams et_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);		
		et.setLayoutParams(et_lp);//设置长宽的参数

		Button bt = new Button(this);//new 按钮控件
		LinearLayout.LayoutParams bt_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		et.setLayoutParams(bt_lp);//设置长宽的参数
		
		ll.addView(bt);
		ll.addView(et);//在android中，addView(ViewGroup view, index)在指定的index处添加一个view。这种方式有一个限制，就是我们无法再任意位置添加view.我们只能顺序添加。
		//如果我们的需求要求按照一定的顺序进行添加时，我们只能先对各项进行排序，然后在一项一项的添加。
		
		setContentView(ll);
		

/**	setContentView(ll);//R.layout.main是个布局文件即控件都是如何摆放如何显示的，setContentView就是设置一个Activity的显示界面，
 * 这句话就是设置这个这句话所再的Activity采用R.layout下的main布局文件进行布局
 * 
 * ll.addView(et);//在android中，addView(ViewGroup view, index)在指定的index处添加一个view。这种方式有一个限制，
 * 就是我们无法再任意位置添加view.我们只能顺序添加。如果我们的需求要求按照一定的顺序进行添加时，我们只能先对各项进行排序，然后在一项一项的添加。
 * 
 * ll.addView(et);//在android中，addView(ViewGroup view, index)在指定的index处添加一个view。这种方式有一个限制，就是我们无法再任意位置添加view.我们只能顺序添加。
	如果我们的需求要求按照一定的顺序进行添加时，我们只能先对各项进行排序，然后在一项一项的添加。
 * 
 * */
			
		
	}

	public void callphone(View v){
		/**
		 * 调用系统组件打电话
		 * 	Intent intent = new Intent();声明一个对象，（意图）
		 * Uri资源的统一定位符，除了可以指定网络资源，还可以指定一些文件（歌曲，图片，文件），还可以指定到数据库某个表里的某个字段里的数据
		 * startActivity(intent); 把意图给他，他会找到可以执行这个意图的组件
		 * 
		 * <uses-permission android:name="android.permission.CALL_PHONE"/>
//		<!-- 需要声明打电话权限 -->
		 * */
		Intent intent = new Intent();
		//intent.setAction(Intent.ACTION_CALL);
		intent.setAction(Intent.ACTION_DIAL);//跳转到拨号键盘
		String n =et_son.getText().toString();
		intent.setData(Uri.parse("tel:"+n.trim()));
		startActivity(intent);
		
		
	}
	
		public void etSms(View v){
			
			Intent intents = new Intent();
			intents.setAction(Intent.ACTION_CAMERA_BUTTON);
			
			startActivity(intents);
			
		}
	
}
