package com.ws.task.checkbox;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends Activity implements OnClickListener {
	private CheckBox mCheckBox;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mCheckBox = (CheckBox) findViewById(R.id.checkbobox);
		mButton = (Button) findViewById(R.id.btn);
		mButton.setEnabled(false);// ¸øButtonÊÚÈ¨
		mCheckBox.setChecked(false);
		mCheckBox.setOnClickListener(this);
		mButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.checkbobox:
			if (mCheckBox.isChecked()) {
				mButton.setEnabled(true);
			} else {
				mButton.setEnabled(false);
			}
			break;
		case R.id.btn:
			finish();
			break;
		}
	}

}
