package com.tz.l2.qqzonelayout;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableLayoutByCode extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TableLayout table=new TableLayout(this);
		table.setBackgroundColor(color.white);
		TableLayout.LayoutParams params=new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
		table.setLayoutParams(params);
		
		
		for(int row=0;row<4;row++){
			TableRow rows=new TableRow(table.getContext());
			TableLayout.LayoutParams rowParams=new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
			rows.setLayoutParams(rowParams);
			for(int clo=0;clo<3;clo++){
				TextView tv=new TextView(this);
				tv.setText("row"+row+"clo"+clo+" ");
				tv.setTextColor(Color.BLACK);
				tv.setTextSize(20f);
				rows.addView(tv);
			}
			if(row%2==0){
				rows.setBackgroundColor(Color.WHITE);
			}else{
				rows.setBackgroundColor(Color.LTGRAY);
			}
			table.addView(rows);
		}
		setContentView(table);
	}
}
