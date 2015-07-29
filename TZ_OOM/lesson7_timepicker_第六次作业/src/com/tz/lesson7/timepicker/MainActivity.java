package com.tz.lesson7.timepicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private SimpleDateFormat dateFormat;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
	}

	public void onTimePickerDialog(View v) {
		TimePickerDialog timePickerDialog = createTimePickerDialog();
		timePickerDialog.show();
	}
	
	
	private TimePickerDialog createTimePickerDialog() {
		Date date = new Date();
		TimePickerDialog timePickerDialog = new TimePickerDialog(this, new OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Toast.makeText(MainActivity.this, "当前时间 为：" + hourOfDay + ": " + minute, Toast.LENGTH_LONG).show();
			}
			
		}, date.getHours(), date.getMinutes(), true);
		
		timePickerDialog.setTitle(dateFormat.format(date));
		return timePickerDialog;
	}
	
}
