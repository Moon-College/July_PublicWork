package com.qingzhu.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.inputmethodservice.Keyboard.Row;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		onCreateTableLayout();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void onCreateTableLayout() {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		List<String> contentList = new ArrayList<String>();
		contentList.add("Æ»¹û");
		contentList.add("Ïã½¶");
		contentList.add("Î÷¹Ï");
		
		contentList.add("²ÝÝ®");
		contentList.add("²¤ÂÜ");
		contentList.add("¸ÊÕá");
		
		contentList.add("½ð½Û");
		contentList.add("ÁñÁ«");
		contentList.add("Ê¯Áñ");
		
		contentList.add("¶¬Ôæ");
		contentList.add("·ïÀæ");
		contentList.add("ÀóÖ¦");
		
		TableLayout mainLayout = new TableLayout(this);
		TableLayout.LayoutParams  tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
		mainLayout.setLayoutParams(tableParams);
		
		TableRow row = null;
		
		for (int i = 0; i < contentList.size(); i++) {
			if(0 == i % 3) {
				row = new TableRow(this);
				TableLayout.LayoutParams  rowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
				row.setBackgroundColor(Color.parseColor("#E7E7F7"));
				row.setPadding(2, 2, 2, 2);
				mainLayout.addView(row, rowParams);
			}
			TextView textView  = new TextView(this);
		    String text = contentList.get(i);
		    textView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
		    textView.setGravity(Gravity.CENTER_HORIZONTAL);
		    textView.setTextSize(20);
		    textView.setText(text);
			row.addView(textView);
		}
		setContentView(mainLayout);
	}
}
