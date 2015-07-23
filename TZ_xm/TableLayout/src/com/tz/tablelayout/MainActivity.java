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
		// TableLayou �Ĳ��ֲ���
		TableLayout.LayoutParams tl_Params = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.MATCH_PARENT);
		// TableRow �Ĳ��ֲ���
		TableRow.LayoutParams tr_Params = new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT);
		// TextView �Ĳ��ֲ���
		LayoutParams tv_Params = new LayoutParams(0,
				TableRow.LayoutParams.WRAP_CONTENT, 1);

		// ����һ��TableLayout����
		TableLayout tl = new TableLayout(this);
		tl.setLayoutParams(tl_Params);

		// ����forѭ�������к���
		for (int i = 1; i < 11; i++) {
			TableRow row = new TableRow(this);
			row.setLayoutParams(tr_Params);
			for (int j = 1; j < 4; j++) {
				TextView tv = new TextView(this);
				tv.setLayoutParams(tv_Params);
				tv.setGravity(Gravity.CENTER);
				tv.setBackgroundColor(bgColorTv[j - 1]);
				tv.setText("��" + i + "��" + j + "��");
				row.addView(tv);
			}
			tl.addView(row);
		}
		setContentView(tl);
	}

}
