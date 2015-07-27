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
	 * Java����ʵ�ֱ�񲼾�
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	 
		// ����tablelayout
	        tableLayout = new TableLayout(this);
	        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(
	        		LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	        tableLayout.setLayoutParams(tableLayoutParams);
	        
		 //����Tablerow����
		 TableRow tr = new TableRow(this);
		 LayoutParams trp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		 
		 tr.setLayoutParams(trp);
		 
		 //����textview������
		  TextView tv1 = new TextView(this);
		  tv1.setPadding(5, 5, 5, 5);
		  tv1.setWidth(100);
		  tv1.setGravity(Gravity.CENTER);
		  tv1.setText("��һ��");
		  
		  TextView tv2 = new TextView(this);
		  tv2.setPadding(5, 5, 5, 5);
		  tv2.setWidth(100);
		  tv2.setGravity(Gravity.CENTER);
		  tv2.setText("�ڶ���");
		  
		  TextView tv3 = new TextView(this);
		  tv3.setPadding(5, 5, 5, 5);
		  tv3.setWidth(100);
		  tv3.setGravity(Gravity.CENTER);
		  tv3.setText("������");
		  
		  tr.addView(tv1);
		  tr.addView(tv2);
		  tr.addView(tv3);
		  tableLayout.addView(tr);
		
		  //����EditText��text�Լ�background
		  for(int i =0; i<strlist.length;i++){
			  tableRow_current = new TableRow(this);//��edit����ͬһ��tablerow�м�
			  tableRow_current.setLayoutParams(trp);
			  for(int j =0 ;j<strlist[1].length;j++){
				  Log.i("Text", strlist[i][j].toString());
				  EditSetTextAndBack(i, j); 	
				  tableRow_current.addView(edit);
			  }			
			  trList.add(tableRow_current);	 //��ÿһ��tablerow����list���棬��װ��Tablelayout��
			  tableLayout.addView(trList.get(i));	          
		  } 
		  setContentView(tableLayout);
	}
	
	/**
	 * ����EditText��ֵ�뱳����ɫ
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
