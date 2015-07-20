package com.tanzhuo.twoceishi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tablelayout1 extends Activity {
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	//用代码创建TableLayout,设置宽度和高度
    TableLayout table=new TableLayout(this);
    TableLayout.LayoutParams params=new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
   
    table.setLayoutParams(params);
    for(int i=1;i<=10;i++){
    	//用代码创建TableRow,设置宽度和高度
    	  TableRow.LayoutParams params1=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
    	    TableRow row=new TableRow(this);
    	    row.setGravity(Gravity.CENTER);
    	    
    	    for(int j=1;j<=3;j++){
    	    	 TableRow.LayoutParams trlp_in = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
    	    	 TextView view=new TextView(this);
    	    	    view.setText("行"+i+"列"+j);
    	    	   
    	    	   view.setLayoutParams(trlp_in);
    	    	    row.addView(view);
    	    }
    	    if(i%2==0){
    	    	row.setBackgroundColor(Color.YELLOW);
    	    }else{
    	    	row.setBackgroundColor(Color.BLUE);
    	    }
    	 
    	    table.addView(row,params1);
    }
  
    
   
  

    setContentView(table);
    }
}
