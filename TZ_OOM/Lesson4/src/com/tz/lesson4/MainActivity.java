package com.tz.lesson4;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 代码实现 表格布局
 *
 */
public class MainActivity extends Activity {

	private int[] columnBgColors = new int[] {Color.RED, Color.GREEN, Color.BLUE};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		initData();
		setContentLayout();
	}
	
	private void setContentLayout() {
		// 初始化一个表格布局
		TableLayout tableLayout = new TableLayout(this);
		// 初始化表格布局LayoutParams
		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
																TableLayout.LayoutParams.MATCH_PARENT, 
																TableLayout.LayoutParams.MATCH_PARENT);
		
		// 设置表格布局参数
		tableLayout.setLayoutParams(params);;
		// 获取数据
		List<List<String>> list = initData();
		
		// 遍历数据，创建TableRow
		for (int i = 0; i < list.size(); i++) {
			// 初始化TableRow
			TableRow tableRow = new TableRow(this);
			// 初始化TableRow LayoutParams
			TableRow.LayoutParams tableRowLayoutParams = new TableRow.LayoutParams(
																		TableRow.LayoutParams.MATCH_PARENT, 
																		TableRow.LayoutParams.WRAP_CONTENT);
			// 设置TableRow 布局参数
			tableRow.setLayoutParams(tableRowLayoutParams);
			// 获取每一行的列
			List<String> columnDatas = list.get(i);
			
			// 每列的布局参数
			TableRow.LayoutParams columnParams = new TableRow.LayoutParams(
																TableRow.LayoutParams.WRAP_CONTENT, 
																TableRow.LayoutParams.WRAP_CONTENT);
			// 设置每列的权重
			columnParams.weight = 1.0f;
			
			for(int j = 0; j< columnDatas.size(); j++) {
				// 获取每列的显示的数据
				String columnData = columnDatas.get(j);
				
				TextView textView = new TextView(this);
				textView.setLayoutParams(columnParams);
				// 设置textVeiw 背景色
				textView.setBackgroundColor(columnBgColors[j]);
				textView.setTextColor(Color.WHITE);
				// 设置textView 内边距
				textView.setPadding(0, 5, 0, 5);
				// 设置单行显示 
				textView.setSingleLine(true);
				textView.setGravity(Gravity.CENTER);
				textView.setText(columnData);
				
				tableRow.addView(textView);
			}
			// 将TableRow 添加到TableLayout中
			tableLayout.addView(tableRow);
		}
		
		setContentView(tableLayout);
	}

	/**
	 * 初始化数据
	 * @return 
	 */
	private List<List<String>> initData() {
		List<List<String>> list = new ArrayList<List<String>>();
		for(int i = 0; i< 10; i++) {
			List<String> subList = new ArrayList<String>();
			for(int j = 0; j< 3; j++) {
				subList.add((i+1) + "行 " + (j+1)+"列");
			}
			list.add(subList);
		}
		return list;
	}
	
}
