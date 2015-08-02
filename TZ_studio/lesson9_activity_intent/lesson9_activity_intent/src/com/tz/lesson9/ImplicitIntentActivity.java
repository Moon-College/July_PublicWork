package com.tz.lesson9;

import android.os.Bundle;
import android.widget.TextView;

/**
 * 此类的描述： 隐式
 * @author:  Sky
 * @最后修改人： Sky 
 * @最后修改日期:2015年8月2日 上午4:31:29
 * @version: 2.0
 */
public class ImplicitIntentActivity extends BaseActivity{
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.implicit_activity);
		this.tv=(TextView)this.findViewById(R.id.tv);
		if (getIntent().getAction().equals("com.action.implicit")) {
			this.tv.setText("是隐式意图跳转过来的");
		}
	}

}
