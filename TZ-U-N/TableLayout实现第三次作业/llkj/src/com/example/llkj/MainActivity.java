package com.example.llkj;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	//定义存放表格数据的二维数组
	private String[][] dataBrr = new String[10][3];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.main);
		//初始化数据
		initData();
		//初始化视图控件
		initTableView();
	}


	private void initTableView() {
	
	//定义表格布局
		TableLayout tableLayout= new TableLayout(this);
		ViewGroup.LayoutParams  params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.WRAP_CONTENT	);			
		   tableLayout.setLayoutParams(params);
		   //定义单元格参数
		   TableRow.LayoutParams params2 = new TableRow.LayoutParams(0,   ViewGroup.LayoutParams.WRAP_CONTENT	);
		   params2.weight=1.0f;//设置一行被多个单元格平分
		   
			//定义竖直线，宽度为1;
		   TableRow.LayoutParams params3 = new TableRow.LayoutParams(1,ViewGroup.LayoutParams.MATCH_PARENT);
		   //定义水平直线，高度为1;
		   TableRow.LayoutParams params4= new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
		   
		   
		   TableRow coco ;
		   TextView te_xt;
		   View vmiew,vline;
		   for (int i = 0; i < dataBrr.length; i++) {
			
			   coco = new TableRow(this);
			   for (int j = 0; j < dataBrr[i].length; j++) {
				
				   te_xt = new TextView(this);
				   te_xt.setLayoutParams(params2);
				 //  te_xt.setText(Color.BLUE);
			       te_xt.setText(dataBrr[i][j]);
				   te_xt.setGravity(Gravity.CENTER_HORIZONTAL);
				   te_xt.setPadding(0, 10, 0, 10);
				 //定义一条竖直线
				   vline = new View(this);
				   vline.setLayoutParams(params3);
				   vline.setBackgroundColor(Color.BLUE);	
				 //添加单元格TextView
				   coco.addView(te_xt);
				   coco.addView(vline);		//添加竖线一作区分单元格		   
			}
			 //添加一行
			   tableLayout.addView(coco);
			 //添加一条水平线，高度为1px
			   vmiew = new View(this);
			   vmiew.setLayoutParams(params4);
			   vmiew.setBackgroundColor(Color.RED);
			   tableLayout.addView(vmiew);			   			   
		}	
		   
		   		   setContentView(tableLayout); 
	}


	private void initData() {
		for (int i = 0; i < dataBrr.length; i++) {
			for (int j = 0; j < dataBrr[i].length; j++) {
				dataBrr[i][j] ="第"+(i+1)+"行"+(j+1)+"列";
			}
		}
		
	}
}
