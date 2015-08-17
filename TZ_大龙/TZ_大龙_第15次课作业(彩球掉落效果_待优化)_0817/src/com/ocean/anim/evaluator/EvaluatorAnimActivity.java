package com.ocean.anim.evaluator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.lsn15_propertyanimation.R;

public class EvaluatorAnimActivity extends Activity implements OnClickListener {
	
	private ImageView iv_evaluator;
	private ImageView iv_global;
	private TextView btn_one;
	private TextView btn_two;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluator);
		findView();
		setOnClick();
	}

	private void findView() {
		iv_evaluator = (ImageView) findViewById(R.id.iv_evaluator);
		iv_global = (ImageView) findViewById(R.id.iv_global);
		btn_one = (TextView) findViewById(R.id.btn_one);
		btn_two = (TextView) findViewById(R.id.btn_two);
	}

	private void setOnClick() {
		btn_one.setOnClickListener(this);
		btn_two.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_one:
			EvaluatorAnim.startValue2(iv_evaluator);
			break;
		case R.id.btn_two:
			EvaluatorAnim.startValue(iv_global);
			break;
		default:
			break;
		}
	}

}
