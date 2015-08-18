package com.ocean.anim.inflator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lsn15_propertyanimation.R;

public class InflatorAnimActivity extends Activity implements OnClickListener {

	private ImageView iv_inflator;
	private TextView tv_single;
	private TextView tv_together;
	private TextView tv_sync;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inflator);
		findView();
		setOnClick();
	}

	private void findView() {
		iv_inflator = (ImageView) findViewById(R.id.iv_inflator);
		tv_single = (TextView) findViewById(R.id.tv_single);
		tv_together = (TextView) findViewById(R.id.tv_together);
		tv_sync = (TextView) findViewById(R.id.tv_sync);
	}
	
	private void setOnClick() {
		tv_single.setOnClickListener(this);
		tv_together.setOnClickListener(this);
		tv_sync.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_single:
			InflatorAnim.startAnim(this, R.animator.rotation, iv_inflator);
			break;
		case R.id.tv_together:
			InflatorAnim.startAnim(this, R.animator.muli_anim, iv_inflator);
			break;
		case R.id.tv_sync:
			InflatorAnim.startAnim(this, R.animator.muli_anim_sync, iv_inflator);
			break;
		default:
			break;
		}
	}
}
