package com.tz.lsn4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TableLayout tl;
	
	private String[][] stdArray = new String[][]{
			{"编号","姓名","性别","EQ+IQ"},
			{"tz001","董大龙","男","90"},
			{"tz002","罗鼎","男","95"},
			{"tz003","周杰伦","男","80"},
			{"tz004","那英","女","70"},
			{"tz005","汪峰 ","男","80"},
			{"tz006","蔡依林","女","70"},
			{"tz007","陈慧琳","女","70"},
			{"tz008","王杰","男","80"},
			{"tz009","杨幂","女","50"},
			{"tz010","王菲","女","60"}
			
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		//创建一个TableLayout 
		tl = new TableLayout(this);
		//设置为纵向布局
		tl.setOrientation(TableLayout.VERTICAL);
		//定义一个布局属性，并设置到TableLayout中
		TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
		tl.setLayoutParams(tableLayoutParams);
		
		//作业标题与说明
		TextView tv_des = new TextView(this);
		TableRow.LayoutParams tv_desLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
		tv_des.setLayoutParams(tv_desLayoutParams);
		tv_des.setBackgroundColor(Color.BLACK);
		tv_des.setText("第4节课作业：TableLayout布局 代码动态生成");
		tv_des.setTextColor(Color.RED);
		tv_des.setPadding(5, 10, 5, 10);//左上右下
		tv_des.setTextSize(18);
		tl.addView(tv_des);
		
		//遍历一维数据
		for (int i = 0; i < stdArray.length; i++) {
			//定义一个TableRow
			TableRow tr = new TableRow(this);
			//定义一个TableLayout 子控件的属性 ，并设置到TableRow中
			TableRow.LayoutParams tl_lParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
			
			tl_lParams.column = 2;
			tr.setLayoutParams(tl_lParams);
			
			//遍历二维数据
			for (int j = 0; j < stdArray[i].length; j++) {
				//定义一个TextView
				TextView tv = new TextView(this);
				//定义一个TableLayout 子控件的属性
				TableRow.LayoutParams tvParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
				tvParams.weight = 1;
				
				if(i== 0) {
					tv.setTextSize(25);
					tv.setBackgroundColor(Color.BLUE);
					tv.setTextColor(Color.WHITE);
				} else {
					tv.setTextSize(18);
				}
				
				tv.setPadding(5, 5, 5, 5); //左上右下
				
				//设置文本居中
				tv.setGravity(Gravity.CENTER);
				
				//设置TextView 的内容
				tv.setText(stdArray[i][j]);
				//设置到TextView中
				tv.setLayoutParams(tvParams);
				
				//将TextView 加载到  TableRow 中
				tr.addView(tv);
				
				//垂直方向添加一个空的TextView并设置背景颜色作为边框线
				TextView tv_empty = new TextView(this);
				TableRow.LayoutParams tv_emptyLayoutParams = new TableRow.LayoutParams(3,TableRow.LayoutParams.MATCH_PARENT);
				tv_empty.setLayoutParams(tv_emptyLayoutParams);
				tv_empty.setBackgroundColor(Color.BLACK);
				tr.addView(tv_empty);
				
			}
			//将TableRow 加载到  TableLayout 中
			tl.addView(tr);
			
			//水平方向添加一个空的TextView并设置背景颜色作为边框线
			TextView tv_empty_vertical = new TextView(this);
			TableRow.LayoutParams tv_emptyLayoutParams_vertical = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,3);
			tv_empty_vertical.setLayoutParams(tv_emptyLayoutParams_vertical);
			tv_empty_vertical.setBackgroundColor(Color.BLACK);
			tl.addView(tv_empty_vertical);
		}
		
		//将TableLayout 加载进视图
		setContentView(tl);
	}
	
	



}
