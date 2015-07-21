package com.tz.third.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tz.first.R;

/**
 * 第三节课第一个作业.在布局文件中使用 LinearLayout布局实现手机版qq中的"动态"页面的界面布局. 
 * xml布局文件中除了最外面使用了RelativeLayout外，全部使用的LinearLayout布局.
 * 
 * @author JDY
 * 
 */
public class QQLineraLayoutActivity extends Activity {
	/**
	 * 点击后返回上级界面
	 */
	private Button back_bt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qq_lineralayout);
		back_bt = (Button)findViewById(R.id.aqqll_return_bt);
		back_bt.setOnClickListener(new Button.OnClickListener() {// 创建监听
			public void onClick(View v) {
				finish();
			}
		});
	}
	
}
