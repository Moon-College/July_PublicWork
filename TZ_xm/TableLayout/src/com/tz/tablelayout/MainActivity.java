package com.tz.tablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class MainActivity extends Activity {

	private int[] bgColorTv = new int[] { Color.RED, Color.GREEN, Color.BLUE };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTableLayout();
	}

	private void setTableLayout() {
		// TableLayou 的布局参数
		TableLayout.LayoutParams tl_Params = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.MATCH_PARENT);
		// TableRow 的布局参数
		TableRow.LayoutParams tr_Params = new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT);
		// TextView 的布局参数
		LayoutParams tv_Params = new LayoutParams(0,
				TableRow.LayoutParams.WRAP_CONTENT, 1);

		// 创建一个TableLayout容器
		TableLayout tl = new TableLayout(this);
		tl.setLayoutParams(tl_Params);

		// 利用for循环设置行和列
		for (int i = 1; i < 11; i++) {
			TableRow row = new TableRow(this);
			row.setLayoutParams(tr_Params);
			for (int j = 1; j < 4; j++) {
				TextView tv = new TextView(this);
				tv.setLayoutParams(tv_Params);
				tv.setGravity(Gravity.CENTER);
				tv.setBackgroundColor(bgColorTv[j - 1]);
				tv.setText("第" + i + "行" + j + "列");
				row.addView(tv);
			}
			tl.addView(row);
		}
		setContentView(tl);
	}

}
