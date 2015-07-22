package com.tz.doujiang.mydemo03;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tz.doujiang.mydemo03.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        TableLayout tableLayout = new TableLayout(this);
        for (int i = 0; i < 10; i++) {
			TableRow tableRow = new TableRow(this);
			for (int j = 0; j < 3; j++) {
				TextView textView = new TextView(this);
				textView.setText("row"+i+"*"+"column"+j);
				switch (j) {
				case 0:
					textView.setBackgroundColor(0xffff0000);
					break;
				case 1:
					textView.setBackgroundColor(0xffffff00);
					break;
				case 2:
					textView.setBackgroundColor(0xff00ffff);
					break;

				default:
					break;
				}
				textView.setTextColor(0xffffffff);
				TableRow.LayoutParams params = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.weight=1;
				params.gravity=Gravity.CENTER_HORIZONTAL;
				tableRow.addView(textView,params);
			}
			tableLayout.addView(tableRow,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}
        ((ViewGroup) findViewById(R.id.content)).addView(tableLayout,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

}
