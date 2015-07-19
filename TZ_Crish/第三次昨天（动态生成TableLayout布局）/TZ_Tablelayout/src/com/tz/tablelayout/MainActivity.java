package com.tz.tablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 使用代码动态生成一个表格布局
 * @author Crish
 * 
 */
public class MainActivity extends Activity {
	
	//定义存放表格数据的二维数组
	private String[][] dataArr = new String[10][3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//初始化数据
		initData();
		
		//初始化视图控件
		initTableView();
	}

	private void initTableView() {
		//定义表格布局根布局
		TableLayout rootView = new TableLayout(this);
		ViewGroup.LayoutParams rootParam = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		rootView.setLayoutParams(rootParam);
		
		//定义单元格参数
		TableRow.LayoutParams tvParam = new TableRow.LayoutParams(
				0, ViewGroup.LayoutParams.WRAP_CONTENT);
		tvParam.weight = 1.0f; //设置一行被各个单元格平分
		
		//定义竖直线，宽度为1;
		TableRow.LayoutParams vLineParam = new TableRow.LayoutParams(
				1, ViewGroup.LayoutParams.MATCH_PARENT);
		//定义水平直线，高度为1;
		TableRow.LayoutParams hLineParam = new TableRow.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 1);
		
		TableRow rowView;  //定义表格的一行容器TableRow
		TextView colTv;    //定义一个单元格TextView
		View vLine, hLine; 
		for(int i = 0; i < dataArr.length; i++) {
			rowView = new TableRow(this); 
			for(int j = 0; j < dataArr[i].length; j++) {
				//初始化单元格TextView, 并设置相关属性
				colTv = new TextView(this);
				colTv.setLayoutParams(tvParam);
				colTv.setTextColor(Color.BLUE);
				colTv.setText(dataArr[i][j]);
				colTv.setGravity(Gravity.CENTER_HORIZONTAL);
				colTv.setPadding(0, 10, 0, 10);
				
				//定义一条竖直线
				vLine = new View(this);
				vLine.setLayoutParams(vLineParam);
				vLine.setBackgroundColor(Color.RED);
				
				//添加单元格TextView
				rowView.addView(colTv);
				rowView.addView(vLine); //添加竖线一作区分单元格
				
			}
			//添加一行
			rootView.addView(rowView);
			
			//添加一条水平线，高度为1px
			hLine = new View(this);
			hLine.setLayoutParams(hLineParam);
			hLine.setBackgroundColor(Color.RED);
			rootView.addView(hLine);
		}
		
		setContentView(rootView);
	}

	//初始化数据
	private void initData() {
		for(int i = 0; i < dataArr.length; i++) {
			for(int j = 0; j < dataArr[i].length; j++) {
				dataArr[i][j] = "第" + (i+1) + "行" + (j+1) + "列";
			}
		}
		
	}
}
