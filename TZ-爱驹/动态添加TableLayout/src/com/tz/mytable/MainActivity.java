package com.tz.mytable;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author 爱驹
 * @date 2015.07.18
 */
public class MainActivity extends Activity {

	private TableLayout tl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		setContentView(tl);
	}

	/**
	 * 动态添加tablelayout
	 */
	private void initView() {
		final int ROWCOUNT = 3;
		// 创建TableLayout
		tl = new TableLayout(this);
		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT);
		tl.setLayoutParams(params);

		// 向TableRow中添加TextView
		for (int i = 0; i < 5; i++) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT));
			// 创建多个TextView
			for (int j = 0; j < ROWCOUNT; j++) {
				TextView tv = new TextView(this);
				TableRow.LayoutParams tv_layout = new TableRow.LayoutParams(0,
						TableRow.LayoutParams.WRAP_CONTENT, 1);
				tv.setText("textview" + (i * ROWCOUNT + j));
				tv.setTextColor(Color.BLACK);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(20f);
				tv.setLayoutParams(tv_layout);
				// 选择颜色
				if (j == 0) {
					tv.setBackgroundColor(Color.RED);
				} else if (j == 1) {
					tv.setBackgroundColor(Color.GREEN);
				} else if (j == 2) {
					tv.setBackgroundColor(Color.BLUE);
				}

				// 将TextView添加到TableRow中
				tr.addView(tv);
			}

			// 将TableRow添加到TableLayout中
			tl.addView(tr);
		}
	}
}
