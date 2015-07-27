package com.tz.tablelayout;
  
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText; 
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class tablelayout_java extends Activity {
     private EditText edit;
    private TableLayout tableLayout; 
    private TableRow tableRow_current; 
    String [][] strlist = {{"1","1-2","1-3"},{"2","2-2","2-3"},{"3","3-2","3-3"},{"4","4-2","4-3"},{"5","5-2","5-3"},{"6","6-2","6-3"},
    		{"7","7-2","7-3"},{"8","8-2","8-3"},{"9","9-2","9-3"},{"10","10-2","10-3"}};
    
    private ArrayList<TableRow> trList = new ArrayList<TableRow>(); 
   
    /**
	 * Java代码实现表格布局
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	 
		// 设置tablelayout
	        tableLayout = new TableLayout(this);
	        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(
	        		LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	        tableLayout.setLayoutParams(tableLayoutParams);
	        
		 //设置Tablerow属性
		 TableRow tr = new TableRow(this);
		 LayoutParams trp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		 
		 tr.setLayoutParams(trp);
		 
		 //设置textview的属性
		  TextView tv1 = new TextView(this);
		  tv1.setPadding(5, 5, 5, 5);
		  tv1.setWidth(100);
		  tv1.setGravity(Gravity.CENTER);
		  tv1.setText("第一列");
		  
		  TextView tv2 = new TextView(this);
		  tv2.setPadding(5, 5, 5, 5);
		  tv2.setWidth(100);
		  tv2.setGravity(Gravity.CENTER);
		  tv2.setText("第二列");
		  
		  TextView tv3 = new TextView(this);
		  tv3.setPadding(5, 5, 5, 5);
		  tv3.setWidth(100);
		  tv3.setGravity(Gravity.CENTER);
		  tv3.setText("第三列");
		  
		  tr.addView(tv1);
		  tr.addView(tv2);
		  tr.addView(tv3);
		  tableLayout.addView(tr);
		
		  //设置EditText的text以及background
		  for(int i =0; i<strlist.length;i++){
			  tableRow_current = new TableRow(this);//将edit放在同一个tablerow中间
			  tableRow_current.setLayoutParams(trp);
			  for(int j =0 ;j<strlist[1].length;j++){
				  Log.i("Text", strlist[i][j].toString());
				  EditSetTextAndBack(i, j); 	
				  tableRow_current.addView(edit);
			  }			
			  trList.add(tableRow_current);	 //将每一个tablerow都用list保存，在装的Tablelayout中
			  tableLayout.addView(trList.get(i));	          
		  } 
		  setContentView(tableLayout);
	}
	
	/**
	 * 设置EditText的值与背景颜色
	 * @author jiandan
	 * @param i 
	 * @param j
	 */	
	private void EditSetTextAndBack(int i, int j) {
		edit = new EditText(this);
		  edit.setText(strlist[i][j].toString());
		  edit.setPadding(3, 3, 3, 3);
		  edit.setWidth(100);
		  edit.setGravity(Gravity.CENTER);
		
		  int color;
		  Random rnd = new Random();
		  color = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255),rnd.nextInt(255));
		  if (i % 2 == 0) {
		        color = Color.RED;
		    }
		    else {
		        color = Color.BLUE;
		    }
		  edit.setBackgroundColor(color);
	}
	
	 
}
