package com.tz;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TableLayout tableLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 声明二维数组
		String[][] strs = new String[5][3];
		String str = null;
		// 创建Tablelayout
		tableLayout = new TableLayout(this);
		tableLayout.setOrientation(TableLayout.VERTICAL);
		// 设定tablelayout宽高属性
		TableLayout.LayoutParams lp = new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		// 绑定参数
		tableLayout.setLayoutParams(lp);
		
		
		for (int i = 1; i <= strs.length; i++) {
			// 创建tablerow
			TableRow tableRow = new TableRow(this);
			tableRow.setOrientation(TableRow.HORIZONTAL);
			tableRow.setBackgroundColor(Color.GREEN);
			//设定tablerow的宽高
			TableRow.LayoutParams trLp = new TableRow.LayoutParams(
					TableLayout.LayoutParams.MATCH_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT);
			tableRow.setLayoutParams(trLp);
			
			
			for (int j = 1; j <= strs[0].length; j++) {
				str = "第" + i + "行，" + "第" + j + "列";
				
				TextView mtView = new TextView(this);
				
				// 添加textview文本信息
				mtView.setText(str);
				
				if(j==1){
					mtView.setBackgroundColor(Color.RED);
				}else if(j==2){
					mtView.setBackgroundColor(Color.GREEN);
				}else if(j==3){
					mtView.setBackgroundColor(Color.BLUE);
				}
				// 设定textview宽高以及权重
				
				TableRow.LayoutParams tvLp = new TableRow.LayoutParams(0,
						TableRow.LayoutParams.WRAP_CONTENT, 1);
				// 给textview设定参数
				mtView.setLayoutParams(tvLp);
				
				// 将textview添加至tablerwo中
				tableRow.addView(mtView);
				
				Toast.makeText(this, str, 1).show();
			}
			tableLayout.addView(tableRow);
		}
		setContentView(tableLayout);
	}
}
