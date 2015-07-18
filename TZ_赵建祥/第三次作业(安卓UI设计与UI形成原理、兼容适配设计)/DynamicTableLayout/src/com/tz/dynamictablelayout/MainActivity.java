package com.tz.dynamictablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tz.dynamictablelayout.util.ColorUtil;
import com.tz.dynamictablelayout.util.DensityUtil;
import com.tz.dynamictablelayout.util.MyLog;

/**
 * 表格布局
 * @author 赵建祥
 *
 */
public class MainActivity extends Activity {

	public static final String MY_TAG="MY_MainActivity";
	
	private static int TD_NUMS = 30;// 单元格总个数
	private static int TR_NUMS = 3;// 每行放的单元格数量

	private String[][] tds;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG=true;
		// setContentView(R.layout.main);
		tds = initData(TD_NUMS, TR_NUMS);

		createTableLayout();
	}

	/**
	 * 创建表格布局
	 */
	private void createTableLayout() {
		//外层一个相对布局，用来让内部TableLayout可以内容包裹
		RelativeLayout displayRl=new RelativeLayout(this);
		displayRl.setBackgroundColor(Color.WHITE);
		LayoutParams displayRlParams=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		displayRl.setLayoutParams(displayRlParams);
		
		// 创建table
		TableLayout table = new TableLayout(this);
		table.setOrientation(TableLayout.VERTICAL);
		table.setBackgroundColor(Color.RED);// 来个红色背景
		table.setPadding(DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f));
		RelativeLayout.LayoutParams tableParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		tableParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		table.setLayoutParams(tableParams);

		displayRl.addView(table);
		
		//给表格添加数据
		attachDataToTable(table, tds);
		setContentView(displayRl);
	}

	/**
	 * 给表可靠添加数据
	 * 
	 * @param tl 表格布局对象
	 * @param tds2 表格单元格数
	 */
	private TableLayout attachDataToTable(TableLayout tl, String[][] tds2) {
		if (tds2 != null && tds2.length > 0) {
			for (int i = 0; i < tds2.length; i++) {
				// 添加行
				TableRow tr = new TableRow(this);
				for (int j = 0; j < tds2[i].length; j++) {
					//添加列
					TextView td = new TextView(this);
					td.setText(tds2[i][j]);
					td.setTextColor(Color.WHITE);
					td.setBackgroundColor(ColorUtil.generateRandomColor());
					td.setGravity(Gravity.CENTER);
					TableRow.LayoutParams tdParams = new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, 1);
					tdParams.setMargins(
							DensityUtil.dip2px(this, 5f), 
							DensityUtil.dip2px(this, 5f), 
							DensityUtil.dip2px(this, 5f), 
							DensityUtil.dip2px(this, 5f));
					td.setLayoutParams(tdParams);
					tr.addView(td);
				}
				tl.addView(tr);
			}
		}
		return tl;
	}

	/**
	 * 初始化数据，动态创建表格数据
	 * 
	 * @param tD_NUMS2
	 *            单元格总个数
	 * @param tR_NUMS2
	 *            每行放的单元格数量
	 * @return
	 */
	private String[][] initData(int tD_NUMS2, int tR_NUMS2) {
		// 计算行数，能整除的行数为商，不能整除的再加1
		int i = tD_NUMS2 / tR_NUMS2 + (tD_NUMS2 % tR_NUMS2 != 0 ? 1 : 0);
		int j = tR_NUMS2;
		String[][] tempData = new String[i][j];
		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				// 数据从1依次递增
				tempData[m][n] = (m * tR_NUMS2 + n + 1) + "";
				MyLog.i(MY_TAG, tempData[m][n]);
			}
		}
		return tempData;
	}
}