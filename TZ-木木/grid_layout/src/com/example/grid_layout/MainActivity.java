package com.example.grid_layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private final String[] showString = {"+", "1", "2", "3", 
			"-", "4", "5", "6", 
			"*", "7", "8", "9", 
			"/", ".", "0", "="};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//表格布局
		GridLayout gridLayout = new GridLayout(this);
		GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
		gridLayout.setLayoutParams(layoutParams);
		
		gridLayout.setColumnCount(4);
		gridLayout.setRowCount(6);
		gridLayout.setOrientation(GridLayout.HORIZONTAL);
		
		//添加textView用与显示
		TextView textView = new TextView(this);
		textView.setText("0");
		textView.setTextSize(50);
		GridLayout.LayoutParams tvLayoutParams = new GridLayout.LayoutParams(GridLayout.spec(0, 4), GridLayout.spec(0, 4));
		textView.setLayoutParams(tvLayoutParams);
		
		gridLayout.addView(textView);
		
		
		//添加控件
		for(int i = 0; i < showString.length; i++){
			Button button = new Button(this);
			button.setText(showString[i]);
			gridLayout.addView(button);
		}
		
		
		
		setContentView(gridLayout);
	}

}
