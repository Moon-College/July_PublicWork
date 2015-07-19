package com.tz.testlayout.activity;

import com.tz.testlayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/** 
 * @author 作者 tz_stiven QQ:114442034 
 * @version 创建时间：2015年7月16日 下午7:41:21 
 * 类说明 
 */
public class MainActivity extends Activity {
	
	private ImageView mIv_message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
}
