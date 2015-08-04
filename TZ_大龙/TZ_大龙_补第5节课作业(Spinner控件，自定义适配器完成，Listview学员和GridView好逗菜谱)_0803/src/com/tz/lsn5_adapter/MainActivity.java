package com.tz.lsn5_adapter;

import com.tz.lsn5_adapter.activity.GridViewActivity;
import com.tz.lsn5_adapter.activity.ListViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private Spinner spinner;
	private AutoCompleteTextView acTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initSpinner();
		initAutoComplete();
	}

	private void initAutoComplete() {
		// TODO Auto-generated method stub
		acTextView = (AutoCompleteTextView)findViewById(R.id.acTextView);
		//���ôӵ�1����ʼ�Զ���ʾ
		acTextView.setThreshold(1);
		String[] data = {"Dallon","alex","tz","TZ","�ٶ�","aaaaa","����","�Ϻ�","����","����","ţBX","Android","android","123456","hehehe","����","abcd","best"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_autocomeplete_item,R.id.tv_autoComplete, data);
		acTextView.setAdapter(adapter);
	}

	private void initSpinner() {
		spinner = (Spinner) findViewById(R.id.sp);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.activity_spinner_item,
				R.id.tv_spItem, 
				new String[] { "ѧ��", "��ʦ","����Ա", "ҽ��", "˾��" });
		spinner.setAdapter(adapter);
	}
	
	
	
	public void btnClick(View view) {
		switch (view.getId()) {
			case R.id.btn_listView:
				Intent li = new Intent(this, ListViewActivity.class);
				startActivity(li);
				break;
			case R.id.btn_gridView:
				Intent gi = new Intent(this, GridViewActivity.class);
				startActivity(gi);
				break;
			default:
				break;
		}
	}

}
