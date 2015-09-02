package com.ocean.anim.set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lsn15_propertyanimation.R;

public class SetAnimActivity extends Activity implements OnClickListener {

	private ImageView iv_set;
	private TextView tv_together;
	private TextView tv_sync;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		findView();
		setOnClick();
	}

	private void setOnClick() {
		tv_together.setOnClickListener(this);
		tv_sync.setOnClickListener(this);
	}

	private void findView() {
		iv_set = (ImageView) findViewById(R.id.iv_set);
		tv_together = (TextView) findViewById(R.id.tv_together);
		tv_sync = (TextView) findViewById(R.id.tv_sync);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_together:
			SetAnim.startAnim(iv_set);
			break;
		case R.id.tv_sync:
			SetAnim.startAnimSync(iv_set);
			break;
		default:
			break;
		}
	}
}
