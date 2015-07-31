package com.example.tzclass8_homework;

import java.util.Calendar;

import com.tz.MainActivity;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends Activity {
	EditText et_name,et_pwd,et_pwdForSure;
	Button btn_ok,btn_cancle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);

	}

	public void show(View v) {
		switch (v.getId()) {
		case R.id.btn_normal:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("这是alert提示消息");
			builder.setIcon(getResources().getDrawable(R.drawable.img3));
			builder.setPositiveButton("确定", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(DialogActivity.this, "这是想要输入的内容",
							Toast.LENGTH_LONG).show();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(DialogActivity.this, "这是想要输入的内容",
							Toast.LENGTH_LONG).show();
				}
			});
			AlertDialog show = builder.show();
			break;
		case R.id.btn_process:
			ProcessTask task = new ProcessTask();
			task.execute(".....");
			break;
		case R.id.btn_time:
			Calendar c = Calendar.getInstance();
			TimePickerDialog tpd = new TimePickerDialog(DialogActivity.this,
					new OnTimeSetListener() {
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							Toast.makeText(DialogActivity.this, hourOfDay+"时"+minute+"分", Toast.LENGTH_LONG).show();
						}
					}, 
					c.get(c.HOUR_OF_DAY), 
					c.get(c.MINUTE), 
					true);
			tpd.show();
			break;
		case R.id.btn_data:
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			DatePickerDialog dpd = new DatePickerDialog(
					this,
					new OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							Toast.makeText(DialogActivity.this, year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日", Toast.LENGTH_LONG).show();
						}
					},
					calendar.get(calendar.YEAR),
					calendar.get(calendar.MONTH),
					calendar.get(calendar.DAY_OF_MONTH));
			dpd.show();
			break;
		case R.id.btn_custom:
			AlertDialog.Builder adb = new AlertDialog.Builder(DialogActivity.this);
			View view = View.inflate(DialogActivity.this, R.layout.popup_item, null);
			adb.setView(view);
			
			et_name = (EditText) view.findViewById(R.id.et_userName);
			et_pwd = (EditText) view.findViewById(R.id.et_pwd);
			btn_ok = (Button) view.findViewById(R.id.btn_ok);
			btn_ok.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(DialogActivity.this, et_name.getText().toString()+et_pwd.getText().toString(), Toast.LENGTH_LONG).show();
				}
			});
			adb.show();
			break;
		case R.id.btn_popup:
			View contentView = View.inflate(DialogActivity.this, R.layout.popup_item, null);
			PopupWindow pw = new PopupWindow(
					contentView, 
					300,
					LayoutParams.WRAP_CONTENT,
					true);
			pw.setBackgroundDrawable(getResources().getDrawable(R.drawable.local_popup_bg));
			int[] location = new int[2];
			v.getLocationInWindow(location);
			pw.showAtLocation(v, Gravity.TOP|Gravity.LEFT, location[0]+100, location[1]);
			et_name = (EditText) contentView.findViewById(R.id.et_userName);
			et_pwd = (EditText) contentView.findViewById(R.id.et_pwd);
			btn_ok = (Button) contentView.findViewById(R.id.btn_ok);
			btn_ok.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(DialogActivity.this, et_name.getText().toString()+et_pwd.getText().toString(), Toast.LENGTH_LONG).show();
				}
			});
			break;
		default:
			break;
		}
	}

	
	
	
	class ProcessTask extends AsyncTask<String, Integer, Boolean> {
		ProgressDialog pg = null;

		@Override
		protected void onPreExecute() {
			pg = new ProgressDialog(DialogActivity.this);
			pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pg.setMessage("正在努力加载");
			pg.setMax(100);
			pg.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			int progress = 0;
			try {
				while (true) {
					progress += 5;
					Thread.sleep(500);
				//	pg.setProgress(progress);
					publishProgress(progress);
					if (progress >= pg.getMax()) {
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			pg.setProgress(values[0]);
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				Toast.makeText(DialogActivity.this, "这是想要输入的内容成功",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(DialogActivity.this, "这是想要输入的内容失败",
						Toast.LENGTH_LONG).show();
			}
			pg.dismiss();
			super.onPostExecute(result);

		}
	}
}
