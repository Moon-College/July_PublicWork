package com.example.Logcat_Phone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
public class MainActivity extends Activity {
	private Button bt_state;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// …Ë÷√title
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.title);
		bt_state=(Button) findViewById(R.id.bt_state);
		bt_state.requestFocus();
	}
}
