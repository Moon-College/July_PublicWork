package com.ws.tablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TableLayout tl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
//		加载布局
		setContentView(tl);
	}
// 初始化控件
	private void init() {
		//new一个TableLayout布局
		tl = new TableLayout(this);
		//设置布局的宽高
		TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tl.setLayoutParams(lp);
		for (int i = 0; i < 5; i++) {
			//new一个TableRow
			TableRow tr = new TableRow(this);
			//设置TableRow的宽高
			tr.setLayoutParams(lp);
			for (int j = 0; j < 3; j++) {
				//new一个TextView
				TextView tv = new TextView(this);
				//设置TextView的宽高
				TableRow.LayoutParams tvlp = new TableRow.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						android.widget.TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
				//应用到TextView上
				tv.setLayoutParams(tvlp);
				//设置TextView文字的对其方式
				tv.setGravity(Gravity.CENTER);
				//把TextView放到TableRow中
				tr.addView(tv);
				//设置TextView显示的内容
				tv.setText("第" + i + "行" + "，第" + j + "列");
				//根据列数设置textView的背景颜色
				switch (j) {
				case 0:
					tv.setBackgroundColor(Color.BLUE);
					break;
				case 1:
					tv.setBackgroundColor(Color.RED);
					break;
				case 2:
					tv.setBackgroundColor(Color.GREEN);
					break;

				}
			}
			//把TableRow放到TableLayout中
			tl.addView(tr);
		}

	}

}
