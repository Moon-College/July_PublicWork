package com.tz.tz_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author Tokey
 *
 */
@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	private int rownum = 50;// 行>0
	private int column = 4;// 列>0

	// 数据
	List<List<String>> mData = new ArrayList<List<String>>();
	private TableLayout parents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData(rownum, column);
		initView();
		setContentView(parents);
	}

	private void initView() {
		// 创建顶级父容器
		parents = new TableLayout(this);
		TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.MATCH_PARENT);
		parents.setLayoutParams(lp);
		// 创建
		createTable(rownum, column, mData);
	}

	/**
	 * 
	 * @param rownum2
	 * @param mData2
	 */
	private void createTable(int rownum, int column, List<List<String>> mData2) {

		for (int i = 0; i < rownum; i++) {
			TableRow row = new TableRow(this);
			TableRow.LayoutParams lp = new TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT);
			row.setLayoutParams(lp);
			for (int j = 0; j < column; j++) {
				TextView textView = new TextView(this);
				TableRow.LayoutParams lps = new TableRow.LayoutParams(0,
						TableRow.LayoutParams.WRAP_CONTENT);
				lps.weight = 1;
				textView.setLayoutParams(lps);
				textView.setGravity(Gravity.CENTER);
				textView.setText(mData2.get(i).get(j));
				textView.setTextColor(getResources().getColor(
						android.R.color.black));
				textView.setBackgroundColor(Color
						.parseColor(getRandColorCode()));
				row.addView(textView);
			}
			parents.addView(row);
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData(int rownum, int column) {

		for (int i = 0; i < rownum; i++) {
			List<String> data = new ArrayList<String>();
			for (int j = 0; j < column; j++) {
				data.add("第" + (i + 1) + "行，第" + (j + 1) + "列");
			}
			mData.add(data);
		}
	}

	/**
	 * 获取十六进制的颜色代码.例如 "#6E36B4"
	 * 
	 * @return String
	 */
	public static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;

		return "#" + r + g + b;
	}
}
