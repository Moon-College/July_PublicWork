package com.example.customer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
/**
 * 此方法描述的是：输入行数和列数 自动生成布局
 * @author:  studio
 * @最后修改人： studio 
 * MainActivityd
 */
public class MainActivity extends Activity implements OnClickListener {
	private RelativeLayout layout;
	private LinearLayout ll;
	private EditText rowEt;
	private EditText ColumnEt;
	private Button okBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layout = (RelativeLayout) findViewById(R.id.layout);
		dynamicLayout();

	}
	/**
	 * 此方法描述的是：动态添加控件
	 * @author:  studio
	 * @最后修改人： studio 
	 * dynamicLayout
	 */
	private void dynamicLayout() {
		// 动态设置TableLayout
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(params);
		// 生成一个行数的输入框
		rowEt = new EditText(this);
		LayoutParams rowEtParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		rowEt.setLayoutParams(rowEtParams);
		rowEt.setHint("请输入行数");
		// 生成一个列数的输入框
		ColumnEt = new EditText(this);
		LayoutParams ColumnEtParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ColumnEt.setLayoutParams(ColumnEtParams);
		ColumnEt.setHint("请输入列数");
		// 生成一个按钮
		okBtn = new Button(this);
		okBtn.setId(110);
		okBtn.setText("生成表格");
		okBtn.setOnClickListener(this);
		ll.addView(rowEt);
		ll.addView(ColumnEt);
		ll.addView(okBtn);
		layout.addView(ll);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 110:
			getTableLayout();
			break;

		default:
			break;
		}

	}

	/**
	 * 
	 * 此方法描述的是：得到TableLayout布局
	 * @author:  studio
	 * @最后修改人： studio 
	 * getTableLayout void
	 */
	public void getTableLayout(){
		for (int i = 0; i < ll.getChildCount(); i++) {
			View view = ll.getChildAt(i);
			if (view instanceof TableLayout) {
				ll.removeView(view);
			}
		}
		// 行数
		String rowCountStr = rowEt.getText().toString().trim();
		// 列数
		String ColumnCountStr = ColumnEt.getText().toString().trim();
		TableLayout tableLayout = new TableLayout(MainActivity.this);
		for (int i = 0; i < Integer.parseInt(rowCountStr); i++) {
			TableRow row = new TableRow(MainActivity.this);
			for (int j = 0; j < Integer.parseInt(ColumnCountStr); j++) {// 添加列
				TextView col = new TextView(MainActivity.this);
				col.setText("i=" + i);
				col.setTextColor(0xFFFF0000);
				row.addView(col);// 添加列
			}
			tableLayout.addView(row);
		}
		ll.addView(tableLayout);

	}

}
