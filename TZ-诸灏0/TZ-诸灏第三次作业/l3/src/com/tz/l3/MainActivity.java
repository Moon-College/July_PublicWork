package com.tz.l3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TableLayout tabLayout;
	private TableRow tabRow;
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tabLayout = new TableLayout(this);
	
		TableLayout.LayoutParams tabParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
		
		tabLayout.setLayoutParams(tabParams);
		
		for(int i=0;i<10;i++){
			tabRow = new TableRow(this);
			TableRow.LayoutParams tabRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
			tabRow.setLayoutParams(tabRowParams);
			for(int j=0;j<3;j++){
				tv = new TextView(this);
				//textview没有LayoutParams,只能借用TableRow的LayoutParams
				TableRow.LayoutParams tvParams = new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT );
				tvParams.weight = 1f;
				tv.setText("这是第"+(i+1)+"行");
				tv.setLayoutParams(tvParams);
				tabRow.addView(tv);
			}
			tabLayout.addView(tabRow);
		}
		setContentView(tabLayout);
	}

}
