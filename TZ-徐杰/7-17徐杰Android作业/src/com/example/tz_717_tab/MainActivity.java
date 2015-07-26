package com.example.tz_717_tab;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.Menu;
import android.view.Window;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
    Integer [] colors = new Integer[5] ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setColor();
		TableLayout layout = new TableLayout(this);
		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout.setOrientation(TableLayout.VERTICAL);
		layout.setLayoutParams(params);
		for (int i = 0; i < 5; i++) {
			TableRow row = new TableRow(this);
			TableRow.LayoutParams RowParams = new TableRow.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			row.setLayoutParams(RowParams);
			for (int j = 0; j < 3; j++) {
				TextView textView = new TextView(this);
				TableRow.LayoutParams textParams = new TableRow.LayoutParams(0,
						LayoutParams.WRAP_CONTENT, 1);
				textView.setBackgroundColor(getResources().getColor(colors[i]));
				textView.setLayoutParams(textParams);
				textView.setTextSize(16.0f);
				textView.setTextColor(Color.parseColor("#FFFFFF"));
				textView.setText("第"+i+"行"+"第"+(j+1)+"列");
				row.addView(textView);
			}

			layout.addView(row);

		}

		setContentView(layout);
	}
	private void setColor() {
    colors[0] = R.color.color1;
    colors[1] = R.color.color2;
    colors[2] = R.color.color3;
    colors[3] = R.color.color4;
    colors[4] = R.color.color5;
	}

}
